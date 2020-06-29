package hr.tvz.gredisnjak.studapp;


import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name="STUDENTS")
public class Student {

    @Column(name="FIRSTNAME")
    private String name;
    @Column(name="LASTNAME")
    private String surname;
    @Column(name="DATEOFBIRTH")
    private LocalDate dateOfBirth;
    @Id
    @Column(name="JMBAG")
    private String JMBAG;
    @Column(name="NUMBEROFECTS")
    private Integer numberOfECTS;

    //@Column(name="COURSES")
    @ManyToMany(targetEntity = Course.class)
    @JoinTable(
            name = "student_course",
            joinColumns = { @JoinColumn(name = "student_jmbag")},
            inverseJoinColumns = { @JoinColumn(name = "course_id")}
    )
    private List<Course> courses;

    public Student(String name, String surname, LocalDate dateOfBirth, String JMBAG, int numberOfECTS) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.JMBAG = JMBAG;
        this.numberOfECTS = numberOfECTS;
    }
    public Student(){};
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getJmbag() {
        return JMBAG;
    }

    public void setJmbag(String JMBAG) {
        this.JMBAG = JMBAG;
    }

    public int getNumberOfECTS() {
        return numberOfECTS;
    }

    public void setNumberOfECTS(int numberOfECTS) {
        this.numberOfECTS = numberOfECTS;
    }
}
