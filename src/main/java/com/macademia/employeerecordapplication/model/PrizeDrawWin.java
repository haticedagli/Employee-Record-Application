package com.macademia.employeerecordapplication.model;

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
@Table(name = "prizedrawwin")
@AllArgsConstructor
@NoArgsConstructor
public class PrizeDrawWin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="employeeId")
    private Employee employee;
    @Column(name="`month`")
    private Integer month;
    @Column(name="`year`")
    private Integer year;
    private Date date;

}
