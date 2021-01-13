package com.tictactoe.gameserver.jobs;

import com.tictactoe.gameserver.domain.GamingPool;
import com.tictactoe.gameserver.repository.GamingPoolRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class JobConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DatabaseCleanUpTasklet databaseCleanUpTasklet;

    @Bean(name = "databaseCleanUpJob")
    public Job databaseCleanUpJob(DatabaseCleanUpJobListener listener, Step step) {
        return jobBuilderFactory
                .get("Database CleanUp Job")
                .listener(listener)
                .start(step)
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory
                .get("Database CleanUp Step")
                .tasklet(databaseCleanUpTasklet)
                .build();
    }
}