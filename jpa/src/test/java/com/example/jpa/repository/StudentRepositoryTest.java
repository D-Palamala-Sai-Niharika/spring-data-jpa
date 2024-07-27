package com.example.jpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
				.firstName("ishan")
				.lastName("sethi")
				.emailId("ishan@gmail.com")
				.guardian(guardian)
				.build();
		this.studentRepo.save(student);
	}
	
	@Test
	public void printAllStudents() {
		List<Student> students =this.studentRepo.findAll();
		System.out.println("Student=" + students);
	}
	
	@Test
	public void printStudentsByFirstName() {
		List<Student> students = this.studentRepo.findByFirstName("neha");
		System.out.println("student=" + students);
	}
	
	@Test
	public void printStudentsByFirstNameContaining() {
		List<Student> students = this.studentRepo.findByFirstNameContaining("niha");
		System.out.println("student=" + students);
	}
	
	@Test
	public void printStudentsByFirstNameAndLastName() {
		Student student = this.studentRepo.findByFirstNameAndLastName("Niharika","Palamala");
		System.out.println("student=" + student);
	}
	
	@Test
	public void printStudentsByLastNameNotNull() {
		List<Student> students = this.studentRepo.findByLastNameNotNull();
		System.out.println("students=" + students);
	}
	
	@Test
	public void printStudentsByGuardianName() {
		List<Student> students = this.studentRepo.findByGuardianName("bhargavi");
		System.out.println("students=" + students);
	}
	
	@Test
	public void printStudentsByDateCreatedBetween() throws ParseException {
		Date start = new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-19");
		Date end = new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-22");
		List<Student> students = this.studentRepo.findByDateCreatedBetween(start, end);
		System.out.println("students=" + students);
	}
	
	@Test
	public void printfindByEmailAddress() {
		Student student = this.studentRepo.findByEmailAddress("neha@gmail.com");
		System.out.println("student=" + student);
	}
	
	@Test
	public void printfindFirstNameByEmailAddress() {
		String fn = this.studentRepo.findFirstNameByEmailAddress("neha@gmail.com");
		System.out.println("firstName=" + fn);
	}
	
	@Test
	public void printfindFirstNameAndLastNameByEmailAddress() {
		String fn = this.studentRepo.findFirstNameAndLastNameByEmailAddress("neha@gmail.com");
		System.out.println("student=" + fn);
	}
	
	@Test
	public void findByEmailAddressParam() {
		Student student = this.studentRepo.findByEmailAddressParam("neha@gmail.com");
		System.out.println("student=" + student);
	}
	
	@Test
	public void findByEmailAddressNativeParam() {
		Student student = this.studentRepo.findByEmailAddressNativeParam("neha@gmail.com");
		System.out.println("student=" + student);
	}
	
	@Test
	public void updateStudentFirstNameWithEmailAddress() {
		int success = this.studentRepo.updateStudentFirstNameWithEmailAddress("neha@gmail.com","Deepthi");
		System.out.println("success=" + success);
	}
	
	@Test
	public void updateStudentFirstNameWithEmailAddressParam() {
		int success = this.studentRepo.updateStudentFirstNameWithEmailAddressParam("neha@gmail.com","Niharika");
		System.out.println("success=" + success);
	}
	
}
