package com.joaogil.cruddemo.dao;

import com.joaogil.cruddemo.entity.Course;
import com.joaogil.cruddemo.entity.Instructor;
import com.joaogil.cruddemo.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {

    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    Instructor findInstructorByIdJoinFetchCourses(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    List<Course> findCoursesByInstructorId(int theId);
    Course findCourseById(int theId);

    void update(Instructor theInstructor);
    void update(Course theCourse);

    void deleteInstructorById(int theId);
    void deleteInstructorDetailById(int theId);
    void deleteCourseById(int theId);
}
