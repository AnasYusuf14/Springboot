package com.learn.springboot.cruddemo.rest;


import com.learn.springboot.cruddemo.Service.EmployeeService;
import com.learn.springboot.cruddemo.dao.EmployeeDAO;
import com.learn.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }
//    expose "/employees" and get list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }
//    add mapping for GE /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee==null){
            throw new RuntimeException("Employee id is not found -" + employeeId);
        }

        return theEmployee;
    }
// add mapping for POST /employees/ - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
// if id  = 0 it will be insert a new employee in DB else it will be doing update for exsist employee
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }
    //add mapping PUT /employees - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }
    //Add DELETE MAPPING
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);
        if(theEmployee == null){
            throw new RuntimeException("Employee id not found -" + employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Success! deleted Employee id -" + employeeId;
    }


}
