package com.backend.quizapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class QuizappApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizappApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // Matches all paths
						.allowedMethods("*") // Allows all HTTP methods (GET, POST, etc.)
						.allowedOrigins("http://localhost:5173") // Frontend running on Vite
						.allowedHeaders("*"); // Allows all headers
			}
		};
	}
}
