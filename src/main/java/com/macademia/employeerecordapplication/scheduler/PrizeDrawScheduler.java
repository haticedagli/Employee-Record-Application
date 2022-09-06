package com.macademia.employeerecordapplication.scheduler;

import com.macademia.employeerecordapplication.service.PrizeDrawWinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PrizeDrawScheduler {

    @Autowired
    private PrizeDrawWinService prizeDrawWinService;

    /**
     * It runs every one hour, but if there is any winner for this month, not saving the winner.
     * Probably this function will save the winner every beginning of the new month.
     */
    @Scheduled(fixedDelay = 3600000, initialDelay = 10000)
    public void scheduleFixedDelayTask() {
        prizeDrawWinService.calculateWinner();
    }

}
