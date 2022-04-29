package it.polimi.ingsw.model.expertCards.deck;

import it.polimi.ingsw.model.expertCards.CardManager;
import it.polimi.ingsw.model.expertCards.ExpertCard;

/**
 * card #12
 */
public class HallBagSwapCard implements ExpertCard {

    /**
     * Default constructor
     */
    public HallBagSwapCard() {
        this.id = "49";
    }

    /**
     *
     */
    private Integer cost = 3;

    /**
     * 
     */
    private String id;

    /**
     *
     */
    public void apply() {
        incrementCost();
    }

    /**
     * @return
     */
    public Integer getCost() {
        return this.cost;
    }

    /**
     * 
     */
    private void incrementCost() {
        cost = cost + 1;
    }

    /**
     *
     * @return
     */
    @Override
    public String getId() {
        return this.id;
    }

}