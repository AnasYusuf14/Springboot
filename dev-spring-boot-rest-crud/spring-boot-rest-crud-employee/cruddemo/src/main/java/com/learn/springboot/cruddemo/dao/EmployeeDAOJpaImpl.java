package com.learn.springboot.cruddemo.dao;

import com.learn.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{
//    Define Entity Manager
    private EntityManager entityManager;

//    set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }


    @Override
    public List<Employee> findAll() {
//        create a query and get result list
        TypedQuery<Employee>  theQuery = entityManager.createQuery("from Employee",Employee.class);

//        Execute the query and get result list
          List<Employee> employees= theQuery.getResultList();
//        return the results
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        Employee theEmployee = entityManager.find(Employee.class,theId);

        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
//        merge is doing insert/save in database if id = 0 else doing update
        Employee dbEmployee = entityManager.merge(theEmployee);
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
// find employee bu id
        Employee theEmployee = entityManager.find(Employee.class, theId);
//        Delete employee
        entityManager.remove(theEmployee);
    }

}
