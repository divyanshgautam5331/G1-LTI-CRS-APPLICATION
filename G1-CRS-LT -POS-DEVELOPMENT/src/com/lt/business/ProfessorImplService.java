package com.lt.business;

import com.lt.bean.*;
import com.lt.dao.ProfessorDaoImpl;
import com.lt.dao.StudentDaoImpl;
import com.lt.exception.CourseNotAssignedToProfessorException;
import com.lt.exception.CourseNotFoundException;
import com.lt.exception.GradeNotAddedException;
import com.lt.exception.ProfessorNotFoundException;
import com.lt.exception.StudentNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 *  Professor Business Layer implementing ProfessorInterface.
 */
public class ProfessorImplService extends User implements ProfessorInterface {
	 private static Logger logger = Logger.getLogger(ProfessorImplService.class);
ProfessorDaoImpl pdo = ProfessorDaoImpl.getInstance();

	/**
	 * this method will display Full Course List
	 */
	@Override
    public void viewFullCourses(long professorId) throws CourseNotAssignedToProfessorException  {
    	
        ProfessorDaoImpl pdo = new ProfessorDaoImpl();
        try
        {
            List<Courses> courseList = pdo.getCourseList(professorId);

        
        //System.out.println(courseList);
            if(!courseList.isEmpty()) {
        System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s|","-----------","-----------","---------" ,"-------")) ;
        System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s|","COURSE ID","COURSE NAME","DETAILS","FEES"));
        System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s|","-----------","-----------","---------" ,"-------"));
        for (Courses c : courseList ){
            System.out.println(String.format("|%-11s | %-11s | %-11s| %-11s ",
                    c.getCourseId(),c.getCourseName(),c.getCourseDetails(),c.getCourseFee()));
        }
        }
        }
        catch(Exception e)
        {
        	logger.error(e.getMessage());
        }
    }
	
	/**
	 * this method will add grade for the student
	 */

    @Override
    public void addGrade(Grade grade) throws SQLException,StudentNotFoundException {
        ProfessorDaoImpl pdo = new ProfessorDaoImpl();
        pdo.addGrade(grade);
    }
    
    /**
	 * this method will get the List of registered courses by student
	 */
    @Override
    public List<Courses> getListOfRegCourses( long studentId,long semesterId) throws SQLException,StudentNotFoundException{
        ProfessorDaoImpl pdo = new ProfessorDaoImpl();
        List<Courses> studentList = pdo.getListOfRegCourses(studentId,semesterId);
        return studentList;
    }
    
    
    /**
   	 * this method will get the List of registered students for professor
   	 */
    @Override
    public List<Student> viewRegisteredStudents(long professorId) throws SQLException, StudentNotFoundException {
        ProfessorDaoImpl pdo = new ProfessorDaoImpl();
        List<Student> studentList = pdo.getStudentList(professorId);
        return studentList;
    }

    /**
   	 * this method will get the professor Id
   	 */
    @Override
    public Professor getProfessorId(String username) throws SQLException,ProfessorNotFoundException {
        // pdo = new ProfessorDaoImpl();
        Professor prof = pdo.getProfessorId(username);
        return prof;
    }

}
