package com.joaogil.cruddemo.rest;

import com.joaogil.cruddemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        // Just index into the list ... get it simple for now;
        Student theStudent = theStudents.get(studentId);

        return theStudent;
    }
}
