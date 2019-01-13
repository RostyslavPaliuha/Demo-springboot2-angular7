package com.example.demo.repository.employee;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeCrudRepository extends JpaRepository<Employee,Long> {
}
