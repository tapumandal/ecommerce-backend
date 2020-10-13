package com.tapumandal.ecommerce;

import com.tapumandal.ecommerce.service.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
@EnableWebSecurity
public class SikderEnterpriseApplication {
	public static void main(String[] args) {
		SpringApplication.run(SikderEnterpriseApplication.class, args);
	}

}
