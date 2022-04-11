package it.polimi.ingsw.model.islands;

import it.polimi.ingsw.model.enumerations.Color;
import it.polimi.ingsw.model.enumerations.PlayerColor;
import it.polimi.ingsw.model.pawns.Tower;

import java.util.*;

/**
 *
 */
public class IslandGroup implements IslandInterface {

    /**
     * Default constructor
     */
    public IslandGroup() {

    }

    /**
     *
     */
    private boolean isDenied;

    /**
     *
     */
    private String id;

    /**
     * linked list of island in the same group
     */
    private LinkedList<Island> islandGroup = new LinkedList<Island>();

    /**
     * @param newIsland indicates the new Island element of this islandGroup
     */
    public void addIsland(Island newIsland) {
        islandGroup.add(newIsland);
    }

    /**
     * @param newIslandGroup indicates the new Island elements (originally in newIslandGroup) of this islandGroup
     */
    public void addIslandGroup(IslandGroup newIslandGroup) {
        for (Island island: newIslandGroup.getIslandGroupElement()) {
            islandGroup.add(island);
        }
    }

    /**
     * @param color of student
     * @return this color number of students on the group
     */
    public Integer numOfStudents(Color color) {
        int total = 0;
        for (int i=0; i< islandGroup.size(); i++) {
            total += islandGroup.get(i).numOfStudents(color);
        }
        return total;
    }

    /**
     * set isDenied
     */
    public void setDeny() {
        this.isDenied = true;
    }

    /**
     * @return this.isDenied
     */
    public boolean getDeny() { return this.isDenied; }

    /**
     *
     */
    public void removeDeny() {
        this.isDenied = false;
    }

    /**
     * @return towers number of the group
     */
    public Integer numOfTowers() { return this.islandGroup.size(); }

    /**
     * @return
     */
    public PlayerColor getInfluenceColor() {
        return this.islandGroup.get(0).getInfluenceColor();
    }

    /**
     * @return copy of islandGroup linkedlist
     */
    public LinkedList<Island> getIslandGroupElement() {
        return new LinkedList<>(this.islandGroup);
    }

    /**
     * @return LinkedList<Tower>        Return the LinkedList of every Towers in this islandGroup
     */
    public LinkedList<Tower> getTowers() {
        LinkedList<Tower> towerList = new LinkedList<>();
        for (Island island: islandGroup) {
            towerList.add(island.getTowers().get(0));
        }
        return towerList;
    }

    /**
     * @param newSet indicates new isGrouped status
     */
    public void setIsGrouped (boolean newSet) {
        //da modificare: caso in cui si separano (impossibile)
    }

    /**
     * @return
     */
    public Boolean isGrouped() { return true; }

    public Integer getSize() { return this.islandGroup.size(); }

}
