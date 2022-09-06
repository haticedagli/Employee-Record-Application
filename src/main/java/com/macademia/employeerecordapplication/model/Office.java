package com.macademia.employeerecordapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "office")
@AllArgsConstructor
@NoArgsConstructor
public class Office implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String location;
    private String address;
    private Long capacity;
    @JsonIgnore
    @OneToMany(mappedBy="office", fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();
}
