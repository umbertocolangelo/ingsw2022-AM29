package it.polimi.ingsw.Model.Player.Board;

import it.polimi.ingsw.Model.Calculations.Professor.ProfessorCalculator;
import it.polimi.ingsw.Model.Enumerations.Color;
import it.polimi.ingsw.Model.ObjectTypes.fixedObjectProfessor;
import it.polimi.ingsw.Model.ObjectTypes.fixedObjectStudent;
import it.polimi.ingsw.Model.Pawns.Professor;
import it.polimi.ingsw.Model.Pawns.Student;
import it.polimi.ingsw.Model.Player.Player;
import java.util.*;

/**
 * 
 */
public class Line implements fixedObjectStudent, fixedObjectProfessor {

    /**
     * Default constructor
     */
    public Line(Color color) {
        this.color=color;

    }
    /**
     *
     */
    private Color color;

    /**
     * 
     */
    private Student students[] = new Student[10];

    /**
     * 
     */
    private Integer studentsNum;

    /**
     * 
     */
    private Professor professor;

    /**
     * 
     */
    private Player player;

    /**
     * 
     */
    private ProfessorCalculator professorCalculator;


    /**
     * 
     */
    private void checkCoin() {
        // TODO implement here
    }

    /**
     * 
     */
    private void checkProfessor() {
        // TODO implement here
    }

    /**
     *
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * 
     */
    public void setStandardCheck() {
        // TODO implement here
    }

    /**
     * 
     */
    public void setEqualCheck() {
        // TODO implement here
    }

    /**
     * @param student
     */
    public void addStudent(Student student) {
        // TODO implement here
    }

    /**
     * @param student
     */
    public void removeStudent(Student student) {
        // TODO implement here
    }

    /**
     * @return
     */
    public LinkedList<Student> getStudents() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Integer numOfStudents() {
        // TODO implement here
        return null;
    }

    /**
     * @param professor
     */
    public void addProfessor(Professor professor) {
        // TODO implement here
    }

    /**
     * @param professor
     */
    public void removeProfessor(Professor professor) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Boolean isProfessor() {
        // TODO implement here
        return null;
    }

}