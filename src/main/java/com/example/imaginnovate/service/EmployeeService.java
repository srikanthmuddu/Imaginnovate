package com.example.imaginnovate.service;

import com.example.imaginnovate.model.Employee;
import com.example.imaginnovate.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    public static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    EmployeeRepository employeeRepository;

    //create
    public Employee saveEmployee(Employee employee){
        LOGGER.info("Saving Employee");
        return employeeRepository.save(employee);
    }

    //read
    public List<Employee> getAllEmployees(){
        LOGGER.info("Getting All Employees");
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(Long id){
        LOGGER.info("Getting Employee by id");
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()){
            return employeeOptional.get();
        }
        throw new RuntimeException("Employee not found");
    }


    public double calculateTax(double salary){
        if (salary <= 250000){
            return 0;
        } else if (salary > 250000 &&  salary <= 500000) {
            return (salary - 250000) * 0.05;
        } else if (salary > 500000 && salary <= 1000000) {
            return 250000 * 0.05 + (salary - 500000) * 0.10;
        }else {
            return 250000 * 0.05 + 500000 * 0.10 + (salary - 1000000) * 0.20;
        }
    }

    public double calculateCess(double salary){
        if (salary > 2500000){
            return  (salary - 2500000) * 0.02;
        }
        return 0;
    }

    public double calculateTotalSalary(Employee employee){
        LocalDate startDate = employee.getDoj().withDayOfMonth(1);
        LocalDate endDate = LocalDate.now().withMonth(4).withDayOfMonth(1);
        long monthsBetween = ChronoUnit.MONTHS.between(startDate, endDate.withDayOfMonth(1));

        double lossOfPay = Optional.of(employee)
                .filter(emp-> emp.getDoj().getDayOfMonth() > 1)
                .map(emp -> emp.getSalary() / 30 * (emp.getDoj().getDayOfMonth() - 1))
                .orElse(0.0);

        return employee.getSalary() * monthsBetween - lossOfPay;
    }

}
