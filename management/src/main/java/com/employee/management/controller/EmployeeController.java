package com.employee.management.controller;

import com.employee.management.model.Employee;
import com.google.gson.Gson;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.employee.management.service.EmployeeService;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequestMapping("/api/employees")

public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable @Valid String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        try {
            Employee emp = employeeService.findById(id);
            return new ResponseEntity<>(emp, headers, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("{ \"error\": \"no document with provided id\" }", headers, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> postEmployee(@RequestBody @Valid Employee employeeDetails) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
//        if(employeeDetails.getName() == null || employeeDetails.getSalary() == 0 || employeeDetails.getTelephone() == 0){
//            return new ResponseEntity<>("An employee must have a name, a salary and a telephone number!", headers, HttpStatus.BAD_REQUEST);
//        }
//        if(employeeDetails.getTelephone() < 99999999){
//            return new ResponseEntity<>("Telephone number must have 10 digits!", headers, HttpStatus.BAD_REQUEST);
//        }
//        if(Objects.equals(employeeDetails.getName(), "")){
//            return new ResponseEntity<>("Name should not be empty!", headers, HttpStatus.BAD_REQUEST);
//        }
        Employee newEmployee = this.employeeService.save(employeeDetails);
        Gson gson = new Gson();
        return new ResponseEntity<>(gson.toJson(employeeDetails), headers, HttpStatus.CREATED);

    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<String> putEmployee(@RequestBody @Valid Employee employeeDetails, @PathVariable String id) {
//        if (!ObjectId.isValid(id)) {
//            return new ResponseEntity<>("Provided object id is not valid!", HttpStatus.BAD_REQUEST);
//        }
//        if(employeeDetails.getName() == null || employeeDetails.getSalary() == 0 || employeeDetails.getTelephone() == 0){
//            return new ResponseEntity<>("An employee must have a name, a salary and a telephone number!", HttpStatus.BAD_REQUEST);
//        }
//        if(employeeDetails.getTelephone() < 99999999){
//            return new ResponseEntity<>("Telephone number must have 10 digits!", HttpStatus.BAD_REQUEST);
//        }
//        if(Objects.equals(employeeDetails.getName(), "")){
//            return new ResponseEntity<>("Name should not be empty!", HttpStatus.BAD_REQUEST);
//        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        employeeDetails.setId(id);
        Employee newEmp = this.employeeService.save(employeeDetails);
        headers.add(HttpHeaders.LOCATION, "/api/employees" + newEmp.getId());
        Gson gson = new Gson();
        return new ResponseEntity<>(gson.toJson(employeeDetails), headers, HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable @Valid String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        try {
            Employee emp = this.employeeService.findById(id);
            employeeService.deleteById(id);
            return new ResponseEntity<>("employee deleted", headers, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("could not find employee for provided id", headers, HttpStatus.NOT_FOUND);
        }

    }
}
