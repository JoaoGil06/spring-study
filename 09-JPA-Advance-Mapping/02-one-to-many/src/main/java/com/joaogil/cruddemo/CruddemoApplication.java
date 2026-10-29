package com.joaogil.cruddemo;

import com.joaogil.cruddemo.dao.AppDAO;
import com.joaogil.cruddemo.entity.Course;
import com.joaogil.cruddemo.entity.Instructor;
import com.joaogil.cruddemo.entity.InstructorDetail;
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

        };
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
