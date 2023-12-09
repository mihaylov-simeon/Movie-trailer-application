package com.moveiapp.final_exam_projectmovieapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

// Configuration used to allow requests on a specific origin,
// different from the one which the server-side is running on currently
@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000"); // Only origin 3000
        config.addAllowedHeader("*"); // All headers
        config.addAllowedMethod("*"); // All methods
        source.registerCorsConfiguration("/**", config); // All paths
        return new CorsFilter(source);
    }
}
