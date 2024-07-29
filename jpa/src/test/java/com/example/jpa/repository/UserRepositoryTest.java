package com.example.jpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Profile;
import com.example.jpa.entity.User;

@SpringBootTest
class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepo;

	@Test
	public void saveUser() {

		// Create the User
		User user = User
				.builder()
				.userName("neha")
				.build();
		
		// Create Profile and set their User reference
		Profile profile = Profile
				.builder()
				.bio("frontend dev")
				.user(user)
				.build();
		
		// Add profile to the user
		user.setProfile(profile);
		
		this.userRepo.save(user);
	}

}
