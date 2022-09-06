package com.macademia.employeerecordapplication.model.enums;

import com.macademia.employeerecordapplication.exception.NotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Gender {
    FEMALE,
    MALE,
    ;

    public static Gender findByName(String name){
        return Arrays.stream(Gender.values())
                .filter(k-> k.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Gender not found. Available genders are " + Arrays.toString(Gender.values())));
    }
}
