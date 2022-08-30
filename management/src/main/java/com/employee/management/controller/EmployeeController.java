package com.employee.management.controller;

import com.employee.management.model.Employee;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.employee.management.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")

public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable String id){
        Employee emp = (Employee) employeeService.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(emp, headers, HttpStatus.OK);

    }
    @PostMapping("/add")
    public ResponseEntity<?> postEmployee(@RequestBody Employee employeeDetails){
        Employee newEmployee = this.employeeService.save(employeeDetails);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Location", "/api/employees/" + newEmployee.getId().toString());

        return new ResponseEntity<>(new JsonObject("{\"employee\":"+ employeeDetails.toString()+" }"), headers, HttpStatus.CREATED);
    }
}
