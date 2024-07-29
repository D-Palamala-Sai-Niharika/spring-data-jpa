package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

}
