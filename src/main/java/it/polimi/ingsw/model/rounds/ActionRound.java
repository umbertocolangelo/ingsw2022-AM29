package it.polimi.ingsw.model.rounds;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.enumerations.AssistantCard;
import it.polimi.ingsw.model.enumerations.PlayerColor;
import it.polimi.ingsw.model.enumerations.PlayerPhase;
import it.polimi.ingsw.model.enumerations.Wizard;
import it.polimi.ingsw.model.expertCards.ExpertCard;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.pawns.MotherNature;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.studentSuppliers.Cloud;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * 
 */
public class ActionRound implements RoundInterface, Serializable {

    /**
     * Every time is called he set the current player, the player phase e change the player ing from the orderedlist
     */
    public ActionRound(Game game, Boolean isThreePlayers) {
        this.game=game;
        this.game.setCurrentPlayer(this.game.getOrderedPlayerList().getFirst());
        this.currentPlayer=this.game.getCurrentPlayer();
        this.currentPlayer.setPlayerPhase(PlayerPhase.MOVING_STUDENTS);
    }
    /**
     * Keep the track if we already played a card in this round
     */
    private Boolean cardAlreadyPlayed=false;

    /**
     *Keep the reference to the current player
     */
    private Player currentPlayer;

    /**
     *Keep track of the number of students moved
     */
    private Integer studentsMoved = 0;

    /**
     *
     */
    private MotherNature motherNature;

    /**
     * Keep the reference to mother nature
     */
    private Game game;

    /**
     *
     * @return boolean  Check if the round ended end if we all the player played so create pianification
     */
    @Override
    public Boolean checkRoundEnded() {
        if(this.game.getOrderedPlayerList().isEmpty() || game.getOrderedPlayerList().getLast().getCardPlayed()==null) {
            for(Cloud d: game.getClouds())
            game.getBag().addStudentsOnCloud(d);
            for(int i=0;i<game.getPlayerList().size();i++){
                this.game.getPlayerList().get(i).setPlayerPhase(PlayerPhase.CHOOSING_ASSISTANT);
            }
            game.getCardManager().resetCurrentCard();
            this.game.setCurrentPlayer(game.getPlayerList().getFirst());
            this.game.setPianificationRoundState();

            return true;
        }
        if(this.currentPlayer.getPlayerPhase()==PlayerPhase.CHOOSING_CLOUD){
            this.game.setActionRoundState();
            game.getCardManager().resetCurrentCard();
            return true;
        }
        return false;
    }

    /**
     * @param student   The student present in the hall that has to be moved
     * @return boolean  True if the student is moved correctly, false if it's not possible to move
     */
    public Boolean moveStudentIngressToHall(Student student) {
        if(this.currentPlayer.getPlayerPhase() != PlayerPhase.MOVING_STUDENTS ||
                !this.currentPlayer.getSchool().getIngress().getStudents().contains(student) ||
                this.currentPlayer.getSchool().getHall().getLine(student.getColor()).getStudents().size()==10){
            return false;}
        return true;
    }

    /**
     * @param student       Student in the ingress
     * @param island        Island we want to put the student
     * @return              True if it's not his last move
     */
    public Boolean moveStudentIngressToIsland(Student student, Island island) {
        if (this.currentPlayer.getPlayerPhase() != PlayerPhase.MOVING_STUDENTS || !this.currentPlayer.getSchool().getIngress().getStudents().contains(student))
            return false;
        return true;
    }

    /**
     * @param jumps
     * @return
     */
    public Boolean moveMotherNature(Integer jumps) {
       if(currentPlayer.getPlayerPhase()!=PlayerPhase.MOVING_MOTHERNATURE || currentPlayer.getCardPlayedValue()<jumps || jumps<1)
        return false;
       else{
           return true;
       }
    }

    /**
     * @param assistantCard
     * @return
     */
    public Boolean playAssistantCard(AssistantCard assistantCard, Player player) {
        return null;
    }

    /**
     *
     * @param expertCard        We receive the expertCard and called the method needed for each expertCard
     * @return
     */
    public Boolean playExpertCard(ExpertCard expertCard) {
        if(expertCard.getCost()>currentPlayer.getCoins() || cardAlreadyPlayed) // If the current player can't play the card
            return false;
        cardAlreadyPlayed=true;
        return true;
    }


    @Override
    public Boolean chooseColorAndDeck(Player player, PlayerColor color, Wizard wizard) {
        return null;
    }

    @Override
    public Integer getId() {
        return null;
    }


    /**
     * @param student
     * @param island
     * @return
     */
    public Boolean expertStudentToIsland(Student student, Island island) {
        // TODO implement here
        return null;
    }

    /**
     * @param studentCard
     * @param Ingress
     * @return
     */
    public Boolean expertIngressCardSwap(Student studentCard, Student Ingress) {
        return false;
    }

    /**
     *
     * @return
     */

    public Boolean finishExpertMove() {
        return null;
    }

    /**
     * @param studentHall
     * @param studentIngress
     * @return
     */
    public Boolean expertIngressHallSwap(Student studentHall, Student studentIngress) {
     return false;
    }



    /**
     * @param student
     * @return
     */
    public Boolean expertStudentToHall(Student student) {
       return false;
    }

    /**
     * @param cloud     The cloud chose from the
     * @return          True if we can make the move
     */
    public Boolean chooseCloud(Cloud cloud) {
        if (this.currentPlayer.getPlayerPhase() != PlayerPhase.CHOOSING_CLOUD)
            return false;
        if (cloud.getStudents().size() == 0)
            return false;
        LinkedList<Player> players=this.game.getOrderedPlayerList();
        players.removeFirst();
        this.game.setOrderedPlayerList(players);
        return true;
    }


    /**
     * Return the number of the students moved
     */
    public Integer getStudentsMoved(){
        return this.studentsMoved;
    }


    /**
     * Checks if the player has already played an expert card
     * @return true if the player has already played an expert card
     */
    public Boolean getCardAlreadyPlayed(){
        return cardAlreadyPlayed;
    }
}