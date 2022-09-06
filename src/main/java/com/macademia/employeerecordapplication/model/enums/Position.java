package com.macademia.employeerecordapplication.model.enums;

import com.macademia.employeerecordapplication.exception.NotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Position {
    SOFTWARE_ENGINEER,
    QUALITY_ENGINEER,
    TALENT_HUNTER,
    DATA_ENGINEER,
    ;

    public static Position findByName(String name){
        return Arrays.stream(Position.values())
                .filter(k-> k.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Position not found. Available positions are " + Arrays.toString(Position.values())));
    }
}
