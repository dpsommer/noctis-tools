package com.noctis.tools.map.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.noctis.tools.map.MapData;
import com.noctis.tools.map.dao.MapDao;
import com.noctis.tools.map.service.MapService;

@Service("mapService")
public class MapServiceImpl implements MapService {

  private MapDao mapDao;
  
  @Autowired
  public MapServiceImpl(@Qualifier("mapDao") MapDao mapDao) {
    this.mapDao = mapDao;
  }

  @Override
  public MapData getMap(String name) {
    return mapDao.getMap(name);
  }
  
  @Override
  public void saveData(MapData mapData) {
    mapDao.saveData(mapData);
  }

  @Override
  public List<String> getMapNames() {
    return mapDao.getMapNames();
  }

}
