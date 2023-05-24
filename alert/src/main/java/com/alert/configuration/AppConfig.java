package com.alert.configuration;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    @Scope("singleton")
    public Gson gson() {
        return new Gson();
    }
}
