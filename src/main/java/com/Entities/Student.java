package com.Entities;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private String studentName;
    private String rollNo;

    @ManyToMany(cascade =CascadeType.DETACH)
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;


    public Student(){
        super();
    }
    public Student(Long studentId, String studentName, String rollNo, List<Course> courses) {
        super();
        this.studentId = studentId;
        this.studentName = studentName;
        this.rollNo = rollNo;
        this.courses = courses;
    }

    public Long getStudentId() {

        return studentId;
    }

    public void setStudentId(Long studentId) {

        this.studentId = studentId;
    }

    public String getStudentName() {

        return studentName;
    }

    public void setStudentName(String studentName) {

        this.studentName = studentName;
    }

    public String getRollNo() {

        return rollNo;
    }

    public void setRollNo(String rollNo) {

        this.rollNo = rollNo;
    }

    public List<Course> getCourses() {

        return courses;
    }

    public void setCourses(List<Course> courses) {

        this.courses = courses;
    }
    public String toString() {

        return studentId + "\t\t" + studentName + "\t\t" + rollNo;
    }
}
