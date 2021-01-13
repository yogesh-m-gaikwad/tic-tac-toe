package com.tictactoe.gameserver.jobs;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCleanUpJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Executing job id " + jobExecution.getId() + " STARTED.");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("Executing job id " + jobExecution.getId() + " COMPLETED.");
        }
        else{
            System.out.println("Executing job id " + jobExecution.getId() + " in status: " + jobExecution.getStatus() + ".");
        }
    }
}