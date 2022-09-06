package com.macademia.employeerecordapplication.model.dto;

import com.macademia.employeerecordapplication.model.Department;
import com.macademia.employeerecordapplication.model.Employee;
import com.macademia.employeerecordapplication.model.Office;
import com.macademia.employeerecordapplication.model.enums.Gender;
import com.macademia.employeerecordapplication.model.enums.MaritalStatus;
import com.macademia.employeerecordapplication.model.enums.Position;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
public class EmployeeRequest {
    @NotNull
    private String name;
    private String surname;
    private String identityNumber;
    private String phone;
    private String address;
    private String gender;
    private String position;
    private String photo;
    private String maritalStatus;
    private Date startDate;
    private Date endDate;
    @NotNull
    private Long departmentId;
    @NotNull
    private Long officeId;

    public Employee toEmployee(Department department, Office office){
        return new Employee(
                null,
                this.name,
                this.surname,
                this.identityNumber,
                this.phone,
                this.address,
                Gender.findByName(this.gender),
                Position.findByName(this.position),
                this.phone,
                MaritalStatus.findByName(this.maritalStatus),
                this.startDate,
                this.endDate,
                department,
                office,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }
}
