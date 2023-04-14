package com.mediscreen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MediscreenApplication {
	private static final Logger logger = LogManager.getLogger("MediscreenApplication");

	public static void main(String[] args) {
		SpringApplication.run(MediscreenApplication.class, args);
	}

}
