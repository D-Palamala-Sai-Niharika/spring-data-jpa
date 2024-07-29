package com.example.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="profiles")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long profileId;
	private String bio;
	
	@OneToOne(mappedBy="profile")
	private User user;

}
