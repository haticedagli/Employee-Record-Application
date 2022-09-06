package com.macademia.employeerecordapplication.service;

import com.macademia.employeerecordapplication.exception.NotFoundException;
import com.macademia.employeerecordapplication.model.Employee;
import com.macademia.employeerecordapplication.model.Payroll;
import com.macademia.employeerecordapplication.model.dto.PayrollRequest;
import com.macademia.employeerecordapplication.repository.EmployeeRepository;
import com.macademia.employeerecordapplication.repository.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public Payroll getPayrollById(Long id){
        return payrollRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Payroll not found!"));
    }

    public void savePayroll(PayrollRequest payrollRequest){
        Employee employee = employeeRepository.findById(payrollRequest.getEmployeeId())
                .orElseThrow(() -> new NotFoundException("Employee not found!"));
        payrollRepository.save(payrollRequest.toPayroll(employee));
    }

    public List<Payroll> getEmployeePayrolls(Long employeeId){
        return payrollRepository.findByEmployeeId(employeeId);
    }

    public void deletePayrollById(Long id){
        Payroll payroll = payrollRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Payroll not found!"));
        payrollRepository.delete(payroll);
    }

    public Payroll updatePayroll(Long id, PayrollRequest payrollRequest){
        payrollRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Payroll not found!"));
        Employee employee = employeeRepository.findById(payrollRequest.getEmployeeId())
                .orElseThrow(() -> new NotFoundException("Employee not found!"));
        Payroll payroll = payrollRequest.toPayroll(employee);
        payroll.setId(id);
        return payrollRepository.save(payroll);
    }
}
