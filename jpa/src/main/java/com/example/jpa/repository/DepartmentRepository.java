package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
