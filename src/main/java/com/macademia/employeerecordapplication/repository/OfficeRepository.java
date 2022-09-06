package com.macademia.employeerecordapplication.repository;

import com.macademia.employeerecordapplication.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
}
