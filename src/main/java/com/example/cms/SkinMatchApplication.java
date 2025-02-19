package com.example.cms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkinMatchApplication {

	private static Logger LOG = LoggerFactory
			.getLogger(SkinMatchApplication.class);  // need to change

	public static void main(String[] args) {
		SpringApplication.run(SkinMatchApplication.class, args);
		LOG.info("APPLICATION IS RUNNING");
	}
}
