package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.player.board.School;
import it.polimi.ingsw.model.enumerations.AssistantCard;
import it.polimi.ingsw.model.enumerations.PlayerColor;
import it.polimi.ingsw.model.enumerations.PlayerPhase;
import it.polimi.ingsw.model.enumerations.Wizard;
import it.polimi.ingsw.utils.IdManager;

import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Player implements Serializable {

    /**
     * Constructor that require the username and set the deck
     */
    public Player(String name) {
        this.name = name;
        this.id = idCounter.toString();
        IdManager.getInstance().addPlayer(this);
        idCounter++;
        if(idCounter==4){idCounter=1;}
        for (AssistantCard a: AssistantCard.values())
           assistantCards.add(a);
    }

    /**
     * Stores the current available id
     */
    private static Integer idCounter = 1;

    /**
     * 
     */
    private String id;

    /**
     *  Name of the player
     */
    private String name;

    /**
     *  Which wizard the player chose
     */
    private Wizard wizard;

    /**
     *  The player color
     */
    private PlayerColor color;

    /**
     * Indicates if the player plays a game with 2 or 3 players (default set to 2)
     */
    private Boolean isThreePlayers = false;

    /**
     * The deck of this player
     */
    private ArrayList<AssistantCard> assistantCards = new ArrayList<AssistantCard>();

    /**
     *  The card that the player played
     */
    private AssistantCard playedCard;

    /**
     *  Represent the number of coin own by this player
     */
    private Integer coins = 0;

    /**
     * Reference to the school of this player
     */
    private School school;

    /**
     * The value of the card chose
     */
    private Integer cardValue;

    /**
     * The phase where this player is.
     */
    private PlayerPhase phase;

    /**
     * True if the player is the winner
     */
    private Boolean isWinner = false;

    /**
     * @return  String        The name of this player
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return      The wizard tht the player chose
     */
    public Wizard getWizard() {
        return this.wizard;
    }

    /**
     * Sets isThreePlayers to true (called if the current game is for 3 players)
     */
    public void setThreePlayers(){
        isThreePlayers = true;
    }

    /**
     * When the player plays the card, that card is  removed;
     */
    public void playAssistantCard(AssistantCard card) {
        this.cardValue = card.getValue();
        this.playedCard = card;
        assistantCards.remove(assistantCards.indexOf(card));
    }

    /**
     *
     */
    public void setAssistantCard(AssistantCard a){
         this.playedCard=a;
    }
    /**
     * @return  The value of the card that this player played.
     */
    public Integer getCardPlayedValue() {
        return this.cardValue;
    }

    /**
     * @return  The card that the player played
     */
    public AssistantCard getCardPlayed() {
        return this.playedCard;
    }

    /**
     * @return      The school of this player
     */
    public School getSchool() {
        return this.school;
    }

    /**
     * @return the number of coins of this player
     */
    public Integer getCoins() {
        return this.coins;
    }

    /**
     * @return a copy of the deck of this player
   */
    public ArrayList<AssistantCard>getPlayedCards() {
         return new ArrayList<AssistantCard>(this.assistantCards);
    }

    /**
     * Sets the color of the player and instantiates its school
     * @param color is the color set to the player
     */
    public void setPlayerColor(PlayerColor color) {
        this.color = color;
        this.school = new School(color, isThreePlayers);
    }

    /**
     * @return the PlayerColor of the player
     */
    public PlayerColor getPlayerColor() {
        return this.color;
    }

    /**
     * @return true if the deck doesn't contain any cards, otherwise return false.
     */
    public Boolean isDeckEnded() {
       if(this.assistantCards.size()==0)
           return true;
       else
           return false;
    }

    /**
     *  Adds/subtract coins to the player
     * @param value is the number of coins to be added or subtracted
     */
    public void setCoin(Integer value) {
        this.coins=coins+value;
    }

    /**
     * Adds two jumps to current number of jumps possible
     */
    public void twoMoreJumps() {
        this.cardValue = cardValue+2;
    }

    /**
     * Sets the phase of the player
     * @param phase is the phase the player will be set to
     */
    public void setPlayerPhase(PlayerPhase phase) {
        this.phase=phase;
    }

    /**
     * @return the PlayerPhase of the player
     */
    public PlayerPhase getPlayerPhase() {
        return this.phase;
    }

    /**
     * Sets the player as winner
     */
    public void isWinner() {
        isWinner = true;
    }

    /**
     *
     * @return if this player is the Winner
     */
    public Boolean getIsWinner() {
        return this.isWinner;
    }

    /**
     *
     * @param wizard
     */
    public void setWizard(Wizard wizard){
        this.wizard = wizard;
    }

    /**
     * @return the id of the player
     */
    public String getId() { return this.id; }

    /**
     * @return the deck of assistant cards
     */
    public ArrayList<AssistantCard> getAssistantCard(){
        return this.assistantCards;
    }

    /**
     * Sets the assistant card to null
     */
    public void resetAssistantCard(){
        this.playedCard = null;
    }

}