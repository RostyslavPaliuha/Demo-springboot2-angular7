package com.example.demo.service;

import com.example.demo.dto.SearchCriteriaDTO;
import com.example.demo.entity.Employee;
import com.example.demo.repository.employee.EmployeeCriteriaSearchRepository;
import com.example.demo.repository.employee.EmployeeCrudRepository;
import com.example.demo.repository.employee.EmployeePaginationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService {
    private EmployeePaginationRepository repository;
    private EmployeeCrudRepository employeeCrudRepository;
    private EmployeeCriteriaSearchRepository employeeCriteriaSearchRepository;

    @Autowired
    public EmployeeService(EmployeePaginationRepository repository, EmployeeCrudRepository employeeCrudRepository,EmployeeCriteriaSearchRepository employeeCriteriaSearchRepository) {
        this.repository = repository;
        this.employeeCrudRepository = employeeCrudRepository;
        this.employeeCriteriaSearchRepository=employeeCriteriaSearchRepository;
    }

    public Page<Employee> getPage(Integer page) {
        return repository.findAll(PageRequest.of(page, 10));
    }

    public Employee save(Employee employee) {
        return repository.save(employee);
    }


    public List<Employee> searchByCriteria(String criteria,Integer page) {
        return employeeCriteriaSearchRepository.getEmployees(criteria,page);
    }

    public Employee updateEmployee(Employee employee) {
        return repository.save(employee);
    }


}


