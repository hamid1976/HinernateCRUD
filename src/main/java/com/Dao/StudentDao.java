package com.Dao; // Package names should be in lowercase

import com.Entities.Course;
import com.Entities.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentDao {

    private Session session;
    private Scanner ob;

    public StudentDao(Session session) {
        this.session = session;
        ob = new Scanner(System.in);
    }

    public List<Student> getAllStudents() {
        Criteria criteria = session.createCriteria(Student.class);
        List<Student> students = criteria.list();

        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("Student ID\tStudent Name\tRoll No");
            for (Student student : students) {
                System.out.println(student.getStudentId() + "\t\t\t" + student.getStudentName() + "\t\t\t" + student.getRollNo());
            }
        }

        return students;
    }//end getAllStudents

    public Student getStudentById() {
        System.out.println("Enter Student Id:");
        Long stId = ob.nextLong();

        Student student = (Student) session.get(Student.class, stId);

        if (student != null) {
            System.out.println("Student ID\tStudent Name\tRoll No");
            System.out.println(student.getStudentId() + "\t\t\t" + student.getStudentName() + "\t\t\t" + student.getRollNo());
            return student;
        } else {
            System.out.println("Student not found.");
            return null;
        }
    }//end getStudentById
    public void addStudent() {
        Transaction transaction = session.beginTransaction();
        try {
            System.out.println("Enter Student Name:");
            String name = ob.nextLine();
            System.out.println("Enter Roll No:");
            String rollno = ob.nextLine(); // Use nextLine() to read a full line

            System.out.println("How many Courses do you want to enroll:");
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
                Student student = new Student();
                student.setStudentName(name);
                student.setRollNo(rollno);
                student.setCourses(courses);

                session.save(student);

                for (Course course : courses) {
                    session.save(course);
                }

                // Flush the session to persist changes
                session.flush();

                System.out.println("Student Successfully Added");
            } else {
                System.out.println("No valid courses selected for enrollment.");
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Input Type Mismatch or Error");
           // transaction.rollback();
        }
    }


    public void updateStudent(){
        Transaction transaction = session.beginTransaction();
        System.out.print("Enter Student ID to update: ");
        long studentId = ob.nextLong();

        Student student = session.get(Student.class, studentId);

        if (student != null) {
            System.out.print("Enter new Student Name: ");
            ob.nextLine();
            String studentName = ob.nextLine();
            System.out.print("Enter new Roll No: ");
            String rollNo = ob.next();

            student.setStudentName(studentName);
            student.setRollNo(rollNo);

            session.update(student);
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
        transaction.commit();
    }//updateStudents

    public void deleteStudent() {
        Transaction transaction = session.beginTransaction();
        try {
            System.out.print("Enter Student ID to Delete: ");
            long studentId = ob.nextLong();

            Student student = session.get(Student.class, studentId);

            if (student != null) {
                session.delete(student);
                System.out.println("Student Deleted successfully.");

            } else {
                System.out.println("Student not found.");
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//deleteStudents

}
