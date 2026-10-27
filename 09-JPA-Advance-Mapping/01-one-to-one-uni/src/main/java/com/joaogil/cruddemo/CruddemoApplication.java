package com.joaogil.cruddemo;

import com.joaogil.cruddemo.dao.AppDAO;
import com.joaogil.cruddemo.entity.Instructor;
import com.joaogil.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
            deleteInstructorDetail(appDAO);

        };
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
