package com.macademia.employeerecordapplication.service;

import com.macademia.employeerecordapplication.exception.NotFoundException;
import com.macademia.employeerecordapplication.model.Department;
import com.macademia.employeerecordapplication.model.Employee;
import com.macademia.employeerecordapplication.model.Office;
import com.macademia.employeerecordapplication.model.Payroll;
import com.macademia.employeerecordapplication.model.dto.EmployeeRequest;
import com.macademia.employeerecordapplication.repository.DepartmentRepository;
import com.macademia.employeerecordapplication.repository.EmployeeRepository;
import com.macademia.employeerecordapplication.repository.OfficeRepository;
import com.macademia.employeerecordapplication.repository.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private OfficeRepository officeRepository;
    @Autowired
    private PayrollRepository payrollRepository;

    public void saveEmployee(EmployeeRequest employeeRequest){
        Department department = departmentRepository.findById(employeeRequest.getDepartmentId())
                .orElseThrow(() -> new NotFoundException("Department not found!"));
        Office office = officeRepository.findById(employeeRequest.getOfficeId())
                .orElseThrow(() -> new NotFoundException("Office not found!"));
        employeeRepository.save(employeeRequest.toEmployee(department, office));
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found!"));
    }

    public void deleteEmployeeById(Long id){
        List<Payroll> payrolls = payrollRepository.findByEmployeeId(id);
        if(!CollectionUtils.isEmpty(payrolls)){
            payrollRepository.deleteAll(payrolls);
        }
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Long id, EmployeeRequest employeeRequest){
        employeeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Employee not found!"));
        Department department = departmentRepository.findById(employeeRequest.getDepartmentId())
                .orElseThrow(() -> new NotFoundException("Department not found!"));
        Office office = officeRepository.findById(employeeRequest.getOfficeId())
                .orElseThrow(() -> new NotFoundException("Office not found!"));
        Employee employee = employeeRequest.toEmployee(department, office);
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public void updateOfficeOfEmployeesByDepartmentId(Long departmentId, Long officeId){
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("Department not found!"));
        Office office = officeRepository.findById(officeId)
                .orElseThrow(() -> new NotFoundException("Office not found!"));

        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
        employees.forEach(employee -> {
            employee.setOffice(office);
        });
        employeeRepository.saveAll(employees);
    }
}
