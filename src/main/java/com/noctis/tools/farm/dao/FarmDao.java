package com.noctis.tools.farm.dao;

import java.util.List;

import com.noctis.tools.farm.scheduler.Timer;

public interface FarmDao {
  
  public List<Timer> getTimers();
  
  public void addTimer(Timer timer);
  
  public void removeTimer(Timer timer);
  
}
