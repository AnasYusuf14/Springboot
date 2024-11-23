package com.luv2code.springboot.thymeleafdemo.controller;


import com.luv2code.springboot.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {
    @Value("${countries}")
    private List<String> countries;
    @Value("${languages}")
    private List<String> languages;
    @Value("${systems}")
    private List<String> systems;
    @GetMapping("/showStudentForm")
    public String showForm(Model theModel){
        //Create a new student obj
        Student theStudent = new Student();

        //add the student to model
        theModel.addAttribute("student",theStudent);
        //Add the list of countries
        theModel.addAttribute("countries",countries);
        //Add the list of languages to the model
        theModel.addAttribute("languages",languages);
        //Add the list of systems to the model
        theModel.addAttribute("systems",systems);
        return "student-form";
    }
    @PostMapping("processStudentForm")
    public String processForm(@ModelAttribute("student") Student theStudent){
        System.out.println("theStudent: "+theStudent.getFirstName()+" "+theStudent.getLastName());
        return "student-conformation";
    }
}
