package it.polimi.ingsw.model.calculations.influence;

import it.polimi.ingsw.model.enumerations.Color;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.islands.IslandInterface;
import it.polimi.ingsw.model.pawns.MotherNature;
import it.polimi.ingsw.model.pawns.Professor;
import it.polimi.ingsw.model.pawns.Tower;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 */
public class TowerInfluence implements InfluenceCalculator {
    /**
     * Default constructor
     * @param motherNature points to motherNature instance
     */
    public TowerInfluence(Collection<Player> playerList, MotherNature motherNature) {
        this.island = motherNature.getIsland();
        this.players = new ArrayList<Player>(playerList);
        this.manager=manager;
    }

    /**
     * Stores the players
     */
    private Collection<Player> players;

    /**
     * References the InfluenceManager
     */
    InfluenceManager manager;

    /**
     * Stores motherNature instance
     */
    private IslandInterface island;

    /**
     * Calculates the influence with the standard method
     */
    public void calculateInfluence() {

        Integer maxCount = null; // indicates if there is currently a draft
        Player winner = null; // references the winner if currently there is one
        Player oldWinner = null; //references the winner of the previous calculus

        for(Player player : players){
            Integer count = 0;

            if(player.getPlayerColor()==island.getInfluenceColor()){ //if the player has control over the island
                count = island.numOfTowers(); // add the number of tower to the points of the player
                oldWinner = player;
            }


            for(Color color : Color.values()) { // for every color

                if (player.getSchool().getHall().getLine(color)== Professor.getProfessor(color).getPosition()){ // if the player has control of the Professor
                    count += island.numOfStudents(color); //he gets his student points
                }
            }

            if(maxCount==null){     // if it is the first count
                maxCount=count;
                if(count>0){
                    winner = player;
                }
                continue;
            }

            if(count.equals(maxCount)){    // if there is a draft
                winner = null;
            }

            if(count>maxCount){     // if maxCount is lower than count
                maxCount = count;
                winner = player;
            }
        }

        if(winner!=null && island.getInfluenceColor()!=winner.getPlayerColor()){    //if towers have to be mover
            manager.applyInfluence(oldWinner, winner, (Island) island); // apply changes
        }

    }

}