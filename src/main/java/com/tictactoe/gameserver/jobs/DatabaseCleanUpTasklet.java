package com.tictactoe.gameserver.jobs;

import com.tictactoe.gameserver.domain.GamingPool;
import com.tictactoe.gameserver.repository.GamingPoolRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class DatabaseCleanUpTasklet implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseCleanUpTasklet.class);

    @Autowired
    private final GamingPoolRepository gamingPoolRepository;

    public DatabaseCleanUpTasklet(GamingPoolRepository gamingPoolRepository) {
        this.gamingPoolRepository = gamingPoolRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        LocalDateTime aMinuteAgo = LocalDateTime.now().minusSeconds(60);
        List<GamingPool> oldRecords = gamingPoolRepository.findAllByWaitingForPairTrueAndCreatedOnIsLessThan(aMinuteAgo);
        oldRecords = oldRecords.stream().peek(gamingPool -> gamingPool.setWaitingForPair(false)).collect(Collectors.toList());
        logger.debug("DatabaseCleanUpTasklet: Filtered records: " + oldRecords.size());
        gamingPoolRepository.saveAll(oldRecords);
        logger.debug("DatabaseCleanUpTasklet: Updated completed.");
        return RepeatStatus.FINISHED;
    }
}
