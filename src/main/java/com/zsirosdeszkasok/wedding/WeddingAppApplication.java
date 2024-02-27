package com.zsirosdeszkasok.wedding;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class WeddingAppApplication {

	private final String[] allowedOrigins;
	private final String[] allowedMethods;

	public WeddingAppApplication(@Value("${CORS_ALLOWED_ORIGINS}") String[] allowedOrigins,
								 @Value("${CORS_ALLOWED_METHODS}") String[] allowedMethods) {
		this.allowedOrigins = allowedOrigins;
		this.allowedMethods = allowedMethods;
	}

	public static void main(String[] args) {
		SpringApplication.run(WeddingAppApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsMappingConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("GET", "PUT", "POST")
						.allowedOrigins(allowedOrigins);
			}
		};
	}
}
