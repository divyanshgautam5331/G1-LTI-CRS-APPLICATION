package com.lt.business;

import com.lt.bean.Courses;
import com.lt.bean.Grade;
import com.lt.bean.Professor;
import com.lt.bean.Student;
import com.lt.exception.CourseNotAssignedToProfessorException;
import com.lt.exception.ProfessorNotFoundException;
import com.lt.exception.StudentNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *  Professor Interface Layer.
 */
public interface ProfessorInterface {
    
	
	
	/**
	 * this method will display Full Course List
	 * @param professorId : get courses for professor
	 * @return void
	 */
    public void viewFullCourses(long professorId) throws  CourseNotAssignedToProfessorException ;

    /**
	 * this method will add grade for the student
	 * @param grade : for adding grades pass courseId,grades etc..
	 * @return void
	 */
    public void addGrade(Grade grade) throws SQLException, StudentNotFoundException;

    /**
	 * this method will get the List of registered courses by student
	 * @param studentId :studentId for getting list of register courses
	 * @param semseterId :semesterId for getting list of register courses
	 * @return list of courses
	 */
    public List<Courses> getListOfRegCourses( long studentId,long semesterId) throws SQLException, StudentNotFoundException;

    /**
   	 * this method will get the List of registered students for professor
   	 * @param professorId :professorId for getting list of register students
	 * @return list of register students
	 */
    public List<Student> viewRegisteredStudents(long professorId) throws SQLException, StudentNotFoundException;

    /**
   	 * this method will get the professor Id
   	 * @param username : username of professor to get professor Id
   	 * @return professor
   	 */
    public Professor getProfessorId(String username) throws SQLException, ProfessorNotFoundException;
}
