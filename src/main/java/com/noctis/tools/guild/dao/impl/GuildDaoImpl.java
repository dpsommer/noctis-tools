package com.noctis.tools.guild.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.noctis.tools.guild.Guild;
import com.noctis.tools.guild.ResourceRequest;
import com.noctis.tools.guild.dao.GuildDao;
import com.noctis.tools.web.user.dao.impl.UserDaoImpl;

@Repository("guildDao")
public class GuildDaoImpl implements GuildDao {

  private static final Logger logger = LoggerFactory
      .getLogger(UserDaoImpl.class);

  private final MongoOperations mongoOps;

  static final String GUILD_COLLECTION = "guild";
  static final String REQUEST_COLLECTION = "guild.requests";
  
  @Autowired
  public GuildDaoImpl(@Qualifier("mongoOperations") MongoOperations mongoOps) {
    this.mongoOps = mongoOps;
  }
  
  @Override
  public Guild getGuild() {
    Guild guild = null;

    try {
      List<Guild> guilds = mongoOps.findAll(Guild.class, GUILD_COLLECTION);
      if (guilds.isEmpty()) {
        guild = new Guild();
        addGuild(guild);
        guild.init();
      } else {
        guild = guilds.get(0);
      }
      logger.info("Successfully retrieved gold from guild object");
    } catch (Exception e) {
      logger.error("Failed to retrieve gold from guild object", e);
    }

    return guild;
  }
  
  @Override
  public void addGuild(Guild guild) {
    try {
      mongoOps.insert(guild, GUILD_COLLECTION);
      logger.info("Successfully added guild object");
    } catch (Exception e) {
      logger.error("Failed to add guild object", e);
    }
  }

  @Override
  public void setFunds(Guild guild) {
    try {
      List<Guild> guilds = mongoOps.findAll(Guild.class, GUILD_COLLECTION);
      if (guilds.isEmpty()) {
        addGuild(guild);
      } else {
        mongoOps.save(guild);
      }
      logger.info("Successfully set guild gold");
    } catch (Exception e) {
      logger.error("Failed to set guild gold", e);
    }
  }
  
  @Override
  public void addRequest(ResourceRequest request) {
    try {
      mongoOps.insert(request, REQUEST_COLLECTION);
      logger.info("Successfully added request");
    } catch (Exception e) {
      logger.error("Failed to add request", e);
    }
  }

  @Override
  public List<ResourceRequest> getRequests() {
    List<ResourceRequest> requests = new ArrayList<>();
    
    try {
      requests = mongoOps.findAll(ResourceRequest.class,
          REQUEST_COLLECTION);
      logger.info("Successfully retrieved requests");
    } catch (Exception e) {
      logger.error("Failed to retrieve requests", e);
    }
    
    return requests;
  }

  @Override
  public void updateRequest(ResourceRequest request) {
    try {
      mongoOps.save(request, REQUEST_COLLECTION);
      logger.info("Successfully updated request");
    } catch (Exception e) {
      logger.error("Failed to update request", e);
    }
  }

  @Override
  public void deleteRequest(ResourceRequest request) {
    try {
      mongoOps.remove(request, REQUEST_COLLECTION);
      logger.info("Successfully removed request");
    } catch (Exception e) {
      logger.error("Failed to remove request", e);
    }
  }

}
