package com.joaogil.cruddemo.rest;

import com.joaogil.cruddemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // Define @PostConstruct to load the student data ... only once
    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();

        Student student1 = new Student("João", "Gil");
        Student student2 = new Student("Rita", "Ferreira");
        Student student3 = new Student("Pedro", "Gil");

        theStudents.add(student1);
        theStudents.add(student2);
        theStudents.add(student3);
    }

    // define endpoint for "/students" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }

    // define endpoint or "/students/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        // Check the studentId against list size
        if((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        // Just index into the list ... get it simple for now;
        Student theStudent = theStudents.get(studentId);

        return theStudent;
    }

    // Add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
        // Create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // Return the ResponseEntity
        ResponseEntity<StudentErrorResponse> responseEntity = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

        return responseEntity;
    }

    // Add another exception handler ... to catch any exception (Catch everything that is generic)
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
        // Create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // Return the ResponseEntity
        ResponseEntity<StudentErrorResponse> responseEntity = new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

        return responseEntity;
    }
}


