package com.noctis.tools.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Configuration class for mongodb instance
 * 
 * @author Duncan Sommerville
 * 
 */
@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

  private static final Logger logger = LoggerFactory
      .getLogger(MongoConfig.class);

  private static String DEFAULT_DB;

  @Override
  protected String getDatabaseName() {
    return DEFAULT_DB;
  }

  @Override
  public String getMappingBasePackage() {
    return "com.noctis.tools.farm";
  }

  @Override
  public @Bean Mongo mongo() {
    MongoClient mongo = null;
    try {
      MongoClientURI uri = new MongoClientURI(System.getenv("MONGOHQ_URL"));
      DEFAULT_DB = uri.getDatabase();
      mongo = new MongoClient(uri);
    } catch (Exception e) {
      try {
        mongo = new MongoClient();
        DEFAULT_DB = "noctis-test";
      } catch (Exception e1) {
        logger.error(e.getMessage());
      }
    }
    return mongo;
  }

  public @Bean MongoDbFactory mongoDbFactory() {
    return new SimpleMongoDbFactory(mongo(), getDatabaseName());
  }

  public @Bean MongoTemplate mongoTemplate() {
    MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
    return mongoTemplate;
  }

  public @Bean MongoOperations mongoOperations() {
    return mongoTemplate();
  }

}
