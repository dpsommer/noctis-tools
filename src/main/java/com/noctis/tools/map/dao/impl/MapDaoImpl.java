package com.noctis.tools.map.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.noctis.tools.map.MapData;
import com.noctis.tools.map.dao.MapDao;
import com.noctis.tools.web.user.dao.impl.UserDaoImpl;

@Repository("mapDao")
public class MapDaoImpl implements MapDao {
  
  private static final Logger logger = LoggerFactory
      .getLogger(UserDaoImpl.class);

  private final MongoOperations mongoOps;

  static final String MAP_COLLECTION = "map";
  
  @Autowired
  public MapDaoImpl(@Qualifier("mongoOperations") MongoOperations mongoOps) {
    this.mongoOps = mongoOps;
  }

  @Override
  public MapData getMap(String name) {
    Query searchQuery = new Query(Criteria.where("name").is(name));
    MapData map = null;
    
    try {
      map = mongoOps.findOne(searchQuery, MapData.class, MAP_COLLECTION);
      logger.info("Successfully retrieved map " + name);
    } catch (Exception e) {
      logger.error("Failed to retrieve map " + name, e);
    }
    
    return map;
  }

  @Override
  public void saveData(MapData mapData) {
    Query searchQuery = new Query(Criteria.where("name").is(mapData.getName()));
    
    try {
      MapData check = mongoOps.findOne(searchQuery, MapData.class, MAP_COLLECTION);
      if (check != null)
        mapData.setId(check.getId());
      mongoOps.save(mapData, MAP_COLLECTION);
      logger.info("Successfully added map " + mapData.getName());
    } catch (Exception e) {
      logger.error("Failed to add map " + mapData.getName(), e);
    }
  }

  @Override
  public List<String> getMapNames() {
    List<String> mapNames = new ArrayList<>();
    try {
      List<MapData> maps = mongoOps.findAll(MapData.class, MAP_COLLECTION);
      for (MapData map : maps)
        mapNames.add(map.getName());
      logger.info("Successfully retrieved maps");
    } catch (Exception e) {
      logger.error("Failed to retrieve maps", e);
    }
    return mapNames;
  }

}
