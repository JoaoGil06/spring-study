package com.joaogil.springboot.cruddatabase.dao;

import com.joaogil.springboot.cruddatabase.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);



}
