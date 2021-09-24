package com.lt.business;

import com.lt.bean.*;
import com.lt.dao.StudentDaoImpl;
import com.lt.dao.StudentDaoInterface;
import com.lt.exception.CourseNotFoundException;

import java.sql.SQLException;
import java.util.*;
//import static com.lt.dao.StudentDao.studentsList;


/**
 *  Student Business Layer implementing student DAO.
 */
public class StudentImplService implements StudentDaoInterface {

    StudentDaoImpl studentDao = StudentDaoImpl.getInstance();
    boolean flag = false;


    /**
     * this method will do Student Sign up
     */
    @Override
    public boolean signUp(Student student) throws SQLException {
        //studentDao = new StudentDaoImpl();
        flag = studentDao.signUp(student);
        return flag;

    }


    /**
     * this method will allow student to register course
     */
    @Override
    public boolean registerForCourse(long student_id, long semesterId, long courseId) throws SQLException {
        //  studentDao = new StudentDaoImpl();
        flag = studentDao.registerForCourse(student_id, semesterId, courseId);
        return flag;
    }


    /**
     * this method show list of registered courses by student
     */
    @Override
    public Set<RegisterCourse> viewRegisteredCourses(long studentId, long semesterId) throws SQLException {
        // studentDao = new StudentDaoImpl();
        Set<RegisterCourse> registeredList = studentDao.viewRegisteredCourses(studentId, semesterId);
        return registeredList;
    }

    /**
     * this method will remove registered courses by student
     */
    @Override
    public boolean removeCourse(long courseId) throws SQLException {
        return studentDao.removeCourse(courseId);
    }

    /**
     * this method will get student ID 
     */
    @Override
    public Student getStudent(String username) throws SQLException {
        // studentDao = new StudentDaoImpl();
        Student st = studentDao.getStudent(username);
        return st;
    }

    /**
     * this method will show list of available courses
     */
    @Override
    public List<Courses> showAvailableCourses(long semesterId) throws SQLException {
        List<Courses> list = null;
        // studentDao = new StudentDaoImpl();
        list = studentDao.showAvailableCourses(semesterId);
        return list;
    }

    /**
     * this method will show list of registered courses with pending payment status
     */
    @Override
    public Set<RegisterCourse> showListofPendingPayment(long student_id) throws SQLException {
        Set<RegisterCourse> list = new HashSet<RegisterCourse>();
        //  studentDao = new StudentDaoImpl();
        list = studentDao.showListofPendingPayment(student_id);
        return list;
    }

    /**
     * this method will allow you to payfees
     */
    @Override
    public boolean payfees(long courseId, Payment payment, long studentId) throws SQLException {
        //  studentDao = new StudentDaoImpl();
        return studentDao.payfees(courseId, payment, studentId);
    }

    /**
     * this method will get the list of gradecard for the students
     */
    @Override
    public List<GradeCard> viewGradeCard(long semesterId, long studentId) throws SQLException {
        //  studentDao = new StudentDaoImpl();
        List<GradeCard> gradecard = studentDao.viewGradeCard(semesterId, studentId);
        return gradecard;
    }

    /**
     * this method will dipsplay  payfees card
     */
    @Override
    public boolean payfeesCard(long courseId, Payment payment, long studentId) throws SQLException {
        return studentDao.payfeesCard(courseId, payment, studentId);
    }


    /**
     * this method will generate transaction id
     */
    public String generateTransactionId() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%05d", number);
    }

    /**
     * this method will validate card details
     */
    public static boolean validateCard(String card) {
        if (card.length() == 16)
            return true;
        return false;
    }
    
    
    /**
     * this method will check register course id with list of course id's
     */
    public boolean checkId(long id,Set<RegisterCourse> list ){
        for (RegisterCourse c :list) {
            if(c.getCourseId() == id)
                return true;
        }
        return false;
    }

}
