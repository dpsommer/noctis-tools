package com.noctis.tools.guild.service;

import java.util.List;

import com.noctis.tools.guild.ResourceRequest;
import com.noctis.tools.guild.Guild;

public interface GuildService {
  
  public Guild getGuild();
  
  public void setFunds(Guild guild);

  public void addRequest(ResourceRequest request);
  
  public List<ResourceRequest> getRequest();
  
  public void updateRequest(ResourceRequest request);
  
  public void deleteRequest(ResourceRequest request);
  
}
