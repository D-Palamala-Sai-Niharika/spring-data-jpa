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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.jpa.entity.Guardian;
import com.example.jpa.entity.Student;

import org.springframework.data.domain.Pageable; // Correct import
import org.springframework.data.domain.Sort;

@SpringBootTest  // Using this to check data reflection in DB
//@DataJpaTest  // ideally we shd be using datajpa test for repository - tests and flushes data so that DB doesn't impact
class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepo;
	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	//save
	
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
				.email("bhar@gmail.com")
				.name("gnanambha")
				.mobile("9097999999")
				.build();
		
		Student student = Student.builder()
				.firstName("bhargavi")
				.lastName("kailasani")
				.emailId("bhargavi@gmail.com")
				.guardian(guardian)
				.grade(10)
				.build();
		this.studentRepo.save(student);
	}
	
	//retrieve
	
	@Test
	public void printAllStudents() {
		List<Student> students =this.studentRepo.findAll();
		System.out.println("Student=" + students);
	}
	
	//derived query methods
	
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
	
	
	// paging
	
	@Test
	public void getElementsByPagination() {
		Pageable firstPageWithThreeRecords = PageRequest.of(0, 3); // page number starts from 0
		//Pageable secondPageWithFourRecords = PageRequest.of(1, 4);
		
		List<Student> studentsInFirstPageWithThreeRecords = this.studentRepo.findAll(firstPageWithThreeRecords).getContent();
		long totalElements = this.studentRepo.findAll(firstPageWithThreeRecords).getTotalElements();
		long numberOfElements = this.studentRepo.findAll(firstPageWithThreeRecords).getNumberOfElements();
		long pageNumber = this.studentRepo.findAll(firstPageWithThreeRecords).getNumber();
		long totalPages = this.studentRepo.findAll(firstPageWithThreeRecords).getTotalPages();
		
		System.out.println("getContent=" + studentsInFirstPageWithThreeRecords);
		System.out.println("numberOfElements=" + numberOfElements);
		System.out.println("totalElements=" + totalElements);
		System.out.println("pageNumber=" + pageNumber);
		System.out.println("totalPages=" + totalPages);
		
	}
	
	@Test
	public void getElementsByLastNameConatiningPagination() {
		Pageable records = PageRequest.of(0, 2);
		//Pageable records = PageRequest.of(1, 2); 
		List<Student> students = this.studentRepo.findByLastNameContaining("pal", records).getContent();
		System.out.println("getContent=" + students);
		
	}
	
	//sorting 
	
	@Test
	public void sortElementsByGrade() {
		 //Sort sort =  Sort.by("grade").ascending();  // based on class properties . jpql considers class rather than tables
		 Sort sort =  Sort.by("grade").descending();
		 List<Student> students = this.studentRepo.findAll(sort);
		 System.out.println("getContent=" + students + students.size());
	}
	
	@Test
	public void getElementsBySorting() {
		 Sort sort =  Sort.by("firstName");  // based on class properties . jpql considers class rather than tables
		 List<Student> students = this.studentRepo.findAll(sort);
		 System.out.println("getContent=" + students + students.size());
		
	}
	
	@Test
	public void getElementsByLastNameConatiningSorting() {
		 Sort sort =  Sort.by("firstName");  // based on class properties . jpql considers class rather than tables
		 List<Student> students = this.studentRepo.findByLastNameContaining("pal", sort);
		 System.out.println("getContent=" + students + students.size());
		
	}

	
	@Test
	public void sortElementsByGradeAndFirstName() {
		 Sort sort =  Sort.by("firstName").and(Sort.by("grade"));  // based on class properties . jpql considers class rather than tables
		 List<Student> students = this.studentRepo.findAll(sort);
		 System.out.println("getContent=" + students + students.size());
		
	}
	
	//paging and sorting
	
	@Test
	public void findAllPageable() {
		//Pageable sortByGrade = PageRequest.of(0, 3,Sort.by("grade"));
		
		//Pageable sortByFirstNameDesc = PageRequest.of(0, 3,Sort.by("firstName"));
		
		Pageable sortByFirstNameAndGradeDesc = PageRequest.of(0, 3,Sort.by("grade").descending().and(Sort.by("firstName"))); 
		
		List<Student> students = this.studentRepo.findAll(sortByFirstNameAndGradeDesc).getContent();
		System.out.println("getContent=" + students);
	}
	
	@Test
	public void getElementsByLastNameConatiningPaginationAndSorting() {
		Pageable records = PageRequest.of(0, 2, Sort.by("grade"));
		//Pageable records = PageRequest.of(1, 2); 
		List<Student> students = this.studentRepo.findByLastNameContaining("pal", records).getContent();
		System.out.println("getContent=" + students);
		
	}
}
