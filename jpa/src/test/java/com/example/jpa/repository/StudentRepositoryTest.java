package com.example.jpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Guardian;
import com.example.jpa.entity.Student;

@SpringBootTest  // Using this to check data reflection in DB
//@DataJpaTest  // ideally we shd be using datajpa test for repository - tests and flushes data so that DB doesn't impact
class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepo;
	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void saveStudent() {
		//use builder pattern to create an instance/object/record of student
		Student student = Student.builder()
				.firstName("neha")
				.lastName("Palamala")
				.emailId("neha@gmail.com")
				//.guardianName("latha")
				//.guardianMobile("999999999")
				//.guardianEmail("latha@gmail.com")
				.build();
		this.studentRepo.save(student);
	}
	
	@Test
	public void saveStudentWithGuardian() {
		
		Guardian guardian = Guardian.builder()
				.email("bhargavi@gmail.com")
				.name("bhargavi")
				.mobile("8888888888")
				.build();
		
		Student student = Student.builder()
				.firstName("neha")
				.lastName("Palamala")
				.emailId("test@gmail.com")
				.guardian(guardian)
				.build();
		this.studentRepo.save(student);
	}
	
	@Test
	public void printAllStudents() {
		List<Student> students =this.studentRepo.findAll();
		System.out.println("Student=" + students);
	}
}
