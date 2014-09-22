package com.noctis.tools.guild.dao;

import java.util.List;

import com.noctis.tools.guild.ResourceRequest;
import com.noctis.tools.guild.Guild;

public interface GuildDao {

  public Guild getGuild();
  
  public void setFunds(Guild guild);

  public void addGuild(Guild guild);

  public List<ResourceRequest> getRequests();
  
  public void addRequest(ResourceRequest request);

  public void updateRequest(ResourceRequest request);
  
  public void deleteRequest(ResourceRequest request);
  
}
