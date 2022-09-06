package com.macademia.employeerecordapplication.controller;

import com.macademia.employeerecordapplication.model.Department;
import com.macademia.employeerecordapplication.model.dto.DepartmentRequest;
import com.macademia.employeerecordapplication.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{id}")
    public Department get(@PathVariable Long id){
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    public void save(@RequestBody DepartmentRequest departmentRequest){
        departmentService.saveDepartment(departmentRequest);
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable Long id, @RequestBody DepartmentRequest departmentRequest){
        return departmentService.updateDepartment(id, departmentRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        departmentService.deleteDepartment(id);
    }

    @GetMapping
    public List<Department> getAll(){
        return departmentService.getAllDepartments();
    }

}
