package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.employee.EmployeeCriteriaSearchRepository;
import com.example.demo.repository.employee.EmployeeCrudRepository;
import com.example.demo.repository.employee.EmployeePaginationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for manipulating employees data.
 */

@Service
public class EmployeeService {
    private EmployeePaginationRepository repository;
    private EmployeeCrudRepository employeeCrudRepository;
    private EmployeeCriteriaSearchRepository employeeCriteriaSearchRepository;

    @Autowired
    public EmployeeService(EmployeePaginationRepository repository, EmployeeCrudRepository employeeCrudRepository, EmployeeCriteriaSearchRepository employeeCriteriaSearchRepository) {
        this.repository = repository;
        this.employeeCrudRepository = employeeCrudRepository;
        this.employeeCriteriaSearchRepository = employeeCriteriaSearchRepository;
    }

    /**
     * Get paginated result of employees data.
     */
    public Page<Employee> getPage(Integer page) {
        return repository.findAll(PageRequest.of(page, 10));
    }

    /**
     * Save new employee.
     */
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    /**
     * Search employees by pattern of first characters.
     **/
    public List<Employee> searchByCriteria(String criteria, Integer page) {
        return employeeCriteriaSearchRepository.searchEmployyes(criteria, page);
    }

    /**
     * Update Employee record.
     */
    public Employee updateEmployee(Employee employee) {
        return repository.save(employee);
    }

    /**
     * Delete employee by ID.
     */
    public boolean deleteEmployee(long empID) {
        employeeCrudRepository.deleteById(empID);
        return employeeCrudRepository.existsById(empID);
    }

    /**
     * Find employee by ID.
     */
    public Employee findEmployee(long empID) {
        return employeeCrudRepository.findById(empID).orElse(new Employee());
    }
}


