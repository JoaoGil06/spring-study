package com.joaogil.springboot.cruddatabase.dao;

import com.joaogil.springboot.cruddatabase.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    // define field for entity manager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {

        // Create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        // Execute a query and get result list
        List<Employee> employees = theQuery.getResultList();

        // return the results
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        // Get Employee
        Employee theEmployee = entityManager.find(Employee.class, theId);

        // Return employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        // Save employee
        Employee dbEmployee = entityManager.merge(theEmployee);

        // Return db employee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        // Find employee by id
        Employee theEmployee = entityManager.find(Employee.class, theId);

        // remove employee
        entityManager.remove(theEmployee);
    }
}
