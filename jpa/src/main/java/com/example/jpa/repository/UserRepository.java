package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
