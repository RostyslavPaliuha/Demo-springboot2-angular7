package com.example.demo.repository.department;

import com.example.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * String data jpa repo.
 * */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
