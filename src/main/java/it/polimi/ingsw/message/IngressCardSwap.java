package it.polimi.ingsw.message;

import it.polimi.ingsw.model.Game;

import java.io.Serializable;

public class IngressCardSwap implements Serializable,MessageMethod {
    /**
     *
     */
    private String studentCard;

    /**
     *
     */
    private String studentHall;


    /**
     * @param game
     */
    @Override
    public void apply(Game game) {
        game.expertIngressCardSwap( studentCard,studentHall);

    }

    public void setStudentCard(String student){
        this.studentCard=student;
    }

    public void setStudentIngress(String studentHall){
        this.studentHall=studentHall;
    }
}