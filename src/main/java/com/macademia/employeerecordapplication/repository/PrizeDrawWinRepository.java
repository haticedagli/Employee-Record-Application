package com.macademia.employeerecordapplication.repository;

import com.macademia.employeerecordapplication.model.PrizeDrawWin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrizeDrawWinRepository extends JpaRepository<PrizeDrawWin, Long> {
    Optional<PrizeDrawWin> findTopByOrderByDateDesc();
}
