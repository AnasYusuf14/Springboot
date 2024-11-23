package com.springlearn.demo.rest;


import com.springlearn.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;
    //define @PostConstruct  to load the student data only once..
    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Anas" , "Yusuf"));
        theStudents.add(new Student("Reem" , "Yusuf"));
        theStudents.add(new Student("Ahmed" , "Yusuf"));
    }





    //    define endpoint  "/students"  - return a list of student
    @GetMapping("/students")
    public List<Student> getStudents(){
//        List<Student> theStudents = new ArrayList<>();
//        theStudents.add(new Student("Anas" , "Yusuf"));
//        theStudents.add(new Student("Reem" , "Yusuf"));
//        theStudents.add(new Student("Ahmed" , "Yusuf"));
          return theStudents;
    }

    //define endpoint or "/students/{studentId}" to retrieve a  student at index in arrayList
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        //check the student id again the list size

        if((studentId >= theStudents.size())||studentId < 0){
            throw new StudentNotFoundExeption("Student id not found -" + studentId);
        }
        return theStudents.get(studentId);
    }

//    Add an exception handler using @ExceptionHandler
//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundExeption exc){
////        create a studentErrorResponse
//        StudentErrorResponse error = new StudentErrorResponse();
//        error.setStatus(HttpStatus.NOT_FOUND.value());
//        error.setMessage(exc.getMessage());
//        error.setTimeStamp(System.currentTimeMillis());
//
//        //Return ResponseEntity
//        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
//    }
//    //Add another Exception Handler .. to catch any exception (catch all)
//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
//        //        create a studentErrorResponse
//        StudentErrorResponse error = new StudentErrorResponse();
//        error.setStatus(HttpStatus.BAD_REQUEST.value());
//        error.setMessage(exc.getMessage());
//        error.setTimeStamp(System.currentTimeMillis());
//
//        //return ResponseEntity
//        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
//    }
}





