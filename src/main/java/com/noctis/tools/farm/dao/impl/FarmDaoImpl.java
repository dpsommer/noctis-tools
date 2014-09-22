package com.noctis.tools.farm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.noctis.tools.farm.dao.FarmDao;
import com.noctis.tools.farm.scheduler.Timer;
import com.noctis.tools.web.user.dao.impl.UserDaoImpl;

@Repository("farmDao")
public class FarmDaoImpl implements FarmDao {
  
  private static final Logger logger = LoggerFactory
      .getLogger(UserDaoImpl.class);

  private final MongoOperations mongoOps;

  static final String FARM_COLLECTION = "farm";
  
  static final String TIMER_COLLECTION = "farm.timers";

  @Autowired
  public FarmDaoImpl(@Qualifier("mongoOperations") MongoOperations mongoOps) {
    this.mongoOps = mongoOps;
  }
  
  @Override
  public List<Timer> getTimers() {
    List<Timer> timers = new ArrayList<>();

    try {
      timers = mongoOps.findAll(Timer.class, TIMER_COLLECTION);
      logger.info("Successfully retrieved timers");
    } catch (Exception e) {
      logger.error("Failed to retrieve timers", e);
    }

    return timers;
  }

  @Override
  public void addTimer(Timer timer) {
    try {
      mongoOps.insert(timer, TIMER_COLLECTION);
      logger.info("Successfully added " + timer.getPlantable() 
          + " timer at " + timer.getLocation());
    } catch (Exception e) {
      logger.error("Failed to add timer", e);
    }
  }

  @Override
  public void removeTimer(Timer timer) {
    try {
      mongoOps.remove(timer, TIMER_COLLECTION);
      logger.info("Successfully removed " + timer.getPlantable() 
          + " timer at " + timer.getLocation());
    } catch (Exception e) {
      logger.error("Failed to remove timer", e);
    }
  }

}
