package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Request DTO for criteria search.
 * */
@Data
@NoArgsConstructor
public class SearchCriteriaDTO {
    private String criteria;
    private int page;
}
