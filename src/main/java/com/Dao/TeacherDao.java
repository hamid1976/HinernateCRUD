package com.Dao;

import com.Entities.Course;
import com.Entities.Student;
import com.Entities.Teacher;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeacherDao {
    private Session session;
    private Scanner ob;

    public TeacherDao(Session session) {
        this.session = session;
        ob = new Scanner(System.in);
    }

    public List<Teacher> getAllTeacher(){
        Criteria criteria = session.createCriteria(Teacher.class);
        List<Teacher> teachers = criteria.list();

        if (teachers.isEmpty()) {
            System.out.println("No Teacher found.");
        } else {
            System.out.println("Teacher ID\tTeacher Name");
            for (Teacher teacher : teachers) {
                System.out.println(teacher.getTeacherId() + "\t\t\t" + teacher.getTeacherName());
            }
        }
        return teachers;
    }//end getAllTeacher

    public Teacher getTeacherById() {
        System.out.println("Enter Teacher Id:");
        Long thId = ob.nextLong();

        Teacher teacher = (Teacher) session.get(Teacher.class, thId);

        if (teacher != null) {
            System.out.println("Teacher ID\tTeacher Name");
            System.out.println(teacher.getTeacherId() + "\t\t\t" + teacher.getTeacherName());
            return teacher;
        } else {
            System.out.println("Teacher not found.");
            return null;
        }
    }

    public void addTeacher(){
        Transaction transaction = session.beginTransaction();
        try {
            System.out.println("Enter Teacher Name:");
            String teacherName = ob.nextLine();

            System.out.println("How many Courses do you want to Teach:");
            int numberOfCourses = ob.nextInt();

            List<Course> courses = new ArrayList<>();

            for (int i = 0; i < numberOfCourses; i++) {
                System.out.println("Enter Course ID for course " + (i + 1) + ":");
                Long courseId = ob.nextLong();
                Course course = (Course) session.get(Course.class, courseId);

                if (course != null) {
                    courses.add(course);
                } else {
                    System.out.println("Course with ID " + courseId + " not found.");
                }
            }
            if (!courses.isEmpty()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherName(teacherName);
                teacher.setCourses(courses);

                session.save(teacher);

                for (Course course : courses) {
                    session.save(course);
                }

                // Flush the session to persist changes
                session.flush();

                System.out.println("Teacher Successfully Added");
            } else {
                System.out.println("No valid courses selected for enrollment.");
            }
            transaction.commit();
        } catch (Exception j) {
            j.printStackTrace();
            System.out.println("Input Type Mismatch");
        }
    }//end addTeacher

    public void updateTeacher(){
        Transaction transaction = session.beginTransaction();
        try {
            System.out.print("Enter Teacher ID to update: ");
            long techId = ob.nextLong();

            Teacher tech = session.get(Teacher.class, techId);

            if (tech != null) {
                System.out.print("Enter new Teacher Name: ");
                ob.nextLine();
                String teacherName = ob.nextLine();

                tech.setTeacherName(teacherName);
                session.update(tech);
                System.out.println("Teacher updated successfully.");
            } else {
                System.out.println("Teacher not found.");
            }
            transaction.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }//end updateTeacher

    public void deleteTeacher(){
        Transaction transaction = session.beginTransaction();
        try {
            System.out.print("Enter Teacher ID to Delete: ");
            long teacherId = ob.nextLong();

            Teacher teach = session.get(Teacher.class, teacherId);

            if (teach != null) {
                session.delete(teach);
                System.out.println("Teacher Deleted successfully.");
            } else {
                System.out.println("Teacher not found.");
            }
            transaction.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }//deleteTeacher
}
