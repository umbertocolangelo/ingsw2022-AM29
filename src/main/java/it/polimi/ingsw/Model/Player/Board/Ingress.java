package it.polimi.ingsw.Model.Player.Board;

import it.polimi.ingsw.Model.ObjectTypes.FixedObjectStudent;
import it.polimi.ingsw.Model.Pawns.Student;
import java.util.*;

/**
 * 
 */
public class Ingress implements FixedObjectStudent {

    /**
     * Default constructor
     */
    public Ingress() {

    }

    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private Integer maxStudentsNum;

    /**
     * 
     */
    private Boolean isFourPlayers=false;

    /**
     * 
     */
    private LinkedList<Student> students=new LinkedList<>();

    /**
     * @return LinkedList<Student>      Return a LinkedList of students that are in this ingress
     */
    public LinkedList<Student> getStudents() {

        return new LinkedList<>(this.students);
    }

    /**
     * @param student       Remove the student from the Ingress
     */
    public void removeStudent(Student student) {
        if(this.students.contains(student)){
            //remove il fixed object
            student.setPosition(this);
            students.remove(students.indexOf(student));
        }
    }

    /**
     * @param student       Add the student to the ingress
     */
    public void addStudent(Student student) {

        this.students.add(student);
    }

    /**
     * @return  Integer     Return the number of the students
     */
    public Integer numOfStudents() {

        return this.students.size();
    }

}