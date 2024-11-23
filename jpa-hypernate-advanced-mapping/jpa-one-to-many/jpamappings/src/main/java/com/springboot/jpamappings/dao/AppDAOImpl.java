package com.springboot.jpamappings.dao;

import com.springboot.jpamappings.entity.Course;
import com.springboot.jpamappings.entity.Instructor;
import com.springboot.jpamappings.entity.InstructorDetail;
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

}
