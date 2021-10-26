package com.cliniconline.platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlatformApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(PlatformApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PlatformApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {}
	
}