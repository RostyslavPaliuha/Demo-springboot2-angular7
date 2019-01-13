package com.example.demo.dto;

import com.example.demo.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
public class PaginationResponceDTO {
    private int totalPages;
    private List<Employee> employees;
}
