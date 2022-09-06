package com.macademia.employeerecordapplication.controller;

import com.macademia.employeerecordapplication.model.Employee;
import com.macademia.employeerecordapplication.model.dto.EmployeeRequest;
import com.macademia.employeerecordapplication.model.dto.UpdateOfficeRequest;
import com.macademia.employeerecordapplication.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    public EmployeeService employeeService;

    @GetMapping("/{id}")
    public Employee get(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public void save(@RequestBody EmployeeRequest employeeRequest){
        employeeService.saveEmployee(employeeRequest);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest){
        return employeeService.updateEmployee(id, employeeRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
    }

    @GetMapping
    public List<Employee> getAll(){
        return employeeService.getAllEmployees();
    }

    @PutMapping("/department/{departmentId}/office")
    public void updateOfficeByDepartmentId(@PathVariable Long departmentId, @RequestBody UpdateOfficeRequest updateOfficeRequest){
        employeeService.updateOfficeOfEmployeesByDepartmentId(departmentId, updateOfficeRequest.getOfficeId());
    }

}
