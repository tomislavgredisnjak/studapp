package hr.tvz.gredisnjak.studapp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="COURSES")
public class Course implements Serializable {

    private static final long serialVersionUID= -8633422296916401216L;

    @Id
    @Column(name="COURSE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @Column(name="COURSE_NAME")
    private String name;
    @Column(name="COURSE_ECTS")
    private Integer numberOfECTS;

    @ManyToMany(targetEntity = Student.class, mappedBy = "courses")
    private List<Student> students;

    public Course(Integer ID, String name, Integer numberOfECTS) {
        this.ID = ID;
        this.name = name;
        this.numberOfECTS = numberOfECTS;
    }

    public Course(){
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfECTS() {
        return numberOfECTS;
    }

    public void setNumberOfECTS(Integer numberOfECTS) {
        this.numberOfECTS = numberOfECTS;
    }

    public List<Student> getStudents() {
        return students;
    }
}