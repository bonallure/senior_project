package com.cliniconline.platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


 @SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
//@SpringBootApplication
public class PlatformApplication{

	public static final Logger LOGGER = LoggerFactory.getLogger(PlatformApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PlatformApplication.class, args);
	}
}