package com.spring.EmployeePayRollApp.services;

import com.spring.EmployeePayRollApp.Repo.EmployeeRepository;
import com.spring.EmployeePayRollApp.dto.EmployeePayrollDTO;
import com.spring.EmployeePayRollApp.exceptions.EmployeePayrollException;
import com.spring.EmployeePayRollApp.model.EmployeePayrollData;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeePayrollService implements IEmployeePayrollService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
    // Get all employees from the database
    @Override
    public List<EmployeePayrollData> getEmployeePayrollData() {
        return employeeRepository.findAll();
    }

    // Get employee by ID using JPA
    @Override
    public EmployeePayrollData getEmployeePayrollDataById(int empId) {
        return employeeRepository.findById((long) empId)
                .orElseThrow(() -> new EmployeePayrollException("Employee Not Found with ID: " + empId + " does not exists..!"));
    }

    //  Create new employee and save to MySQL
    @Override
    @Transactional
    public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
        EmployeePayrollData empData = new EmployeePayrollData(); // No manual ID assignment
        empData.setName(empPayrollDTO.getName());
        empData.setSalary(empPayrollDTO.getSalary());
        empData.setGender(empPayrollDTO.getGender());
        empData.setStartDate(empPayrollDTO.getStartDate());
        empData.setNote(empPayrollDTO.getNote());
        empData.setProfilepic(empPayrollDTO.getProfilepic());
        empData.setDepartments(empPayrollDTO.getDepartments());

        log.debug("Emp Data : " + empData.toString());
        return employeeRepository.save(empData);
    }


    @Override
    public List<EmployeePayrollData> getEmployeesByDepartment(String department) {
        return employeeRepository.findEmployeesByDepartment(department);
    }
    //  Update employee data in MySQL --db
    @Override
    public EmployeePayrollData updateEmployeePayrollData(int empId, EmployeePayrollDTO empPayrollDTO) {
        EmployeePayrollData existingEmployee = getEmployeePayrollDataById(empId);
        existingEmployee.setName(empPayrollDTO.getName());
        existingEmployee.setSalary(empPayrollDTO.getSalary());
        return employeeRepository.save(existingEmployee); // Saves updated record to DB
    }

    //  Delete employee from MySQL -- db
    @Override
    public void deleteEmployeePayrollData(int empId) {
        if (!employeeRepository.existsById((long) empId)) {
            throw new EmployeePayrollException("Cannot delete, Employee ID not found: " + empId);
        }
        employeeRepository.deleteById((long) empId);
    }
}
