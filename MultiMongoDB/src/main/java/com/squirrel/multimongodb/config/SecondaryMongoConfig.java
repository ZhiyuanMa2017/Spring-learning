package com.squirrel.multimongodb.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableConfigurationProperties(MultipleMongoProperties.class)
@EnableMongoRepositories(basePackages = "com.squirrel.multimongodb.repository.secondary",
        mongoTemplateRef = "secondaryMongoTemplate")
public class SecondaryMongoConfig {
}
