package com.noctis.tools.map;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class MapData {
  
  @Id
  private String id;
  
  private String name;
  
  private List<String> layers;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getLayers() {
    return layers;
  }

  public void setLayers(List<String> layers) {
    this.layers = layers;
  }
  
}
