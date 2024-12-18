package com.springboot.jpamappings.dao;

import com.springboot.jpamappings.entity.Course;
import com.springboot.jpamappings.entity.Instructor;
import com.springboot.jpamappings.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);
    List<Course> findCourseByInstructorId(int theId);
    Instructor findInstructorByIdJoinFetch(int theId);
    void update(Instructor tempInstructor);
    Course findCourseById(int theId);
    void update(Course tempCourse);
    void deleteCourseById(int theId);
    void save(Course course);
    Course findCourseAndReviewsByCourseId(int theId);
}
