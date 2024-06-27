package com.klc.daloopintegration;

import com.klc.daloopintegration.services.ConnectivityService;
import com.klc.daloopintegration.services.UsageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component

public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private ConnectivityService connectivityService;

    @Autowired
    private UsageService usageService;



    //Job runs every day at 2AM
    //@Scheduled(cron = "0 0 2 * * ?")

@Scheduled(fixedRate = 500)
    public void performTask() {
        logger.info("Starts job ");

        this.connectivityService.validateStationsIssues();
        this.usageService.inspectTransactionWithLessThan3MinOrMore12Hours();

        logger.info("Ends job");


    }
}
