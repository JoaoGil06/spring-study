package com.joaogil.cruddemo.dao;

import com.joaogil.cruddemo.entity.Instructor;
import com.joaogil.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppDAOImpl implements AppDAO{

    // define field for entityManager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        Instructor theInstructor = entityManager.find(Instructor.class, theId);

        return theInstructor;
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor theInstructor = entityManager.find(Instructor.class, theId);

        entityManager.remove(theInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        InstructorDetail theInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        return theInstructorDetail;
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail theInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        entityManager.remove(theInstructorDetail);
    }
}
