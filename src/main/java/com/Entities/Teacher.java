package com.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;
    private String teacherName;

    @ManyToMany(cascade =CascadeType.DETACH)
    @JoinTable(name = "teacher_courses",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses = new ArrayList<>();

    public  Teacher(){
        super();
    }
    public Teacher(Long teacherId, String teacherName, List<Course> courses) {
        super();
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.courses = courses;
    }

    public Long getTeacherId() {

        return teacherId;
    }

    public void setTeacherId(Long teacherId) {

        this.teacherId = teacherId;
    }

    public String getTeacherName() {

        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<Course> getCourses() {

        return courses;
    }

    public void setCourses(List<Course> courses) {

        this.courses = courses;
    }
}
