package com.example.demo.controller;

import com.example.demo.dto.PaginationResponceDTO;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Employees REST endpoints
 */
@CrossOrigin()
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    /**
     * Retrieve paginated employee result.
     */
    @GetMapping
    public ResponseEntity<PaginationResponceDTO> getPaginatedResult(@RequestParam int page) {
        Page<Employee> employeePage = service.getPage(page);
        return ResponseEntity.ok(new PaginationResponceDTO(employeePage.getTotalPages(), employeePage.getContent()));
    }

    /**
     * Retrieve paginated employee search result.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployeesByName(@RequestParam String criteria, @RequestParam Integer page) {
        List<Employee> employees = service.searchByCriteria(criteria, page);
        return ResponseEntity.ok(employees);
    }

    /**
     * Find employee by ID.
     */
    //@GetMapping
    public ResponseEntity<Employee> findEmployeeByID(@RequestParam long empID) {
        return ResponseEntity.ok(service.findEmployee(empID));
    }

    /**
     * Save new Employee.
     */
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee e = service.save(new Employee());
        return e != null ? ResponseEntity.status(201).body(e) : ResponseEntity.badRequest().body(e);
    }

    /**
     * Update employee record.
     */
    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = service.updateEmployee(employee);
        return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee) : ResponseEntity.badRequest().build();
    }

    /**
     * Delete employee record by ID.
     */
    @DeleteMapping
    public ResponseEntity deleteEmployee(@RequestParam long empID) {
        return ResponseEntity.status(service.deleteEmployee(empID) ? 400 : 200).build();
    }
}
