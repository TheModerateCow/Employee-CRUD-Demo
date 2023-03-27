package com.example.employeedemofirstattempt.controller;

import com.example.employeedemofirstattempt.dtos.EmployeeFormDto;
import com.example.employeedemofirstattempt.model.entities.Employee;
import com.example.employeedemofirstattempt.model.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/employees")
    public ResponseEntity<List<Employee>> retrieveEmployees() {
        return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.FOUND);
    }

    @GetMapping(path = "/employees/{employeeId}")
    public ResponseEntity<Employee> retrieveEmployeeById(@PathVariable Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        return optionalEmployee.map(employee -> new ResponseEntity<>(employee, HttpStatus.FOUND)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    // Have to add error handling when the input is not email format
    @PostMapping(path = "/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeFormDto employeeFormDto) {
        Employee newEmployee = new Employee(employeeFormDto.getFirst_name(), employeeFormDto.getLast_name(), employeeFormDto.getEmail());

        return new ResponseEntity<>(employeeRepository.save(newEmployee), HttpStatus.CREATED);
    }

    // Have to add error handling when the input is not email format
    @PutMapping(path = "/employees")
    public ResponseEntity<Employee> updateEmployee(@RequestBody EmployeeFormDto employeeFormDto) {
        Optional<Employee> tempEmployee = employeeRepository.findById(employeeFormDto.getId());
        if (tempEmployee.isPresent()) {
            Employee employee = tempEmployee.get();

            employee.setId(employeeFormDto.getId());
            employee.setFirstName(employeeFormDto.getFirst_name());
            employee.setLastName(employeeFormDto.getLast_name());
            employee.setEmail(employeeFormDto.getEmail());

            employeeRepository.save(employee);

            return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping(path = "/employees/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId) {
        Optional<Employee> tempEmployee = employeeRepository.findById(employeeId);

        if (tempEmployee.isPresent()) {
            employeeRepository.deleteEmployeeById(employeeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
