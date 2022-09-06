package com.macademia.employeerecordapplication.model.enums;

import com.macademia.employeerecordapplication.exception.NotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MaritalStatus {
    SINGLE,
    MARRIED,
    ;

    public static MaritalStatus findByName(String name){
        return Arrays.stream(MaritalStatus.values())
                .filter(k-> k.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Marital status not found. Available marital statuses are " + Arrays.toString(MaritalStatus.values())));
    }
}
