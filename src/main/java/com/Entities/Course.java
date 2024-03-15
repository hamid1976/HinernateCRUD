package com.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String courseName;
    private String courseDuration;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Student> students = new ArrayList<>();

    @ManyToMany(mappedBy = "courses",cascade=CascadeType.ALL)
    private List<Teacher> teachers = new ArrayList<>();


    public Course(){
        super();
    }

    public Course(Long courseId, String courseName, String courseDuration, List<Student> students, List<Teacher> teachers) {
        super();
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.students = students;
        this.teachers = teachers;
    }

    public Long getCourseId() {

        return courseId;
    }

    public void setCourseId(Long courseId) {

        this.courseId = courseId;
    }

    public String getCourseName() {

        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDuration() {
        return courseDuration;
    }
    public void setCourseDuration(String courseDuration) {

        this.courseDuration = courseDuration;
    }

    public List<Student> getStudents() {

        return students;
    }

    public void setStudents(List<Student> students) {

        this.students = students;
    }

    public List<Teacher> getTeachers() {

        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {

        this.teachers = teachers;
    }
}
//
//package com.Entities;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "courses")
//public class Course {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long courseId;
//    private String courseName;
//    private String courseDuration;
//
//    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "courses")
//    private List<Student> students = new ArrayList<>();
//
//    @ManyToMany(mappedBy = "courses")
//    private List<Teacher> teachers = new ArrayList<>();
//
//    public Course() {
//        super();
//    }
//
//    public Course(Long courseId, String courseName, String courseDuration, List<Student> students, List<Teacher> teachers) {
//        super();
//        this.courseId = courseId;
//        this.courseName = courseName;
//        this.courseDuration = courseDuration;
//        this.students = students;
//        this.teachers = teachers;
//    }
//
//    public Long getCourseId() {
//        return courseId;
//    }
//
//    public void setCourseId(Long courseId) {
//        this.courseId = courseId;
//    }
//
//    public String getCourseName() {
//        return courseName;
//    }
//
//    public void setCourseName(String courseName) {
//        this.courseName = courseName;
//    }
//
//    public String getCourseDuration() {
//        return courseDuration;
//    }
//
//    public void setCourseDuration(String courseDuration) {
//        this.courseDuration = courseDuration;
//    }
//
//    public List<Student> getStudents() {
//        return students;
//    }
//
//    public void setStudents(List<Student> students) {
//        this.students = students;
//    }
//
//    public List<Teacher> getTeachers() {
//        return teachers;
//    }
//
//    public void setTeachers(List<Teacher> teachers) {
//        this.teachers = teachers;
//    }
//}
