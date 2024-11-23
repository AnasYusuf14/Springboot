package com.luv2code.springboot.thymeleafdemo.controller;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.http.HttpRequest;

@Controller
public class HelloWorldController {
    //new controller to show initial HTML form
    @GetMapping("showForm")
    public String showForm(){
        return "helloworld-form";
    }

    //need controller to process the HTML form
    @RequestMapping("/processForm")
    public String processForm(){
        return "helloworld";
    }

    //need a controller to read form data and add data to model
    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request , Model theModel){
        String theName = request.getParameter("studentName");

        theName= theName.toUpperCase();

        String result = "Yo!" +theName;

        theModel.addAttribute("message",result);
        return "helloworld";

    }
    //by @RequestParam
    @PostMapping("/processFormVersionThree")
    public String processFormVersionThree( @RequestParam("studentName") String theName, Model theModel){
        theName = theName.toUpperCase();

        String result = "Hi my friend!" + theName;
        theModel.addAttribute("message",result);
        return "helloworld";

    }
}
