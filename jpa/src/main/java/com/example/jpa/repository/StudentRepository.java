package com.example.jpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jpa.entity.Student;

import jakarta.transaction.Transactional;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
    // Derived query methods
	
	List<Student> findByFirstName(String firstName);
	
	List<Student> findByFirstNameContaining(String name);
	
	Student findByFirstNameAndLastName(String firstName, String lastName);
	
	List<Student> findByLastNameNotNull();
	
	List<Student> findByGuardianName(String guardianName);
	
	List<Student> findByDateCreatedBetween(Date start, Date end);
	
	// Paging and Sorting
	
	Page<Student> findByLastNameContaining(String name, Pageable pageable); // returns page object
	
	List<Student> findByLastNameContaining(String name, Sort sort);
	
	
	//List<Student> findAll(Sort.by("grade").ascending());

	// JPQL
	@Query("select s from Student s where s.emailId = ?1")
	Student findByEmailAddress(String emailId);  //can give any name
	
	// JPQL
	@Query("select s.firstName from Student s where s.emailId = ?1")
	String findFirstNameByEmailAddress(String emailId);
	
	// JPQL
	@Query("select s.firstName, s.lastName from Student s where s.emailId = ?1")
	String findFirstNameAndLastNameByEmailAddress(String emailId); // return type matters
	
	// Native Query
	@Query(value="select * from tbl_students s where s.email_address= ?1", nativeQuery=true)
	Student findByEmailAddressNative(String emailId);
	
	// Query Param - JPQL
	@Query("select s from Student s where s.emailId = :email")
	Student findByEmailAddressParam(@Param("email") String emailId); 
		
	// Query Param -Native Query 
	@Query(value="select * from tbl_students s where s.email_address= :email", nativeQuery=true)
	Student findByEmailAddressNativeParam(@Param("email") String emailId);
	
	@Modifying
	@Transactional // used in service layer for modifying operations
	@Query(value="update tbl_students set first_name=?2 where email_address=?1", nativeQuery=true)
	int updateStudentFirstNameWithEmailAddress(String emailId, String firstName);
	
	@Modifying
	@Transactional
	@Query(value="update tbl_students set first_name= :name where email_address= :email", nativeQuery=true)
	int updateStudentFirstNameWithEmailAddressParam(@Param("email") String emailId, @Param("name") String firstName);

	
}
