package com.noctis.tools.guild.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.noctis.tools.guild.Guild;
import com.noctis.tools.guild.ResourceRequest;
import com.noctis.tools.guild.dao.GuildDao;
import com.noctis.tools.guild.service.GuildService;

@Service("guildService")
public class GuildServiceImpl implements GuildService {

  private GuildDao guildDao;

  @Autowired
  public GuildServiceImpl(@Qualifier("guildDao") GuildDao guildDao) {
    this.guildDao = guildDao;
  }

  @Override
  public Guild getGuild() {
    return guildDao.getGuild();
  }

  @Override
  public void setFunds(Guild guild) {
    guildDao.setFunds(guild);
  }

  @Override
  public void addRequest(ResourceRequest request) {
    request.init();
    guildDao.addRequest(request);
  }

  @Override
  public List<ResourceRequest> getRequest() {
    return guildDao.getRequests();
  }

  @Override
  public void updateRequest(ResourceRequest request) {
    guildDao.updateRequest(request);
  }

  @Override
  public void deleteRequest(ResourceRequest request) {
    guildDao.deleteRequest(request);
  }

}
