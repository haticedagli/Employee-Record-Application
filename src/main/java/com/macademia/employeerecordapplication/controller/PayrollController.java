package com.macademia.employeerecordapplication.controller;

import com.macademia.employeerecordapplication.model.Payroll;
import com.macademia.employeerecordapplication.model.dto.PayrollRequest;
import com.macademia.employeerecordapplication.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payroll")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @GetMapping("/{id}")
    public Payroll get(@PathVariable Long id){
        return payrollService.getPayrollById(id);
    }

    @PostMapping
    public void save(@RequestBody PayrollRequest payrollRequest){
        payrollService.savePayroll(payrollRequest);
    }

    @PutMapping("/{id}")
    public Payroll update(@PathVariable Long id, @RequestBody PayrollRequest payrollRequest){
        return payrollService.updatePayroll(id, payrollRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        payrollService.deletePayrollById(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Payroll> getEmployeePayrolls(@PathVariable Long employeeId){
        return payrollService.getEmployeePayrolls(employeeId);
    }

}
