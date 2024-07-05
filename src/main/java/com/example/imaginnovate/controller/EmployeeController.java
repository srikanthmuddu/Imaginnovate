package com.example.imaginnovate.controller;

import com.example.imaginnovate.model.Employee;
import com.example.imaginnovate.model.EmployeeTaxDetail;
import com.example.imaginnovate.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    @GetMapping(value = "/{id}/taxDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeTaxDetail> getEmployeeTaxDetails(@PathVariable Long id){
        Employee employee = employeeService.findEmployeeById(id);

        double totalSalary = employeeService.calculateTotalSalary(employee);
        double yearlySalary = totalSalary * ((double) 12 /
                (ChronoUnit.MONTHS.between(employee.getDoj(), LocalDate.now().withMonth(3).withDayOfMonth(31)) + 1));
        double taxAmount = employeeService.calculateTax(yearlySalary);
        double cessAmount = employeeService.calculateCess(yearlySalary);

      EmployeeTaxDetail taxDetail = new EmployeeTaxDetail(
                employee.getEmployeeId(),
                employee.getFirstName(),
                employee.getLastName(),
                yearlySalary,
                taxAmount,
                cessAmount);

        return ResponseEntity.ok(taxDetail);
    }

    @GetMapping(value = "/getAll", produces =MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
}
