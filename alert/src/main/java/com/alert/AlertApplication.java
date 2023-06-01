package com.alert;

import com.alert.configuration.EndpointProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(EndpointProperties.class)
public class AlertApplication {
	private static final Logger logger = LogManager.getLogger("AlertApplication");

	public static void main(String[] args) {
		SpringApplication.run(AlertApplication.class, args);
	}

}
