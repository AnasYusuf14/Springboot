package com.luv2code.springdemo.mvc;

import com.luv2code.springdemo.mvc.validation.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {
    private String firstName;


    @NotNull(message = "is required")
    @Size(min=5,message = "at least 5 char is required")
    private String lastName;

    @NotNull(message = "is required")
    @Min(value = 0 , message = "Must be greater than or equal zero")
    @Max(value=10 , message="Must be less than or equal 10")
    private Integer freePasses ;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}",message = "only 5 chars / digits")
    private String postalCode;


    @CourseCode
    private String courseCode;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
