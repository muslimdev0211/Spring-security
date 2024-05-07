package com.muslimdev.dars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DarsApplication.class, args);
	}

}
