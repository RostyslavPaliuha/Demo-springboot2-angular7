package com.example.demo.repository.employee;

import com.example.demo.dto.SearchCriteriaDTO;
import com.example.demo.entity.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmployeeCriteriaSearchRepository {
    @PersistenceContext
    private EntityManager entityManager;
/**
 * Search palatable employees by criteria specified pattern.
 * */
    public List<Employee> searchEmployyes(String criteria, Integer page) {
        Session s = entityManager.unwrap(Session.class);
        Criteria c = s.createCriteria(Employee.class);
        c.setFirstResult(page==0?0:page*10-1);
        c.setMaxResults(10);
        List<Employee> employees = c.add(Restrictions.like("empName", criteria+"%").ignoreCase()).list();
        return employees;
    }

}
