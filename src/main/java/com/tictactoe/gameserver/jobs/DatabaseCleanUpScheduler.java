package com.tictactoe.gameserver.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * DatabaseCleanUpScheduler class for DB cleanup jobs.
 *
 * @author Yogesh Gaikwad
 * @since 1.0.0
 */
@Component
public class DatabaseCleanUpScheduler {

    @Autowired
    private Job databaseCleanUpJob;

    @Autowired
    private JobLauncher jobLauncher;

    @Scheduled(fixedDelay = 10000)
    public void runCleanUpDatabaseJob() {
        JobParameters params = new JobParametersBuilder().addLong("jobId", System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(databaseCleanUpJob, params);
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }

}