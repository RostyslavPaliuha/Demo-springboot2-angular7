package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

/**
 * Employee entity.
 */
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tblEmployees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private long empID;
    private String empName;
    private boolean empActive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_dpID", referencedColumnName = "dpID")
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "dpID")
    //@JsonManagedReference
    private Department department;
}
