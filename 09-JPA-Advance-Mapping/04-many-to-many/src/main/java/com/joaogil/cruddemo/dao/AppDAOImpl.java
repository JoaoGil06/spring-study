package com.joaogil.cruddemo.dao;

import com.joaogil.cruddemo.entity.Course;
import com.joaogil.cruddemo.entity.Instructor;
import com.joaogil.cruddemo.entity.InstructorDetail;
import com.joaogil.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        // find the instrucotr
        Instructor theInstructor = entityManager.find(Instructor.class, theId);

        // get the courses
        List<Course> courses = theInstructor.getCourses();

        // break association of all courses for the instructor
        for(Course tempCourse : courses) {
            tempCourse.setInstructor(null);
        }

        // delete the instructor
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

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        // Create Query
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", theId);

        // Executar a query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetchCourses(int theId) {
        // Create Query
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i " +
                                                                    "JOIN FETCH i.courses " +
                                                                    "where i.id = :data", Instructor.class);
        query.setParameter("data", theId);

        // Executar a query
        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor theInstructor) {
        entityManager.merge(theInstructor);
    }

    @Override
    @Transactional
    public void update(Course theCourse) {
        entityManager.merge(theCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        Course theCourse = entityManager.find(Course.class, theId);

        return theCourse;
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        // retrieve the course
        Course theCourse = entityManager.find(Course.class, theId);

        // delete the course
        entityManager.remove(theCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        // Create query
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c "
                                                               + "JOIN FETCH c.reviews "
                                                               + "where c.id = :data", Course.class);
        query.setParameter("data", theId);

        Course theCourse = query.getSingleResult();

        return theCourse;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {
        // Create query
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c "
                + "JOIN FETCH c.students "
                + "where c.id = :data", Course.class);
        query.setParameter("data", theId);

        Course theCourse = query.getSingleResult();

        return theCourse;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {
        // Create query
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s "
                + "JOIN FETCH s.courses "
                + "where s.id = :data", Student.class);
        query.setParameter("data", theId);

        Student theStudent = query.getSingleResult();

        return theStudent;
    }
}
