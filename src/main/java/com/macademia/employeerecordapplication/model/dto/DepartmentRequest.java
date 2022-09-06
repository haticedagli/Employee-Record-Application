package com.macademia.employeerecordapplication.model.dto;

import com.macademia.employeerecordapplication.model.Department;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Getter
@Setter
public class DepartmentRequest {
    @NotNull
    private String name;
    @NotNull
    private String details;

    public Department toDepartment(){
        return new Department(
                null,
                this.name,
                this.details,
                new ArrayList<>()
        );
    }
}
