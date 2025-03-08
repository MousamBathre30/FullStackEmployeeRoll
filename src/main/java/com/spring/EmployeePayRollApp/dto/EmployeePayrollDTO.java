package com.spring.EmployeePayRollApp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@ToString
public class EmployeePayrollDTO {

    @NotEmpty(message = "Employee name cannot be null")
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]{2,}$", message = "Employee name Invalid")
    private String name;

    @Min(value = 500, message = "Min Wage should be more than 500")
    private long salary;

    @Pattern(regexp = "male|female", message = "Gender needs to be male or female")
    private String gender;

    @NotNull(message = "startDate should not be empty")
    @PastOrPresent(message = "startDate should be past or today's date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // Ensures JSON parsing
    private LocalDate startDate;

    @NotBlank(message = "Note cannot be Empty")
    private String note;

    @NotBlank(message = "profilePic cannot be empty")
    private String profilepic;

    @NotNull(message = "department should not be empty")
    private List<String> departments;
}
