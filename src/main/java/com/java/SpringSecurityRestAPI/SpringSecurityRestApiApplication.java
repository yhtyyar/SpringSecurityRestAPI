package com.java.SpringSecurityRestAPI;

import com.java.SpringSecurityRestAPI.properties.S3Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {S3Properties.class})
public class SpringSecurityRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityRestApiApplication.class, args);
	}

}
