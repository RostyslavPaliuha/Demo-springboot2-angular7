package com.example.demo.dto;

import com.example.demo.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Paginated search response DTO
 * */
import java.util.List;

@Data
@AllArgsConstructor
public class PaginationResponceDTO {
    private int totalPages;
    private List<Employee> employees;
}
