package com.bt.marketplace.partnercredentials.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Getter
@Setter
abstract class AbstractMongoConfigure {

    @Value("${spring.data.mongodb.uri}")
    private String url;

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(){
        return new SimpleMongoClientDatabaseFactory(url);
    }

    abstract public MongoTemplate getMongoTemplate() throws Exception;
}
