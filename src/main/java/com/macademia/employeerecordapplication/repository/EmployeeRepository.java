package com.macademia.employeerecordapplication.repository;

import com.macademia.employeerecordapplication.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByDepartmentId(Long id);
    boolean existsByOfficeId(Long id);

    List<Employee> findByDepartmentId(Long id);

    @Query(nativeQuery=true, value="SELECT * FROM employee ORDER BY random() LIMIT 1")
    Optional<Employee> findRandomEmployee();
}
