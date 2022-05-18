package com.simplon.testWebapp.repository;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.simplon.testwebapp")
public class CustomProperties {
	private String apiUrl;

}
