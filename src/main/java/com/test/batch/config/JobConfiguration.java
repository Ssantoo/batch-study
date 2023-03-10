package com.test.batch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step passStep(){
        return this.stepBuilderFactory.get("passStep")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("테스트");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Job passJob(){
        return this.jobBuilderFactory.get("passJob")
                .start(passStep())
                .build();
    }



}
