package com.joaogil.cruddemo;

import com.joaogil.cruddemo.dao.StudentDAO;
import com.joaogil.cruddemo.entity.Student;
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
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
            // createStudent(studentDAO);

             createMultipleStudents(studentDAO);

            // readStudent(studentDAO);

            // queryForStudents(studentDAO);

            // queryForStudentsByLastName(studentDAO);

            // updateStudent(studentDAO);

            // deleteStudent(studentDAO);

            // deleteAllStudents(studentDAO);
        };
    }

    private void deleteAllStudents(StudentDAO studentDAO) {
        System.out.println("Apagar todos os estudantes");

        int numberOfStudentsDeleted = studentDAO.deleteAll();

        System.out.println("Foram apagados " + numberOfStudentsDeleted + " estudantes");
    }

    private void deleteStudent (StudentDAO studentDAO) {
        Integer id = 6;
        System.out.println("Apagar o estudante com o ID: " + id);

        studentDAO.delete(id);
    }

    private void updateStudent(StudentDAO studentDAO) {
        // Create a new student and save him
        Student theStudent = new Student("Johny", "Doe", "johnydoe@teste.com");
        studentDAO.save(theStudent);

        // Retrieve student based on the id: primary key
        studentDAO.findById(theStudent.getId());
        System.out.println("O estudante é: " + theStudent);

        // change first name to "Scooby"
        theStudent.setFirstName("Scooby");

        // update the student
        studentDAO.update(theStudent);

        // display the updated student
        System.out.println("Agora o estudante foi editado e é: " + theStudent);
    }

    private void queryForStudentsByLastName(StudentDAO studentDAO)  {
        // Get a list of students
        List<Student> theStudents = studentDAO.findByLastName("Doe");

        // Display the list
        for(Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }

    private void queryForStudents(StudentDAO studentDAO) {
        // Get a list of students
        List<Student> theStudents = studentDAO.findAll();

        // Display list of students
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }

    private void readStudent(StudentDAO studentDAO) {
        // Create a student object
        System.out.println("A criar o novo object de estudante...");
        Student tempStudent1 = new Student("John", "Doe", "john@gmail.com");

        // Save the student
        System.out.println("A guardar o estudante...");
        studentDAO.save(tempStudent1);

        // Display the ID of the saved student
        Integer id = tempStudent1.getId();
        System.out.println("Estudante guardado. O ID gerado: " + id);

        // Retrieve student based on the id: primary key
        Student foundStudent = studentDAO.findById(id);

        // Display Student
        System.out.println("O estudante encontrado: " + foundStudent.toString());
    }

    private void createMultipleStudents(StudentDAO studentDAO) {
        // Create the student object
        System.out.println("A criar os novos object de 3 estudantes...");
        Student tempStudent1 = new Student("John", "Doe", "john@gmail.com");
        Student tempStudent2 = new Student("Mary", "Public", "mary@gmail.com");
        Student tempStudent3 = new Student("Tina", "Doe", "Tina@gmail.com");

        // Save the student object
        System.out.println("A guardar os estudantes...");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);
    }

    private void createStudent(StudentDAO studentDAO) {
        // Create the student object
        System.out.println("A criar o novo object de estudante...");
        Student tempStudent = new Student("João", "Monteiro", "joao@gmail.com");

        // Save the student object
        System.out.println("A guardar o estudante...");
        studentDAO.save(tempStudent);

        // Display id of the saved student
        System.out.println("Estudante guardado. O ID gerado: " + tempStudent.getId());
    }
}
