package it.polimi.ingsw.model.rounds;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.enumerations.AssistantCard;
import it.polimi.ingsw.model.enumerations.Color;
import it.polimi.ingsw.model.expertCards.ExpertCard;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.studentSuppliers.Cloud;

/**
 * 
 */
public interface RoundInterface {
    /**
     *
     */
    public Boolean  checkRoundEnded();

    /**
     *
     */
    private void setPianificationRound() {

    }

    /**
     *
     */
    private void setActionRound() {

    }

    /**
     * @param student
     * @return
     */
    public Boolean moveStudentIngressToHall(Student student);

    /**
     * @param student 
     * @param island 
     * @return
     */
    public Boolean moveStudentIngressToIsland(Student student, Island island);

    /**
     * @param jumps 
     * @return
     */
    public Boolean moveMotherNature(Integer jumps);

    /**
     * @param assistantCard
     * @param player
     * @return
     */
    public Boolean playAssistantCard(AssistantCard assistantCard, Player player);

    /**
     * @param expertCard
     * @return
     */
    public Boolean playExpertCard(ExpertCard expertCard);

    /**
     *
     * @param game
     * @param color
     */
    public void expertMoveStudentToBag(Game game,Color color);

    /**
     * @param student
     * @param island
     * @return
     */
    public Boolean expertStudentToIsland(Student student, Island island);

    /**
     * @param studentCard
     * @param Ingress
     * @return
     */
    public Boolean expertIngressCardSwap(Student studentCard, Student Ingress);

    /**
     *
     */
    public void finishExpertMove();


    /**
     * @param studentHall
     * @param studentIngress
     * @return
     */
    public Boolean expertIngressHallSwap(Student studentHall, Student studentIngress);

    /**
     * @param student
     * @return
     */
    public Boolean expertStudentToHall(Student student);

    /**
     * @param cloud
     * @return
     */
    public Boolean chooseCloud(Cloud cloud);

}