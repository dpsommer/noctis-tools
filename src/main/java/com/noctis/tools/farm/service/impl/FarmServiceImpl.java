package com.noctis.tools.farm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.noctis.tools.farm.Plantable;
import com.noctis.tools.farm.dao.FarmDao;
import com.noctis.tools.farm.scheduler.Timer;
import com.noctis.tools.farm.service.FarmService;

@Service("farmService")
public class FarmServiceImpl implements FarmService {
  
  private FarmDao farmDao;
  
  @Autowired
  public FarmServiceImpl(@Qualifier("farmDao") FarmDao farmDao) {
    this.farmDao = farmDao;
  }

  @Override
  public List<Timer> getTimers() {
    return farmDao.getTimers();
  }

  @Override
  public Plantable[] getFarmTypes() {
    return Plantable.values();
  }

  @Override
  public void addTimers(Timer[] timers) {
    for (Timer t : timers) {
      t.init();
      farmDao.addTimer(t);
    }
  }

  @Override
  public void removeTimer(Timer timer) {
    farmDao.removeTimer(timer);
  }

}
