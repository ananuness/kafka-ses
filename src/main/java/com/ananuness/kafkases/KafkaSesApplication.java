package com.ananuness.kafkases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class KafkaSesApplication {
	public static void main(String[] args) {
		SpringApplication.run(KafkaSesApplication.class, args);
	}

}
