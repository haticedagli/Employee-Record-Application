package com.macademia.employeerecordapplication.controller;

import com.macademia.employeerecordapplication.model.PrizeDrawWin;
import com.macademia.employeerecordapplication.service.PrizeDrawWinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prize-draw")
public class PrizeDrawController {

    @Autowired
    private PrizeDrawWinService prizeDrawWinService;

    @GetMapping("/winner")
    public PrizeDrawWin getWinnerForThisMonth(){
        return prizeDrawWinService.findWinnerForThisMonth();
    }
}
