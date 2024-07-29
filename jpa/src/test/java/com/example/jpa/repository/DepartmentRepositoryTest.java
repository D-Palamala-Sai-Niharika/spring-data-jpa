package com.example.jpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Department;
import com.example.jpa.entity.Employee;

@SpringBootTest
class DepartmentRepositoryTest {

	@Autowired
	private DepartmentRepository depRepo;
	
	@Test
	public void saveDepartment() {
		Employee emp1=Employee
				.builder()
				.employeeName("neha")
				.build();
		Employee emp2=Employee
				.builder()
				.employeeName("ishan")
				.build();

		Department dep=Department
				.builder()
				.deptName("IT")
				.employees(List.of(emp1,emp2))
				.build();
		emp1.setDept(dep);
		emp2.setDept(dep);
		this.depRepo.save(dep);
	}

}
