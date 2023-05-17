package com.alert.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "endpoint")
public class EndpointProperties {

    private String patientUri;

    private String noteUri;

}