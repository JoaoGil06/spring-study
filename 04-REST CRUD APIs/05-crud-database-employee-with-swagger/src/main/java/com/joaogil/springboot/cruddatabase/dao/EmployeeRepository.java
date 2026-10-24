package com.joaogil.springboot.cruddatabase.dao;

import com.joaogil.springboot.cruddatabase.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // O CRUD está feito
}
