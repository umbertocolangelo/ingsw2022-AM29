package it.polimi.ingsw.message;

import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public class RoundEnd implements Serializable,MessageMethod {
    /**
     * @param game
     */
    @Override
    public void apply(Game game) {
        game.finishExpertMove();
    }
}