package com.springboot.jpamappings.dao;

import com.springboot.jpamappings.entity.Instructor;

public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
}
