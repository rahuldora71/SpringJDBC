package com.spring.jdbc.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.jdbc.entities.Student;

public class StudentDaoImpl implements StudentDao {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(Student student) {
		// TODO Auto-generated method stub
		// insert Query
		String queryString = "insert into student(id,name,city) value(?,?,?)";
		int update = this.jdbcTemplate.update(queryString, student.getId(), student.getName(), student.getCity());
		return update;
	}

	@Override
	public int change(Student student) {
		// Update data
		String queryString = "update student set name=? , city=?  where id=?";
		int result = this.jdbcTemplate.update(queryString, student.getName(), student.getCity(), student.getId());

		return result;
	}

	@Override
	public int delete(Student student) {
		// Delete data
		String queryString = "delete from student where id=?";
		int result = jdbcTemplate.update(queryString, student.getId());

		return result;
	}

	// This code use extra class RowMapperImpl
	@Override
	public Student getStudent(int studentId) {
		// Selecting single student data
		String query = "Select * from student where id=?";
		RowMapper<Student> rowmaper = new RowMaperImpl();
		Student student = jdbcTemplate.queryForObject(query, rowmaper, studentId);
		return student;
	}

	// This code use annomus class for rowMapper
//	@Override
//	public Student getStudent(int studentId) {
//		//Selecting single student data
//		String query="Select * from student where id=?";
//		Student student =(Student) this.jdbcTemplate.queryForObject(query, new RowMapper(){
//
//			@Override
//			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Student student =new Student();
//				student.setId(rs.getInt(1));
//				student.setName(rs.getString(2));
//				student.setCity(rs.getString(3)); 
//				return student;
//				
//			}
//			
//		},studentId);
//		return student;
//	}
	@Override
	public List<Student> getAllStudents() {
		// Select multiple students
		String query="select * from student";
		List<Student> students=this.jdbcTemplate.query(query,new RowMaperImpl());
		return students;
	}
}
