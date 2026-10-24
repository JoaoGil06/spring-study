package com.joaogil.springboot.cruddatabase.service;

import com.joaogil.springboot.cruddatabase.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);
}
