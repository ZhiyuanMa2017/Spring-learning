package com.squirrel.multimongodb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MultipleMongoConfig {

    @Autowired
    private MultipleMongoProperties multipleMongoProperties;

    @Primary
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate primaryMongoTemplate() {
        return new MongoTemplate(primaryFactory(this.multipleMongoProperties.getPrimary()));
    }

    @Bean
    @Qualifier("secondaryMongoTemplate")
    public MongoTemplate secondaryMongoTemplate() {
        return new MongoTemplate(secondaryFactory(this.multipleMongoProperties.getSecondary()));
    }

    @Bean
    @Primary
    public MongoDatabaseFactory primaryFactory(MongoProperties mongoProperties) {
        return new SimpleMongoClientDatabaseFactory(
                mongoProperties.getUri() + "/" + mongoProperties.getDatabase());
    }

    @Bean
    public MongoDatabaseFactory secondaryFactory(MongoProperties mongoProperties) {
        return new SimpleMongoClientDatabaseFactory(
                mongoProperties.getUri() + "/" + mongoProperties.getDatabase());
    }
}
