package com.prod.rabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RabbitApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext app = SpringApplication.run(RabbitApplication.class, args);
	}

}
