package com.spring.EmployeePayRollApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = "com.spring.EmployeePayRollApp")

public class EmployeePayRollAppApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(EmployeePayRollAppApplication.class, args);
		log.info("Employee Payroll App Started in Environment" , context.getEnvironment().getProperty("environment"));
        log.info("Employee Payroll DB User is {} ", context.getEnvironment().getProperty("spring.datasource.username"));
	}

}
