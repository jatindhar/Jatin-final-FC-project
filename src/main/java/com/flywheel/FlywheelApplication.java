package com.flywheel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@ComponentScan(basePackages = {"com.flywheel"})

public class FlywheelApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlywheelApplication.class, args);
	}

}
