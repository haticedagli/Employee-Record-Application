package com.macademia.employeerecordapplication.model.dto;

import com.macademia.employeerecordapplication.model.Office;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class OfficeRequest {

    private String name;
    private String location;
    private String address;
    private Long capacity;

    public Office toOffice(){
        return new Office(
           null,
           this.name,
           this.location,
           this.address,
           this.capacity,
           new ArrayList<>()
        );
    }

}
