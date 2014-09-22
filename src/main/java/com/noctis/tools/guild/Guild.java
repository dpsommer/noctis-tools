package com.noctis.tools.guild;

import org.springframework.data.annotation.Id;

public class Guild {
  
  @Id
  private String id;
  
  private int gold, silver, copper;
  
  public void init() {
    gold = 0;
    silver = 0;
    copper = 0;
  }
  
  public int getGold() {
    return gold;
  }

  public void setGold(int gold) {
    this.gold = gold;
  }

  public int getSilver() {
    return silver;
  }

  public void setSilver(int silver) {
    this.silver = silver;
  }

  public int getCopper() {
    return copper;
  }

  public void setCopper(int copper) {
    this.copper = copper;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
  
}
