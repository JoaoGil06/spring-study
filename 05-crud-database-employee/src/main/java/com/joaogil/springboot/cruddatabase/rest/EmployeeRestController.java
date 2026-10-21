package com.joaogil.springboot.cruddatabase.rest;

import com.joaogil.springboot.cruddatabase.entity.Employee;
import com.joaogil.springboot.cruddatabase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;
    private JsonMapper jsonMapper;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService, JsonMapper theJsonMapper) {
        this.employeeService = theEmployeeService;
        this.jsonMapper = theJsonMapper;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable  int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        return theEmployee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {
        // Só para o caso de alguém passar um ID no body, pomos como 0 porque assim nunca damos update
        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    // add mapping for PUT /employees - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    @PatchMapping("employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, @RequestBody Map<String, Object> patchPayload) {

        Employee foundEmployee = employeeService.findById(employeeId);

        // Throw exception if employee is not found
        if(foundEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        // Throw exception if request contains "id" in body
        if(patchPayload.containsKey("id")) {
            throw new RuntimeException("Employee id not allowed in request body");
        }

        Employee patchedEmployee = jsonMapper.updateValue(foundEmployee, patchPayload);

        Employee dbEmployee = employeeService.save(patchedEmployee);

        return dbEmployee;
    }

    @DeleteMapping("employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee foundEmployee = employeeService.findById(employeeId);

        // Throw exception if employee is not found
        if(foundEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee id successfully - " + employeeId;
    }
}
