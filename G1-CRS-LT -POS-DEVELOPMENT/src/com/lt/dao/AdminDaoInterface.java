package com.lt.dao;

import com.lt.bean.Courses;
import com.lt.bean.Professor;
import com.lt.bean.Student;
import com.lt.exception.CourseExistedException;
import com.lt.exception.StudentDetailsNotFoundException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *  Admin DAO Interface
 */
public interface AdminDaoInterface {

    /**
     * Method to add professor to database
     *
     * @param professor:   for which course
     * @return void and print message
     */
    public void addProfessor(Professor professor) throws SQLException;

    /**
     * Method to add student after approval to database
     *
     * @param studentId:   for which course
     * @return void and print message
     */
    public void approveStudent(int studentId) throws SQLException, StudentDetailsNotFoundException;


    /**
     * Method to show list of students pending for  approval from database
     *
     * @return void and print message
     */
    public List<Student> showListOfPendingStudent() throws SQLException;


    /**
     * Method to generate report card from database
     *
     * @return void and print message
     */
    public void generateReportCard() throws SQLException;

    /**
     * Method to add course to database
     *
     * @param course: Course for which display list of students
     * @return void and print message
     */
    public void addCourse(Courses course) throws SQLException, CourseExistedException, CourseExistedException;

    /**
     * Method to delete course to database
     *
     * @param courseId: Course for which display list of students
     * @return void and print message
     */
    public void deleteCourse(long courseId,List<Courses> coursesList) throws IOException, SQLException;

    /**
     * Method to show list of available course to  admin from database
     *
     * @return List of available courses
     */
    public List<Courses> adminViewAllCourses() throws SQLException;




}
