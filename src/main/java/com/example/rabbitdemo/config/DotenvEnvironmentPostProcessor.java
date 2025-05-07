package com.example.rabbitdemo.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

public class DotenvEnvironmentPostProcessor implements EnvironmentPostProcessor {
    
    private static final Logger logger = LoggerFactory.getLogger(DotenvEnvironmentPostProcessor.class);

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        try {
            Dotenv dotenv = Dotenv.configure()
                    .ignoreIfMalformed()
                    .ignoreIfMissing()
                    .load();

            Map<String, Object> envMap = new HashMap<>();
            dotenv.entries().forEach(entry -> envMap.put(entry.getKey(), entry.getValue()));

            // Print loaded environment variables
            logger.info("=== Loading Environment Variables ===");
            logger.info("RABBITMQ_HOST: {}", dotenv.get("RABBITMQ_HOST"));
            logger.info("RABBITMQ_PORT: {}", dotenv.get("RABBITMQ_PORT"));
            logger.info("RABBITMQ_USERNAME: {}", dotenv.get("RABBITMQ_USERNAME"));
            logger.info("RABBITMQ_PASSWORD: {}", dotenv.get("RABBITMQ_PASSWORD"));
            logger.info("RABBITMQ_VHOST: {}", dotenv.get("RABBITMQ_VHOST"));
            logger.info("SERVER_PORT: {}", dotenv.get("SERVER_PORT"));
            logger.info("================================");

            // Add the properties to the environment
            MutablePropertySources propertySources = environment.getPropertySources();
            propertySources.addFirst(new MapPropertySource("dotenvProperties", envMap));

        } catch (Exception e) {
            logger.error("Error loading .env file: {}", e.getMessage(), e);
        }
    }
} 