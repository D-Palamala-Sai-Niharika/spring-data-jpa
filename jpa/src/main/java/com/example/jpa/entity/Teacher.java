package com.example.jpa.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="teacher")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long teacherId;
	private String tearcherName;
	
	@ManyToMany
	@JoinTable(
		name="teacher_courses",
		joinColumns = @JoinColumn(name="teacher_id"),
		inverseJoinColumns = @JoinColumn(name="course_id")
	)
	private List<Courses> courses;

}
