package com.macademia.employeerecordapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.macademia.employeerecordapplication.model.enums.Gender;
import com.macademia.employeerecordapplication.model.enums.MaritalStatus;
import com.macademia.employeerecordapplication.model.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String identityNumber;
    private String phone;
    private String address;
    @Enumerated
    private Gender gender;
    @Enumerated
    private Position position;
    private String photo;
    @Enumerated
    private MaritalStatus maritalStatus;
    private Date startDate;
    private Date endDate;
    @ManyToOne
    @JoinColumn(name="departmentId")
    private Department department;
    @ManyToOne
    @JoinColumn(name="officeId")
    private Office office;
    @JsonIgnore
    @OneToMany(mappedBy="employee", fetch = FetchType.LAZY)
    private List<Payroll> payrolls;
    @JsonIgnore
    @OneToMany(mappedBy="employee", fetch = FetchType.LAZY)
    private List<PrizeDrawWin> drawWins;

}
