package com.bt.marketplace.partnercredentials.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig extends AbstractMongoConfigure {
    @Autowired
    private Environment env;

    @Primary
    @Bean(name = "mongoTemplate")
    @Override
    public MongoTemplate getMongoTemplate() throws Exception {
        return new MongoTemplate(mongoDatabaseFactory());
    }
}

