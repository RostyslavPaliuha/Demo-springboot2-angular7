package com.example.demo.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
/**
 * Department entity.
 * */
@Data
@NoArgsConstructor
@Entity
@Table(name = "tblDepartments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dpID;
    private String dpName;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department")
    private List<Employee> employees;

}
