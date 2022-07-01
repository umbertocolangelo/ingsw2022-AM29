package it.polimi.ingsw.model.expertCards.deck;

import it.polimi.ingsw.model.expertCards.CardManager;
import it.polimi.ingsw.model.expertCards.ExpertCard;
import it.polimi.ingsw.model.objectTypes.FixedObjectStudent;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.utils.IdManager;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * card #7
 */
public class IngressCardSwapCard implements ExpertCard, FixedObjectStudent, Serializable {

    /**
     * Default constructor
     */
    public IngressCardSwapCard(CardManager cardManager) {
        this.id = "44";
        this.manager=cardManager;
        IdManager.getInstance().addExpertCard(this);
        students = new LinkedList<Student>();
        for (int i=0; i<7; i++) {
            addStudent(manager.getBag().newStudent());
        }
    }

    /**
     * Keep the reference to the id
     */
    private String id;

    /**
     *
     */
    private Integer cost = 1;

    /**
     *
     */
    private LinkedList<Student> students;

    /**
     *
     */
    private CardManager manager;

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
     * @return
     */
    @Override
    public String getId() {
        return this.id;
    }


    /**
     * @param student
     */
    public void addStudent(Student student) {
        if (!this.students.contains(student)) {
            if(student.getPosition()!=null){        // If the student was on a FixedObject, this object is updated
                FixedObjectStudent position = (FixedObjectStudent) student.getPosition();
                position.removeStudent(student);
            }
            student.setPosition(this);
            this.students.add(student);
        }
    }

    /**
     * @param student
     */
    public void removeStudent(Student student) {
        this.students.remove(student);
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
    public Integer numOfStudents() {
        return this.students.size();
    }

}