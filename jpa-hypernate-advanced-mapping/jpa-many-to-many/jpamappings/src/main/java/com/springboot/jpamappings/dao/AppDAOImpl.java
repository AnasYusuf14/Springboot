package com.springboot.jpamappings.dao;

import com.springboot.jpamappings.entity.Course;
import com.springboot.jpamappings.entity.Instructor;
import com.springboot.jpamappings.entity.InstructorDetail;
import com.springboot.jpamappings.entity.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{
    //define field for entity manager
    private EntityManager entityManager;  
    //Inject entity manger using construrctor injection
    @Autowired
    public AppDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        //retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class,theId);
        //get courses
        List<Course> courses = tempInstructor.getCourses();
        //break association
        for(Course tempCourse:courses){
            tempCourse.setInstructor(null);
        }
        //delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        //retrieve constructor detail by id
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class,theId);
        //Remove associated object Reference
        //break bi-directional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);
        //delete instructor by instructor detail
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCourseByInstructorId(int theId) {
        //create query
        TypedQuery<Course> query =entityManager.createQuery("from Course where instructor.id=:data", Course.class);
        query.setParameter("data",theId);
        //execute query
        List<Course> courses =query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        //create query
        TypedQuery<Instructor> query =entityManager.createQuery(
                "select i from Instructor i " + "JOIN FETCH i.courses "+"JOIN FETCH i.instructorDetail "+"where i.id=:data",Instructor.class
        );
        query.setParameter("data",theId);
        //execute the query
        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);    }

    @Override
    public Course findCourseById(int theId) {

        return entityManager.find(Course.class,theId);
    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course tempCourse =entityManager.find(Course.class,theId);
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);

    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        //create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "+"JOIN FETCH c.reviews "+"where c.id = :data", Course.class
        );
        query.setParameter("data",theId);
        //execute query
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseID(int theId) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c "+"JOIN FETCH c.students " + "where c.id=:data",Course.class);
        query.setParameter("data",theId);
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {
        TypedQuery<Student> query = entityManager.createQuery("select c from Student c "+"JOIN FETCH c.courses " + "where c.id=:data",Student.class);
        query.setParameter("data",theId);
        Student student = query.getSingleResult();
        return student;
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);

    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        //get student
        Student student = entityManager.find(Student.class,theId);
        //delete Student
        entityManager.remove(student);
    }

}
