package com.klc.daloopintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan("com.klc.daloopintegration.mappers")
public class DaloopIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaloopIntegrationApplication.class, args);
	}

}
