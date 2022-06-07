package it.polimi.ingsw.server;


import it.polimi.ingsw.listener.PropertyObserver;
import it.polimi.ingsw.message.ClientLost;
import it.polimi.ingsw.message.MessageMethod;
import it.polimi.ingsw.message.SetUp;
import it.polimi.ingsw.message.StartGame;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.utils.SavingManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Server {

    private LinkedList<Player> players = new LinkedList<>();
    private static final int PORT = 12345;
    private ServerSocket serverSocket;private Map<String, SocketClientConnection> waitingConnection = new HashMap<>();
    private Map<String, SocketClientConnection> playingConnection = new HashMap<>();
    private LinkedList<SocketClientConnection> socketConnections = new LinkedList<>();
    private Integer numberOfPlayer = 0;
    private Boolean gameMode; // true for expert mode, false for normal one
    private PropertyObserver propertyObserver;
    private Game game;
    private SetUp setup = new SetUp();
    private Semaphore semaphore = new Semaphore(1);
    /**
     * Set true when a client has been disconnetted, so the server do not modify game
     */
    private Boolean playerIsDisconnetted=false;


//Qui socketClient chiama deregistiring client quando viene disconesso e manda un messaggio ai client che ci si e disconessi

    /**
     * deregister connection
     * @param c
     */
    public synchronized void deregisterConnection(SocketClientConnection c) {
        playerIsDisconnetted=true;
        for(SocketClientConnection clientConnection:socketConnections){
            clientConnection.send(new ClientLost(clientConnection.getName()));
        }
        playingConnection.remove(c);
        //playingConnection.remove(opponent);
        Iterator<String> iterator = waitingConnection.keySet().iterator();
        while (iterator.hasNext()) {
            if (waitingConnection.get(iterator.next())==c) {
                iterator.remove();
            }
        }
    }

    /**
     * @param c         The socketConnection which is currently running
     * @param name         The name of the player
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InterruptedException     Thrown when the modifyGame doesn't end
     */
    public synchronized void lobby(SocketClientConnection c, String name)
            throws IOException, ClassNotFoundException, InterruptedException {

        List<String> keys = new ArrayList<>(waitingConnection.keySet());
        System.out.println("New client " + name);
        waitingConnection.put(name, c);
        keys = new ArrayList<>(waitingConnection.keySet());
        if (waitingConnection.size()==numberOfPlayer) {
            for (SocketClientConnection d : waitingConnection.values()) {
               // if (isCLi){
                 //   d.send("Players arrived, starting game..");
              //  }
            }

            SocketClientConnection c1 = waitingConnection.get(keys.get(0));
            SocketClientConnection c2 = waitingConnection.get(keys.get(1));

            Player player1 = new Player(c1.getName());
            players.add(player1);
            Player player2 = new Player(c2.getName());
            players.add(player2);

            if (waitingConnection.size()==3) {
                SocketClientConnection c3 = waitingConnection.get(keys.get(2));
                Player player3 = new Player(c3.getName());
                players.add(player3);
            }
            // check if there is a matching game saved
            LinkedList<String> playerNames = new LinkedList<String>();
            for(Player p : players) {
                playerNames.add(p.getName());
            }
            Game loadedGame = SavingManager.getInstance().loadGame(playerNames);
            if(loadedGame!=null) { // if there is a save
                game = loadedGame;
                System.out.println("Previously saved game loaded");
                playingConnection.putAll(waitingConnection);
                waitingConnection.clear();
                sendGame();
            } else {
                game = new Game();
                propertyObserver = new PropertyObserver(game,this);
                game.addListener(propertyObserver);
                MessageMethod messageMethod=new StartGame();
                ((StartGame)messageMethod).setGameMode(gameMode);
                ((StartGame)messageMethod).setPlayers(players);

                Thread t1 = new Thread( modifyGame(messageMethod));
                t1.join();
                playingConnection.putAll(waitingConnection);
                waitingConnection.clear();
            }
        }
    }

    /**
     * @throws IOException
     */
    public Server() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
    }

    /**
     * This thread is always open uses a semphore to handle the connections, once he gets on he waits for the semphore and start the thread
     */
    public void run(){
        int connections = 0;
        System.out.println("Server is running");

        while (!Thread.currentThread().isInterrupted() ) { //Abbiamo un problema che il client si disconnetete se tutte due si connetono insieme e scrive il secondo client (?)

            try {
                Socket newSocket = serverSocket.accept();
                newSocket.setSoTimeout(360000);
                connections++;
                System.out.println("Ready for the new connection - " + connections);
                SocketClientConnection socketConnection = new SocketClientConnection(newSocket, this);
                if (socketConnections.isEmpty())
                    socketConnection.setIsFirst();

                socketConnections.add(socketConnection);

                Thread t0 = new Thread(socketConnection);
                //semaphore.acquire(); //utilizza un semaforo per far gestire le connessioni iniziali
                t0.start();

                //  } catch (IOException e) {
                System.out.println("Seee!");

                // } catch (InterruptedException e) {
                //   e.printStackTrace();

                //  } catch (InterruptedException e) {
            }catch(SocketTimeoutException e) {
                System.out.println("### Timed out after 5 seconds.");
                //}            } catch (IOException e) {
                //  e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param username  The name of the player who is calling this method
     * @return  boolean     True if the name is not already chosen, false instead
     */
    public Boolean equalName(String username,Boolean isFirst) {
        //return true; //Serve per la GUI adesso non possiamo ancora mandare i giusti messaggi
        if(isFirst)
            return false;
        for (int i = 0; i<socketConnections.size()-1; i++) {
            if(socketConnections.get(i).getName() == null)
                return false;
            if (socketConnections.get(i).getName().equals(username))
               return true;
        }
        return false;
    }
    /**
     * This method is called from observer, after its modified we sent the game to the client
     */
    public void sendGame(){
        System.out.println("Sending game to clients");
            for (SocketClientConnection c : playingConnection.values()) {
                c.send(game);
        }
    }

    /**
     *
     * @return  game    The reference to the game
     */
    public Game getGame() { return this.game; }

    /**
     * Synchronized the modifying in game with the other threads
     * @param object
     * @return
     */
    public Thread modifyGame(Object object){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                if (object instanceof MessageMethod && !playerIsDisconnetted) {
                   ((MessageMethod) object).apply(game);
                }
                }
            });
        t.start();
        return t;
    }

    public void setGameMode(Boolean gameMode) {
        this.gameMode = gameMode;
    }

    public void setNumberOfPlayer(Integer numberOfPlayer){
        this.numberOfPlayer=numberOfPlayer;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }
}


