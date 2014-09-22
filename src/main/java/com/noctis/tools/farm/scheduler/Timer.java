package com.noctis.tools.farm.scheduler;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.noctis.tools.farm.Plantable;

@Component
public class Timer {

  /** Mongodb unique identifier to mark object for CRUD operations */
  @Id private String id;
  
  private Plantable plantable;
  
  private int count;
  
  private String location;
  
  private String comment;
  
  private boolean watered;
  
  private boolean optimalClimate;
  
  private Long plantTime;
  
  private Long growthTime;

  public void init() {
    growthTime = plantable.getGrowthTime();
    if (optimalClimate)
      growthTime = (long)(growthTime * 0.7);
    if (watered)
      growthTime = (long)(growthTime * 0.9);
    plantTime = System.currentTimeMillis();
  }
  
  /** Accessors/Mutators */
  public String getId() {
    return id;
  }

  public Plantable getPlantable() {
    return plantable;
  }

  public void setPlantable(Plantable plantable) {
    this.plantable = plantable;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public boolean isWatered() {
    return watered;
  }

  public void setWatered(boolean watered) {
    this.watered = watered;
  }

  public boolean isOptimalClimate() {
    return optimalClimate;
  }

  public void setOptimalClimate(boolean optimalClimate) {
    this.optimalClimate = optimalClimate;
  }

  public Long getPlantTime() {
    return plantTime;
  }

  public void setPlantTime(Long plantTime) {
    this.plantTime = plantTime;
  }

  public Long getGrowthTime() {
    return growthTime;
  }

  public void setGrowthTime(Long growthTime) {
    this.growthTime = growthTime;
  }
  
}
