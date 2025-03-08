    package com.spring.EmployeePayRollApp.model;

    import com.spring.EmployeePayRollApp.dto.EmployeePayrollDTO;
    import jakarta.persistence.*;
    import lombok.Data;
    import java.time.LocalDate;
    import java.util.List;

    @Entity
    @Table(name = "newTable4")
    @Data
    public class EmployeePayrollData {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @Column(name = "name")
        private String name;

        private long salary;

        private String gender;

        private LocalDate startDate;

        private String note;

        private String profilepic;

        @ElementCollection
        @CollectionTable(name = "employee_department", joinColumns = @JoinColumn(name = "id"))
        @Column(name = "department")
        private List<String> departments;

        // Default constructor
        public EmployeePayrollData() {}

        // Constructor that accepts EmployeePayrollDTO
        public EmployeePayrollData(int id, EmployeePayrollDTO employeePayrollDTO) {
            this.id = id;
            this.name = employeePayrollDTO.getName();
            this.salary = employeePayrollDTO.getSalary();
            this.gender = employeePayrollDTO.getGender();
            this.startDate = employeePayrollDTO.getStartDate(); // ✅ Directly assign LocalDate
            this.note = employeePayrollDTO.getNote();
            this.profilepic = employeePayrollDTO.getProfilepic();
            this.departments = employeePayrollDTO.getDepartments();
        }
    }
