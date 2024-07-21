package com.example.jpa.entity;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder    // to add the builder pattern for the entity class
@Table(
	name="tbl_students",
	uniqueConstraints = @UniqueConstraint(columnNames= {"email_address"})
)
public class Student {
	@Id
	@SequenceGenerator(
		name="student_sequence",
		sequenceName="student_sequence",
		allocationSize=1
	)
	@GeneratedValue(
		strategy=GenerationType.SEQUENCE,
		generator="student_sequence"
	)
	private Long studentId;
	
	@Column(length=100)
	private String firstName;
	
	private String lastName;
	
	@Column(
		name="email_address",
		nullable=false
	)
	private String emailId;
	
//	private String guardianName;
//	private String guardianEmail;
//	private String guardianMobile;
	
	@Embedded
	private Guardian guardian;
	
	@Column(name="date_created")
	@CreationTimestamp
	private Date dateCreated;
	
	@Column(name="date_updated")
	@UpdateTimestamp
	private Date dateUpdated;
}
