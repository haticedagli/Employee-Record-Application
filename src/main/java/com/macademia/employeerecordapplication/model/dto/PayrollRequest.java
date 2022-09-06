package com.macademia.employeerecordapplication.model.dto;

import com.macademia.employeerecordapplication.model.Employee;
import com.macademia.employeerecordapplication.model.Payroll;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PayrollRequest {

    private Long employeeId;
    private String businessRegistrationId;
    private String socialSecurityRegistrationNumber;
    private Integer totalNumberOfDaysPaid;
    private Double insuredsFee;
    private Double amountOfFeePaid;
    private Date month;

    public Payroll toPayroll(Employee employee){
        return new Payroll(
             null,
             this.businessRegistrationId,
             this.socialSecurityRegistrationNumber,
             this.totalNumberOfDaysPaid,
             this.insuredsFee,
             this.amountOfFeePaid,
             this.month,
             employee
        );

    }
}
