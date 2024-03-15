package com.Dao;

import com.Entities.Course;
import com.Entities.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class CourseDao {
    private Session session;
    private Scanner ob;

    public CourseDao(Session session) {
        this.session = session;
        ob = new Scanner(System.in);
    }

    public List<Course> getAllCourse(){
        System.out.println("-----------Course List----------------");
        Criteria criteria = session.createCriteria(Course.class);
        List<Course> course = criteria.list();

        if (course.isEmpty()) {
            System.out.println("No Course found.");
        } else {
            System.out.println("Course ID\tCourse Name\tCourseDuration");
            for (Course courses : course) {
                System.out.println(courses.getCourseId() + "\t\t\t" + courses.getCourseName()+"\t\t\t"+courses.getCourseDuration());
            }
        }
        return course;
    }//end getAllCourse

    public Course getCourseById() {
        System.out.println("Enter Course Id:");
        Long csId = ob.nextLong();

        Course course = (Course) session.get(Course.class, csId);

        if (course != null) {
            System.out.println("Course ID\tCourse Name\tCourse Duration");
            System.out.println(course.getCourseId() + "\t\t\t" + course.getCourseName() + "\t\t\t" + course.getCourseDuration());
            return course;
        } else {
            System.out.println("Course not found.");
            return null;
        }
    }

    public void addCourse() {
        Transaction transaction = session.beginTransaction();
        try {

            System.out.println("Enter Course Name:");
            String courseName = ob.nextLine();
            System.out.println("Enter Course Duration Months:");
            String courseDuration = ob.nextLine();



            Course cou = new Course();
            cou.setCourseName(courseName);
            cou.setCourseDuration(courseDuration);
            session.save(cou);

            System.out.println("Course Successfully Added");
            transaction.commit();
        } catch (Exception h) {
            System.out.println("Input Type Mismatch");
        }
    }//end addCourse

    public  void updateCourse(){
        Transaction transaction = session.beginTransaction();
        try {
            System.out.print("Enter Course ID to update: ");
            long courId = ob.nextLong();

            Course cour = session.get(Course.class, courId);

            if (cour != null) {
                System.out.println("Enter Course Name:");
                ob.nextLine();
                String courseName = ob.nextLine();

                System.out.println("Enter Course Duration Months:");
                String courseDuration = ob.nextLine();

                cour.setCourseName(courseName);
                cour.setCourseDuration(courseDuration);
                session.update(cour);

                System.out.println("Course updated successfully.");
            } else {
                System.out.println("Course not found.");
            }
            transaction.commit();
        }catch(Exception e){
            System.out.println("Input MisMatch");
        }
    }//end updateCourse


public void deleteCourse() {
    Transaction transaction = session.beginTransaction();
    try {
        System.out.print("Enter Course ID to Delete: ");
        Long courseId = ob.nextLong();

        Course course = session.get(Course.class, courseId);

        if (course != null) {
            session.delete(course);
            System.out.println("Course Deleted successfully.");
        } else {
            System.out.println("Course not found.");
        }
        transaction.commit(); // Commit the transaction after successful deletion
    } catch (Exception e) {
        e.printStackTrace();
        transaction.rollback(); // Roll back the transaction in case of an exception
    }
}


}
