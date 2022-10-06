package com.employee.management.service;

import com.employee.management.model.Employee;
import org.springframework.stereotype.Service;
import com.employee.management.repository.EmployeesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeesRepository employeeRepository;

    public EmployeeServiceImpl(EmployeesRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(Employee f) {
        return employeeRepository.save(f);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findEmployeeByName(String name) {
        return employeeRepository.findEmployeeByName(name);
    }

    @Override
    public Employee findById(String id) {
        return this.employeeRepository.findById(id).get();

//    public Employee updateEmployee(String id, Employee employeeDetails) {
//                Employee emp = EmployeesRepository.findById(id).get();
//                emp.setFirstName(employeeDetails.getFirstName());
//                emp.setLastName(employeeDetails.getLastName());
//
//                return EmployeesRepository.save(emp);
//            }
 }

    @Override
    public void deleteById(String id) {
        this.employeeRepository.deleteById(id);
    }
}
