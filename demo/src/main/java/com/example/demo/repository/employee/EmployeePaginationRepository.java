package com.example.demo.repository.employee;

import com.example.demo.entity.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**Common spring data repo.
 * */
@Repository
public interface EmployeePaginationRepository extends PagingAndSortingRepository<Employee, Long> {

    List<Employee> findEmployeesByDepartmentDpName(String dpName);

}
