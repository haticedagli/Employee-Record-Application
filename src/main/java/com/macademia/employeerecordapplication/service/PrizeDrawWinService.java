package com.macademia.employeerecordapplication.service;

import com.macademia.employeerecordapplication.exception.NotFoundException;
import com.macademia.employeerecordapplication.model.Employee;
import com.macademia.employeerecordapplication.model.PrizeDrawWin;
import com.macademia.employeerecordapplication.repository.EmployeeRepository;
import com.macademia.employeerecordapplication.repository.PrizeDrawWinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class PrizeDrawWinService {

    @Autowired
    private PrizeDrawWinRepository prizeDrawWinRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public PrizeDrawWin findWinnerForThisMonth(){
        return prizeDrawWinRepository.findTopByOrderByDateDesc()
                .orElseThrow(() -> new NotFoundException("Prize winner is not calculated for this month yet."));
    }

    public void calculateWinner(){
        Optional<PrizeDrawWin> lastWinOptional = prizeDrawWinRepository.findTopByOrderByDateDesc();

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);

        if(lastWinOptional.isPresent()) {
            PrizeDrawWin lastWin = lastWinOptional.get();
            if(month != lastWin.getMonth() || year != lastWin.getYear()){
                saveRandomWinner(month, year, date);
            }
        } else {
            saveRandomWinner(month, year, date);
        }
    }

    private void saveRandomWinner(int month, int year, Date date){
        Employee randomEmployee = employeeRepository.findRandomEmployee()
                .orElseThrow(() -> new NotFoundException("There is no employee to pick a winner"));

        PrizeDrawWin prizeDrawWin = new PrizeDrawWin(null, randomEmployee, month, year, date);

        prizeDrawWinRepository.save(prizeDrawWin);
    }
}
