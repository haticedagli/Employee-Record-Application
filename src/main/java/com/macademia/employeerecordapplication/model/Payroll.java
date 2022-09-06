package com.macademia.employeerecordapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "payroll")
@NoArgsConstructor
@AllArgsConstructor

public class Payroll implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String businessRegistrationId;
    private String socialSecurityRegistrationNumber;
    private Integer totalNumberOfDaysPaid;
    private Double insuredsFee;
    private Double amountOfFeePaid;
    @Column(name="`month`")
    private Date month;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employeeId")
    private Employee employee;

}
