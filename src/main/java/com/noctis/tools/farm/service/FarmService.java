package com.noctis.tools.farm.service;

import java.util.List;

import com.noctis.tools.farm.Plantable;
import com.noctis.tools.farm.scheduler.Timer;

public interface FarmService {

  public List<Timer> getTimers();

  public Plantable[] getFarmTypes();
  
  public void addTimers(Timer[] timers);
  
  public void removeTimer(Timer timer);
  
}
