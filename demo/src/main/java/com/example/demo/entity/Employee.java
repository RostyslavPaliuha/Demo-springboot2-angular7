package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

/**
 * Employee entity
 * */

@Data
@NoArgsConstructor
@Entity
@Table(name = "tblEmployees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private long empID;
    private String empName;
    private boolean empActive;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="emp_dpID",referencedColumnName = "dpID")
    @JsonManagedReference
    private Department department;
}
