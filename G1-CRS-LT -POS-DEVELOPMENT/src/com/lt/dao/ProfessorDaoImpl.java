package com.lt.dao;

import com.lt.bean.Courses;
import com.lt.bean.Grade;
import com.lt.bean.Professor;
import com.lt.bean.Student;
import com.lt.constants.SqlConstants;
import com.lt.exception.CourseNotAssignedToProfessorException;
import com.lt.exception.CourseNotFoundException;
import com.lt.exception.GradeNotAddedException;
import com.lt.exception.ProfessorNotFoundException;
import com.lt.exception.StudentNotFoundException;
import com.lt.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Implementation of Professor DAO Interface all methods to interacts with DB
 */
public class ProfessorDaoImpl implements ProfessorDaoInterface {

	private static Logger logger = Logger.getLogger(ProfessorDaoImpl.class);

	private static volatile ProfessorDaoImpl instance = null;

	public ProfessorDaoImpl() {
	}

	public static ProfessorDaoImpl getInstance() {
		if (instance == null) {
			synchronized (ProfessorDaoImpl.class) {
				instance = new ProfessorDaoImpl();
			}
		}
		return instance;
	}

	/**
	 * this method will display Full Course List
	 */
	@Override
	public List<Courses> getCourseList(long professorId) throws SQLException, CourseNotAssignedToProfessorException {

		List<Courses> list = new ArrayList<Courses>();
		Connection con = null;
		PreparedStatement ps = null;
		String sql = SqlConstants.VIEW_COURSE;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, (int) professorId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Courses cs = new Courses();
				cs.setCourseId(rs.getLong(1));
				cs.setCourseName(rs.getString(2));
				cs.setCourseFee(rs.getDouble(3));
				cs.setCourseType(rs.getString(4));
				cs.setCourseDuration(rs.getString(5));
				cs.setCourseDetails(rs.getString(6));
				list.add(cs);

			}

			if (list.isEmpty()) {

				throw new CourseNotAssignedToProfessorException(professorId);
			}
		}

		catch (CourseNotAssignedToProfessorException e) {
			logger.error(e.getMessage(professorId));
		}
		return list;
	}

	/**
	 * this method will get the List of registered courses by student
	 */
	@Override
	public List<Courses> getListOfRegCourses(long studentId, long semesterId)
			throws SQLException, StudentNotFoundException {
		List<Courses> registeredStudentList = null;
		try {
			Connection con = null;
			PreparedStatement ps = null;
			con = DBUtil.getConnection();
			registeredStudentList = new ArrayList<Courses>();
			ps = con.prepareStatement(SqlConstants.LIST_REG_COURSES_SEM);
			ps.setInt(1, (int) studentId);
			ps.setInt(2, (int) semesterId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				long courseId = rs.getLong(1);
				String courseName = rs.getString(2);
				Courses cs = new Courses(courseId, courseName);
				registeredStudentList.add(cs);
			}
			if (registeredStudentList.isEmpty()) {
				throw new StudentNotFoundException(studentId, semesterId);
			}
		} catch (StudentNotFoundException e) {
			logger.error(e.getMessage(studentId, semesterId));

		}
		return registeredStudentList;
	}

	/**
	 * this method will add grade for the student
	 */
	@Override
	public void addGrade(Grade grade) throws SQLException, StudentNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		con = DBUtil.getConnection();
		ps = con.prepareStatement(SqlConstants.ADD_GRADES);
		ps.setLong(1, grade.getCourseId());
		ps.setString(2, grade.getCourseName());
		ps.setLong(3, grade.getStudentId());
		ps.setLong(4, grade.getSemesterId());
		ps.setString(5, grade.getGrade());
		int flag = ps.executeUpdate();
		if (flag != 0)
			logger.info(" Grade added  successfully for course id : " + grade.getCourseId());
		else
			logger.error("Data not inserted.. : " + grade.getCourseId());
	}

	/**
	 * this method will get the List of registered students for professor
	 */
	@Override
	public List<Student> getStudentList(long professorId) throws SQLException, StudentNotFoundException {
		List<Student> list = new ArrayList<Student>();
		Connection con = null;
		PreparedStatement ps = null;
		String sql = SqlConstants.VIEW_REG_STUDENT;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, (int) professorId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getInt(1));
				student.setStudentName(rs.getString(2));
				student.setStudentEmail(rs.getString(3));
				student.setGender(rs.getString(4).charAt(0));
				student.setStudentDOB(rs.getDate(5));
				student.setContact_no(rs.getLong(6));
				student.setSemester_id(rs.getLong(7));
				list.add(student);
			}
			if (list.isEmpty()) {
				throw new StudentNotFoundException(professorId);
			}
		} catch (StudentNotFoundException e) {
			logger.error(e.getMessage(professorId));

		}
		return list;
	}

	/**
	 * this method will get the professorId
	 */
	@Override
	public Professor getProfessorId(String username) throws ProfessorNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		Professor prof = null;
		long prof_id = 0L;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(SqlConstants.GET_PROFESSOR_DATA);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				prof_id = (long) rs.getInt(1);
				String prof_name = (String) rs.getString(2);
				prof = new Professor(prof_id, prof_name);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new ProfessorNotFoundException(username);
		}
		return prof;
	}
}
