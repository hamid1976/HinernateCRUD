package com.Dao;

import com.helper.ConnectionProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.awt.image.Kernel;
import java.util.Scanner;

public class HibernateDemo {
    public static void main(String[] args) {


        Session session= ConnectionProvider.getSession();


        Scanner scanner = new Scanner(System.in);

        char ch = 'T';
        do {
           // Transaction tx = session.beginTransaction();
            try {
                System.out.println("\n1. Student\n2. Teacher\n3. Course\n4. Exit\nSelection:");
                int mainSel = scanner.nextInt();

                if (mainSel == 1) {
                    char d = 'T';
                    do {
                        try {
                            System.out.println("\n1. Students List\n2. GetStudentById \n3. Add Student\n4. Update Student\n5. Delete Student\n6. Back\nEnter Selection");
                            int stdsel = scanner.nextInt();
                            StudentDao dao = new StudentDao(session);

                            switch (stdsel) {
                                case 1:
                                    System.out.println("------------Student List---------------\n");
                                    dao.getAllStudents();
                                    break;
                                case 2:
                                    System.out.println("------------Get Course By Id---------------");
                                    dao.getStudentById();

                                    break;
                                case 3:
                                    System.out.println("------------Add Student-----------------\n");
                                    dao.addStudent();
                                    break;
                                case 4:
                                    System.out.println("------------UPDATE Student-----------------\n");
                                    dao.updateStudent();
                                    break;
                                case 5:
                                    System.out.println("------------Delete Student---------------");
                                    dao.deleteStudent();
                                    break;

                                case 6:
                                    d = 'F';
                                    break;
                                default:
                                    System.out.println("Invalid Selection");
                                    break;
                            }
                        }catch(Exception k){
                            k.printStackTrace();
                            System.out.println("Input Mismatch. Please enter a valid numeric choice. ");
                            scanner.nextLine(); // Consume the invalid input
                        }
                    } while (d == 'T');
                } // end Student

                if (mainSel == 2) {
                    char e = 'T';
                    do {
                        try {
                            System.out.println("\n1. Teachers List\n2. GetTeacherById\n3. Add Teacher\n4. Update Teacher\n5. Delete Teacher\n6. Back\nEnter Selection");
                            int tecSel = scanner.nextInt();

                            TeacherDao tdao = new TeacherDao(session);

                            switch (tecSel) {
                                case 1:
                                    System.out.println("---------------Teacher List-------------");
                                  tdao.getAllTeacher();
                                    break;
                                case 2:
                                    System.out.println("---------------GetTeacherById---------------\n");
                                    tdao.getTeacherById();
                                    break;
                                case 3:
                                    System.out.println("---------------Add Teacher---------------\n");

                                    tdao.addTeacher();
                                    //    tx.commit();
                                    break;
                                case 4:
                                    System.out.println("---------------Update Teacher-------------\n");

                                    tdao.updateTeacher();
                                 //   tx.commit();
                                    break;
                                case 5:
                                    System.out.println("----------------Delete Teacher-------------\n");

                                    tdao.deleteTeacher();
                                    //tx.commit();
                                    break;
                                case 6:
                                    e = 'F';
                                    break;
                                default:
                                    System.out.println("Invalid Selection");
                                    break;
                            }
                        }catch (Exception m){
                            System.out.println("Input Mismatch. Please enter a valid numeric choice.");
                            scanner.nextLine(); // Consume the invalid input
                        }
                    } while (e == 'T');
                } // end Teacher

                if (mainSel == 3) {
                    char f = 'T';
                    do {
                        try {
                            System.out.println("\n1. Courses List\n2. GetCourseById\n3. Add Course\n4. Update Course\n5. Delete Course\n6. Back\nEnter Selection");
                            int crssel = scanner.nextInt();
                            CourseDao cDao = new CourseDao(session);
                            switch (crssel) {
                                case 1:
                                    cDao.getAllCourse();
                                    break;
                                case 2:
                                    System.out.println("-----------Get Course By Id------------------");
                                    cDao.getCourseById();
                                    break;
                                case 3:
                                    System.out.println("-----------Add Course------------------");
                                    cDao.addCourse();
                                    break;
                                case 4:
                                    System.out.println("------------Update Course---------------");
                                    cDao.updateCourse();
                                    break;
                                case 5:
                                    System.out.println("------------Delete Course----------------");
                                    cDao.deleteCourse();
                                    break;
                                case 6:
                                    f = 'F';
                                    break;
                                default:
                                    System.out.println("Invalid Selection");
                                    break;
                            }
                        }catch (Exception h){
                            System.out.println("Input Mismatch. Please enter a valid numeric Number COURSE");
                            scanner.nextLine(); // Consume the invalid input
                        }
                    } while (f == 'T');
                } // end Course

                if (mainSel == 4) {
                    System.out.println("Goodbye");
                    session.close();
                    System.exit(0);
                }
               // tx.commit();

            } catch (java.util.InputMismatchException e) {
                System.out.println("Input Mismatch. Please enter a valid numeric choice. main");
                scanner.nextLine(); // Consume the invalid input
            }finally {

//                    if (tx != null && tx.isActive()) {
//                        tx.rollback(); // Rollback the transaction if it's still active
//                    }
                    System.out.println("Program Working");
                }

            } while (ch == 'T');
    }
}
//
//package com.Dao;
//
//import com.helper.ConnectionProvider;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import java.util.Scanner;
//
//public class HibernateDemo {
//    public static void main(String[] args) {
//        Session session = ConnectionProvider.getSession();
//        Scanner scanner = new Scanner(System.in);
//
//        char ch = 'T';
//        do {
//            Transaction tx = session.beginTransaction();
//            try {
//                System.out.println("\n1. Student\n2. Teacher\n3. Course\n4. Exit\nSelection:");
//                int mainSel = scanner.nextInt();
//
//                switch (mainSel) {
//                    case 1:
//                        handleStudentMenu(session, scanner);
//                        break;
//                    case 2:
//                        handleTeacherMenu(session, scanner);
//                        break;
//                    case 3:
//                        handleCourseMenu(session, scanner);
//                        break;
//                    case 4:
//                        System.out.println("Goodbye");
//                        session.close();
//                        System.exit(0);
//                        break;
//                    default:
//                        System.out.println("Invalid Selection");
//                        break;
//                }
//
//                tx.commit();
//
//            } catch (java.util.InputMismatchException e) {
//                System.out.println("Input Mismatch. Please enter a valid numeric choice.");
//                scanner.nextLine(); // Consume the invalid input
//            } finally {
//                System.out.println("Program Working");
//            }
//        } while (ch == 'T');
//    }
//
//    private static void handleStudentMenu(Session session, Scanner scanner) {
//        char d = 'T';
//        do {
//            try {
//                System.out.println("\n1. Students List\n2. Add Student\n3. Update Student\n4. Delete Student\n5. Back\nEnter Selection");
//                int stdsel = scanner.nextInt();
//                StudentDao dao = new StudentDao(session);
//
//                switch (stdsel) {
//                    case 1:
//                        System.out.println("------------Student List---------------\n");
//                        dao.getAllStudents();
//                        break;
//                    case 2:
//                        System.out.println("------------Add Student-----------------\n");
//                        dao.addStudent();
//                        break;
//                    case 3:
//                        System.out.println("------------UPDATE Student-----------------\n");
//                        dao.updateStudent();
//                        break;
//                    case 4:
//                        System.out.println("------------Delete Student---------------");
//                        dao.deleteStudent();
//                        break;
//                    case 5:
//                        d = 'F';
//                        break;
//                    default:
//                        System.out.println("Invalid Selection");
//                        break;
//                }
//            } catch (Exception k) {
//                System.out.println("Input Mismatch. Please enter a valid numeric choice.");
//                scanner.nextLine(); // Consume the invalid input
//            }
//        } while (d == 'T');
//    }
//
//    private static void handleTeacherMenu(Session session, Scanner scanner) {
//        char e = 'T';
//        do {
//            try {
//                System.out.println("\n1. Teachers List\n2. Add Teacher\n3. Update Teacher\n4. Delete Teacher\n5. Back\nEnter Selection");
//                int tecSel = scanner.nextInt();
//
//                TeacherDao tdao = new TeacherDao(session);
//
//                switch (tecSel) {
//                    case 1:
//                        System.out.println("---------------Teacher List-------------");
//                        tdao.getAllTeacher();
//                        break;
//                    case 2:
//                        System.out.println("---------------Add Teacher---------------\n");
//                        tdao.addTeacher();
//                        break;
//                    case 3:
//                        System.out.println("---------------Update Teacher-------------\n");
//                        tdao.updateTeacher();
//                        break;
//                    case 4:
//                        System.out.println("----------------Delete Teacher-------------\n");
//                        tdao.deleteTeacher();
//                        break;
//                    case 5:
//                        e = 'F';
//                        break;
//                    default:
//                        System.out.println("Invalid Selection");
//                        break;
//                }
//            } catch (Exception m) {
//                System.out.println("Input Mismatch. Please enter a valid numeric choice.");
//                scanner.nextLine(); // Consume the invalid input
//            }
//        } while (e == 'T');
//    }
//
//    private static void handleCourseMenu(Session session, Scanner scanner) {
//        char f = 'T';
//        do {
//            try {
//                System.out.println("\n1. Courses List\n2. Add Course\n3. Update Course\n4. Delete Course\n5. Back\nEnter Selection");
//                int crssel = scanner.nextInt();
//                CourseDao cDao = new CourseDao(session);
//
//                switch (crssel) {
//                    case 1:
//                        cDao.getAllCourse();
//                        break;
//                    case 2:
//                        System.out.println("-----------Add Course------------------");
//                        cDao.addCourse();
//                        break;
//                    case 3:
//                        System.out.println("------------Update Course---------------");
//                        cDao.updateCourse();
//                        break;
//                    case 4:
//                        System.out.println("------------Delete Course----------------");
//                        cDao.deleteCourse();
//                        break;
//                    case 5:
//                        f = 'F';
//                        break;
//                    default:
//                        System.out.println("Invalid Selection");
//                        break;
//                }
//            } catch (Exception h) {
//                System.out.println("Input Mismatch. Please enter a valid numeric choice.");
//                scanner.nextLine(); // Consume the invalid input
//            }
//        } while (f == 'T');
//    }
//}
