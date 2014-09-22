package com.noctis.tools.map.dao;

import java.util.List;

import com.noctis.tools.map.MapData;

public interface MapDao {
  
  public MapData getMap(String name);

  public void saveData(MapData mapData);
  
  public List<String> getMapNames();
  
}
