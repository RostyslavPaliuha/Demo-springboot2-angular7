package com.example.demo.controller;

import com.example.demo.dto.PaginationResponceDTO;
import com.example.demo.dto.SearchCriteriaDTO;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin()
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<PaginationResponceDTO> getAll(@RequestParam Integer page) {
        Page<Employee> employeePage = service.getPage(page);
        return ResponseEntity.ok(new PaginationResponceDTO(employeePage.getTotalPages(), employeePage.getContent()));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployees(@RequestParam String criteria,@RequestParam Integer page) {
        List<Employee> employees = service.searchByCriteria(criteria,page);
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee e = service.save(new Employee());
        return e != null ? ResponseEntity.status(201).body(e) : ResponseEntity.badRequest().body(e);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = service.updateEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
}
