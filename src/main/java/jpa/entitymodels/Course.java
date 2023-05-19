package jpa.entitymodels;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Table(name = "Course")
public class Course {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cId;

    @Column(name = "name", nullable = false, length = 50)
    private String cName;

    @Column(name = "instructor", nullable = false, length = 50)
    private String cInstructorName;

    public Course() {
        // Default constructor
    }

    public Course(String cName, String cInstructorName) {
        this.cName = cName;
        this.cInstructorName = cInstructorName;
    }

    // Getters and Setters

    public int getCId() {
        return cId;
    }

    public void setCId(int cId) {
        this.cId = cId;
    }

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }

    public String getCInstructorName() {
        return cInstructorName;
    }

    public void setCInstructorName(String cInstructorName) {
        this.cInstructorName = cInstructorName;
    }

}
