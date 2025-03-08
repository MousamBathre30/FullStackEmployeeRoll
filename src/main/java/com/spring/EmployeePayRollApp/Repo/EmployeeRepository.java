package com.spring.EmployeePayRollApp.Repo;

import com.spring.EmployeePayRollApp.model.EmployeePayrollData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeePayrollData, Long> {
    @Query(value = "select * from employee_payroll , employee_department where employee_id = id and department = :department" , nativeQuery = true)
    List<EmployeePayrollData> findEmployeesByDepartment(String department);
}
