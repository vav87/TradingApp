package com.example.tradingapp.config;

import com.example.tradingapp.thirdparty.Algo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlgoConfig {

    @Bean
    public Algo algo() {
        return new Algo();
    }
}
