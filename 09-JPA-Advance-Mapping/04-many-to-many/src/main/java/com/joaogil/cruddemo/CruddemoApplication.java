package com.joaogil.cruddemo;

import com.joaogil.cruddemo.dao.AppDAO;
import com.joaogil.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            // createInstructor(appDAO);
            // findInstructor(appDAO);
            // deleteInstructor(appDAO);
            // findInstructorDetail(appDAO);
            // deleteInstructorDetail(appDAO);
            // createInstructorWithCourses(appDAO);

            // findInstructorWithCourses(appDAO);
            // findInstructorWithCoursesWithFetch(appDAO);
            // updateInstructor(appDAO);
            // updateCourse(appDAO);
            // createCourseAndReviews(appDAO);
            // retrieveCourseAndReviews(appDAO);
            // createCourseAndStudent(appDAO);
            // retrieveCourseAndStudents(appDAO);
            retrieveStudentAndCourses(appDAO);

        };
    }

    private void retrieveStudentAndCourses(AppDAO appDAO) {
        // get the course and students
        int theId = 1;
        Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

        // print the course and students
        System.out.println("Student: " + tempStudent);
        System.out.println("Student Courses: " + tempStudent.getCourses());
    }

    private void retrieveCourseAndStudents(AppDAO appDAO) {
        // get the course and students
        int theId = 1;
        Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);

        // print the course and students
        System.out.println("Course: " + tempCourse);
        System.out.println("Course Students: " + tempCourse.getStudents());
    }

    private void createCourseAndStudent(AppDAO appDAO) {
        // Create a course
        Course tempCourse = new Course("Pacman - How To Score One Million Points");

        // Create the students
        Student tempStudent = new Student("João Estudante", "Monteiro", "teste@teste.com");
        Student tempStudent1 = new Student("Rita Estudante", "Monteiro", "teste@teste.com");
        Student tempStudent2 = new Student("Pedro Estudante", "Monteiro", "teste@teste.com");

        // add students to the course
        tempCourse.addStudent(tempStudent);
        tempCourse.addStudent(tempStudent1);
        tempCourse.addStudent(tempStudent2);

        // save the course and associated students
        System.out.println("Course: " + tempCourse);
        System.out.println("Course Students: " + tempCourse.getStudents());
        appDAO.save(tempCourse);
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {
        // get the course and reviews
        int theId = 1;
        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

        // print the course and reviews
        System.out.println("Course: " + tempCourse);
        System.out.println("Course Reviews: " + tempCourse.getReviews());
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        // Create a course
        Course tempCourse = new Course("Pacman - How To Score One Million Points");

        // Add some reviews
        Review tempReview = new Review("Great Course ... loved it");
        Review tempReview1 = new Review("Good Course, job well done");
        Review tempReview2 = new Review("Great Course For Really");

        tempCourse.addReview(tempReview);
        tempCourse.addReview(tempReview1);
        tempCourse.addReview(tempReview2);

        // Save the Course
        appDAO.save(tempCourse);

        System.out.println("Course Saved");
        System.out.println("Course: " + tempCourse);
        System.out.println("Course Reviews: " + tempCourse.getReviews());
    }

    private void updateCourse(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding course ID: " + theId);

        Course tempCourse = appDAO.findCourseById(theId);
        System.out.println("Updating Course ID: " + theId);
        tempCourse.setTitle("New Course Title");

        appDAO.update(tempCourse);

        System.out.println("Done! ");
    }

    private void updateInstructor(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor ID: " + theId);

        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetchCourses(theId);
        System.out.println("Updating instructor ID: " + theId);
        tempInstructor.setLast_name("Tester");

        appDAO.update(tempInstructor);

        System.out.println("Done! ");
    }

    private void findInstructorWithCoursesWithFetch(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor ID: " + theId);

        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetchCourses(theId);

        System.out.println("TempInstructor: " + tempInstructor);
        System.out.println("Associate courses for the instrucotr: " + tempInstructor.getCourses());
    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor ID: " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("TempInstructor: " + tempInstructor);

        // Find courses for instructor
        List<Course> tempInstructorCourses = appDAO.findCoursesByInstructorId(theId);

        // Set the courses for the instructor
        tempInstructor.setCourses(tempInstructorCourses);

        System.out.println("TempInstructor Courses: " + tempInstructor.getCourses());
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        // Create Instructor
        Instructor tempInstructor = new Instructor("Pedro", "Monteiro", "pedrogmonteiro@teste.com");

        // Create Instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("www.youtube-channel.com", "Do Sports");

        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // create some courses
        Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
        Course tempCourse2 = new Course("The Pinball Masterclass");

        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        // Save the instructor
        // Isto também vai salvar os courses por causa do Cascade
        System.out.println("Saving instructor " + tempInstructor);
        System.out.println("The courses " + tempInstructor.getCourses());
        appDAO.save(tempInstructor);

    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int theId = 3;
        System.out.println("Finding instructor ID: " + theId);

        appDAO.deleteInstructorById(theId);
        System.out.println("Deleted...");

    }

    private void findInstructorDetail(AppDAO appDAO) {
        int theId = 3;
        System.out.println("Finding instructor ID: " + theId);

        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

        System.out.println("TempInstructor Detail: " + tempInstructorDetail);

    }

    private void deleteInstructor(AppDAO appDAO) {
        int theId = 2;
        System.out.println("Finding instructor ID: " + theId);

        appDAO.deleteInstructorById(theId);
        System.out.println("Deleted...");

    }

    private void findInstructor(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor ID: " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("TempInstructor: " + tempInstructor);
        System.out.println("The associated instructorDetails: " + tempInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {
        // Create Instructor
        Instructor tempInstructor = new Instructor("João2", "Monteiro", "joaogmonteiro@teste.com");

        // Create Instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("www.youtube-channel.com", "Do Sports");

        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // save the instructor
        System.out.println("Saving instructor " + tempInstructor);
        appDAO.save(tempInstructor);
    }
}
