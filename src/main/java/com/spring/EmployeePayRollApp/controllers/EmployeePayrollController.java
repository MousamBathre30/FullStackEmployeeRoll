package com.spring.EmployeePayRollApp.controllers;

import com.spring.EmployeePayRollApp.dto.EmployeePayrollDTO;
import com.spring.EmployeePayRollApp.dto.ResponseDTO;
import com.spring.EmployeePayRollApp.model.EmployeePayrollData;
import com.spring.EmployeePayRollApp.services.IEmployeePayrollService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
 // ✅ Allow requests from Angular
@RestController
@RequestMapping(value = "/employees")  // ✅ Updated base path to prevent static resource conflict
public class EmployeePayrollController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeePayrollController.class);
    private final IEmployeePayrollService employeePayrollService;

    @Autowired
    public EmployeePayrollController(IEmployeePayrollService employeePayrollService) {
        this.employeePayrollService = employeePayrollService;
    }

    // ✅ Fetch all employees
    @GetMapping
    public ResponseEntity<ResponseDTO> getEmployeePayrollData() {
        logger.debug("🔍 [GET] Request received: Fetching all employees...");
        List<EmployeePayrollData> empDataList = employeePayrollService.getEmployeePayrollData();
        logger.info("✅ Successfully fetched {} employees", empDataList.size());
        ResponseDTO respDTO = new ResponseDTO("Get Call Successful", empDataList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    // ✅ Fetch employee by ID
    @GetMapping("/get/{empId}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("empId") int empId) {
        logger.debug("🔍 [GET] Request received: Fetching employee with ID: {}", empId);
        EmployeePayrollData empData = employeePayrollService.getEmployeePayrollDataById(empId);
        logger.info("✅ Employee fetched: {}", empData);
        ResponseDTO respDTO = new ResponseDTO("Get call for ID successful", empData);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    // ✅ Create new employee
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> createEmployeePayrollData(@Valid @RequestBody EmployeePayrollDTO empPayrollDTO) {
        logger.debug("🔍 [POST] Request received: Creating employee: {}", empPayrollDTO);
        EmployeePayrollData empData = employeePayrollService.createEmployeePayrollData(empPayrollDTO);
        logger.info("✅ Employee created: {}", empData);
        ResponseDTO respDTO = new ResponseDTO("Created Employee Payroll Data Successfully", empData);
        return new ResponseEntity<>(respDTO, HttpStatus.CREATED);
    }

    // ✅ Update employee
    @PutMapping("/update/{empId}")
    public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("empId") int empId, @Valid @RequestBody EmployeePayrollDTO empPayrollDTO) {
        logger.debug("🔍 [PUT] Request received: Updating employee ID: {}, Data: {}", empId, empPayrollDTO);
        EmployeePayrollData empData = employeePayrollService.updateEmployeePayrollData(empId, empPayrollDTO);
        logger.info("✅ Employee updated: {}", empData);
        ResponseDTO respDTO = new ResponseDTO("Updated Employee Payroll Data Successfully", empData);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    // ✅ Fetch employees by department
    @GetMapping("/department/{department}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("department") String department) {
        logger.debug("🔍 [GET] Request received: Fetching employees from department: {}", department);
        List<EmployeePayrollData> empDataList = employeePayrollService.getEmployeesByDepartment(department);
        logger.info("✅ Employees fetched for department {}: {}", department, empDataList.size());
        ResponseDTO responseDTO = new ResponseDTO("Get Call for department successful", empDataList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // ✅ Delete employee
    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") int empId) {
        logger.debug("🔍 [DELETE] Request received: Deleting employee ID: {}", empId);
        employeePayrollService.deleteEmployeePayrollData(empId);
        logger.info("✅ Employee deleted with ID: {}", empId);
        ResponseDTO respDTO = new ResponseDTO("Deleted Successfully", "Deleted ID: " + empId);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    // ✅ Exception handling for debugging
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleException(Exception e) {
        logger.error("❌ Error occurred: {}", e.getMessage(), e);
        ResponseDTO errorResponse = new ResponseDTO("Internal Server Error", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
