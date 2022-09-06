package com.macademia.employeerecordapplication.service;

import com.macademia.employeerecordapplication.exception.BusinessException;
import com.macademia.employeerecordapplication.exception.NotFoundException;
import com.macademia.employeerecordapplication.model.Department;
import com.macademia.employeerecordapplication.model.dto.DepartmentRequest;
import com.macademia.employeerecordapplication.repository.DepartmentRepository;
import com.macademia.employeerecordapplication.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Department getDepartmentById(Long id){
        return departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Department not found."));
    }

    public void saveDepartment(DepartmentRequest departmentRequest){
        departmentRepository.save(departmentRequest.toDepartment());
    }
    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    public void deleteDepartment(Long id){
        if(employeeRepository.existsByDepartmentId(id)){
            throw new BusinessException("You have to remove all employees who is in this department.");
        }
        Department department = getDepartmentById(id);
        departmentRepository.delete(department);
    }

    public Department updateDepartment(Long id, DepartmentRequest departmentRequest){
        departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Department not found."));
        Department updateDepartment = departmentRequest.toDepartment();
        updateDepartment.setId(id);
        return departmentRepository.save(updateDepartment);
    }
}
