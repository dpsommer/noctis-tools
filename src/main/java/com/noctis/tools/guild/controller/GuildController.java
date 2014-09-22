package com.noctis.tools.guild.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.noctis.tools.core.util.JsonResponse;
import com.noctis.tools.core.util.JsonResponse.JsonResponseBuilder;
import com.noctis.tools.guild.ResourceRequest;
import com.noctis.tools.guild.Guild;
import com.noctis.tools.guild.service.GuildService;

/**
 * REST endpoint for handling requests on Farm objects and related
 * services
 * 
 * @author Duncan
 *
 */
@RestController
public class GuildController {

  private GuildService guildService;

  @Autowired
  public GuildController(@Qualifier("guildService") GuildService guildService) {
    this.guildService = guildService;
  }

  /**
   * REST GET request for the guild object
   * 
   * @return the Guild object
   */
  @RequestMapping(method = RequestMethod.GET, value = "/guild")
  public Guild getGuild() {
    return guildService.getGuild();
  }
  
  /**
   * REST POST request to save the new guild fund amount
   * 
   * @return a JsonResponse object
   */
  @RequestMapping(method = RequestMethod.POST, value = "/guild/funds", 
      consumes = "application/json", 
      headers = { "content-type=application/json", "accept=application/json" })
  public JsonResponse setFunds(@RequestBody Guild guild) {
    guildService.setFunds(guild);
    return new JsonResponseBuilder()
    .message("success")
    .build();
  }
  
  /**
   * REST GET request to pull all guild announcements from database
   * 
   * @return List of Announcement objects
   */
  @RequestMapping(method = RequestMethod.GET, value = "/guild/requests")
  public List<ResourceRequest> getRequests() {
    return guildService.getRequest();
  }
  
  /**
   * REST POST request to add an announcement to the database
   * 
   * @param request a Jackson-parsed Announcement object
   * @return a JsonResponse object
   */
  @RequestMapping(method = RequestMethod.POST, value = "/guild/requests", 
      consumes = "application/json", 
      headers = { "content-type=application/json", "accept=application/json" })
  public JsonResponse addRequest(@RequestBody ResourceRequest request) {
    guildService.addRequest(request);
    return new JsonResponseBuilder()
                    .message("success")
                    .build();
  }
  
  @RequestMapping(method = RequestMethod.POST, value = "/guild/requests/update", 
      consumes = "application/json", 
      headers = { "content-type=application/json", "accept=application/json" })
  public JsonResponse updateRequest(@RequestBody ResourceRequest request) {
    guildService.updateRequest(request);
    return new JsonResponseBuilder()
                    .message("success")
                    .build();
  }
  
  @RequestMapping(method = RequestMethod.POST, value = "/guild/requests/remove", 
      consumes = "application/json", 
      headers = { "content-type=application/json", "accept=application/json" })
  public JsonResponse deleteRequest(@RequestBody ResourceRequest request) {
    guildService.deleteRequest(request);
    return new JsonResponseBuilder()
                    .message("success")
                    .build();
  }

}
