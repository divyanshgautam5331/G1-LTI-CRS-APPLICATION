package com.lt.business;

import com.lt.bean.Courses;
import com.lt.bean.Professor;
import com.lt.bean.Student;
import com.lt.dao.AdminDaoImpl;
import com.lt.dao.AdminDaoInterface;
import com.lt.exception.CourseExistedException;
import com.lt.exception.StudentDetailsNotFoundException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *  Admin Business Layer implementing admin DAO.
 */
public class AdminImplService  implements AdminDaoInterface {
	
    private static Logger logger = Logger.getLogger(AdminImplService.class);
    AdminDaoImpl adminDao = AdminDaoImpl.getInstance();
    boolean flag = false;


    /**
     *  this method is used to add professor by admin
     */
    @Override
    public void addProfessor(Professor professor) throws SQLException {
        adminDao.addProfessor(professor);

    }

    /**
     *  this method is used to approve student by admin
     */
    @Override
    public void approveStudent(int studentId) throws SQLException, StudentDetailsNotFoundException {
    adminDao.approveStudent(studentId);
    }

    
    /**
     *  this method shows list of pending students to admin
     */
    @Override
    public List<Student> showListOfPendingStudent() throws SQLException {

        List<Student> pendingStudent = adminDao.showListOfPendingStudent();
        return pendingStudent;
    }

    /**
     *  this method will generate report card
     */
    @Override
    public void generateReportCard() throws SQLException{
        adminDao.generateReportCard();

    }
    
    /**
     *  this method will add course
     */
    @Override
    public void addCourse(Courses course) throws SQLException {
       try{
           adminDao.addCourse(course);
       }catch (CourseExistedException e)
       {
           logger.error(e.getMsg(course.getCourseId()));
       }

    }
    
    /**
     *  this method will delete course
     */
    @Override
    public void deleteCourse(long courseId,List<Courses> coursesList) throws IOException, SQLException {
        adminDao.deleteCourse(courseId,coursesList);


    }

    
    /**
     *  this method will view all admin courses
     */
    @Override
    public List<Courses> adminViewAllCourses() throws SQLException {

        List<Courses> viewAllCourses = adminDao.adminViewAllCourses();

        return viewAllCourses;
    }






}
