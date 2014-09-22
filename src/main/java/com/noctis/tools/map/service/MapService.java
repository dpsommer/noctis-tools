package com.noctis.tools.map.service;

import java.util.List;

import com.noctis.tools.map.MapData;

public interface MapService {

  public MapData getMap(String name);

  public void saveData(MapData mapData);
  
  public List<String> getMapNames();
  
}
