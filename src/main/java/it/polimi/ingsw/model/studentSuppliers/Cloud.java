package it.polimi.ingsw.model.studentSuppliers;

import it.polimi.ingsw.model.objectTypes.FixedObjectStudent;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.utils.IdManager;

import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Cloud implements FixedObjectStudent, Serializable {

    /**
     * Default constructor
     */
    public Cloud() {
        this.id = idCounter.toString();
        IdManager.getInstance().addCloud(this);
        idCounter++;
        if (idCounter==53) {
            idCounter=50;
        }
    }

    /**
     * Stores the current available id
     */
    private static Integer idCounter = 50;

    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private LinkedList<Student> students = new LinkedList<Student>();


    /**
     * @param student
     */
    public void addStudent(Student student) {
        student.setPosition(this);
        this.students.add(student);
    }

    /**
     * @param student
     */
    public void removeStudent(Student student) {
        this.students.remove(this.students.indexOf(student));
    }

    /**
     * @return
     */
    public LinkedList<Student> getStudents() {
        return new LinkedList<>(this.students);
    }

    /**
     * @return
     */
    public Integer numOfStudents() { return this.students.size(); }

    /**
     * @return
     */
    public String getId() { return this.id; }

}