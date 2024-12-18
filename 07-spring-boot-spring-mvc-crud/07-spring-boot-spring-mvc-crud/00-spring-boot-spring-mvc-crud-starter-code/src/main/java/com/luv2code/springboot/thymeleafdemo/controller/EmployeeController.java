package com.luv2code.springboot.thymeleafdemo.controller;


import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService=theEmployeeService;
    }
//    Add mapping for "/list"
    @GetMapping("/list")
    public String listEmployee(Model theModel){
        //get list from db
        List<Employee> theEmployees = employeeService.findAll();

        //add to the model
        theModel.addAttribute("employees",theEmployees);

        return "employees/list-employees";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        // create model attribute to bind form data
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee",theEmployee);

        return "employees/employee-form";
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        //save the employee
        employeeService.save(theEmployee);

        //use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId , Model theModel){
        //Get employee from db
        Employee theEmployee = employeeService.findById(theId);

        theModel.addAttribute("employee",theEmployee);

        return "/employees/employee-form";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int thId){
        employeeService.deleteById(thId);

        return "redirect:/employees/list";
    }

}
