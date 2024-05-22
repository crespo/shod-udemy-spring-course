package com.raulcrespo.springcoredemo.config;

import com.raulcrespo.springcoredemo.common.Coach;
import com.raulcrespo.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("aquatic")
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
