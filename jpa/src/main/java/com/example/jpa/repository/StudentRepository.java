package com.example.jpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jpa.entity.Student;

import jakarta.transaction.Transactional;

public interface StudentRepository extends JpaRepository<Student, Long> {

	List<Student> findByFirstName(String firstName);
	
	List<Student> findByFirstNameContaining(String name);
	
	Student findByFirstNameAndLastName(String firstName, String lastName);
	
	List<Student> findByLastNameNotNull();
	
	List<Student> findByGuardianName(String guardianName);
	
	List<Student> findByDateCreatedBetween(Date start, Date end);
	
	
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
	
	// JPQL
	@Query("select s from Student s where s.emailId = :email")
	Student findByEmailAddressParam(@Param("email") String emailId); 
		
	// Native Query
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
