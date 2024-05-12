package com.spring.jdbc;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.jdbc.dao.StudentDao;
import com.spring.jdbc.dao.StudentDaoImpl;
import com.spring.jdbc.entities.Student;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		System.out.println("Program Started");
//        Spring JDBC
		ApplicationContext context = new ClassPathXmlApplicationContext("com/spring/jdbc/config.xml");
		StudentDao dao = context.getBean("studentDao", StudentDao.class);
		
		// insert data
		
        Student student1=new Student();
        student1.setId(333);
        student1.setName("Ramesh");
        student1.setCity("Karnal");
        int result=dao.insert(student1);
        System.out.println("student added"+result);
		
		//updATE dATA

		Student student2 = new Student();
		student2.setId(333);
		student2.setName("Rajbir");
		student2.setCity("Hissar");
		int result2=dao.change(student2);
		
		System.out.println("data updated = "+result);

		
		//Delete data via id
		Student student3 = new Student();
		student3.setId(333);
		int result3=dao.delete(student3);
		System.out.println("data deleted = "+result);
		
		//Fetching data of single student using id 
		Student student=dao.getStudent(222);
		System.out.println(student);
		
		// Fetching data of all students
		List<Student> students=dao.getAllStudents();
		for (Student student4 : students) {
			System.out.println(student4);
		}
		
	}
}
