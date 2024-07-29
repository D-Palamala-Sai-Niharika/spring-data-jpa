package com.example.jpa.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="courses")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Courses {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long courseId;
	private String courseName;
	
	@ManyToMany(mappedBy="courses")
	private List<Teacher> teachers;

}
