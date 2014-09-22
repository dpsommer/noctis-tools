package com.noctis.tools.farm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.noctis.tools.core.util.JsonResponse;
import com.noctis.tools.core.util.JsonResponse.JsonResponseBuilder;
import com.noctis.tools.farm.Plantable;
import com.noctis.tools.farm.scheduler.Timer;
import com.noctis.tools.farm.service.FarmService;

/**
 * REST endpoint for handling requests on Farm objects and related
 * services
 * 
 * @author Duncan
 *
 */
@RestController
public class FarmController {

  private FarmService farmService;

  @Autowired
  public FarmController(@Qualifier("farmService") FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * REST GET request for individual Farm objects based on mongodb's
   * assigned ObjectId. Useful for pulling single objects from a user's
   * Farm list.
   * 
   * @param id
   *          - the ObjectId assigned to the Farm object by mongodb
   * @return the Farm object; autoparsed by Jackson into a
   *         frontend-readable JSON object
   */
  @RequestMapping(method = RequestMethod.GET, value = "/farm/timer")
  public List<Timer> getTimers() {
    return farmService.getTimers();
  }

  /**
   * REST GET request for the enumerated values in the CharacterClass enum;
   * useful for creating options lists for display on the frontend
   * 
   * @return an array of the values in CharacterClass
   */
  @RequestMapping(method = RequestMethod.GET, value = "/farm/types")
  public Plantable[] getFarmTypes() {
    return farmService.getFarmTypes();
  }

  /**
   * REST POST request to insert an array of Timer objects into the database.
   * 
   * @param timers
   *          - Timer array autoparsed from a frontend JSON object
   * @return a JsonResponse object with a success message
   */
  @RequestMapping(method = RequestMethod.POST, value = "/farm/timer", 
      consumes = "application/json", 
      headers = { "content-type=application/json", "accept=application/json" })
  @ResponseBody
  public JsonResponse addTimers(@RequestBody Timer[] timers) {
    farmService.addTimers(timers);
    return new JsonResponseBuilder()
                    .message("success")
                    .build();
  }
  
  /**
   * REST POST request to insert an array of Timer objects into the database.
   * 
   * @param timers
   *          - Timer array autoparsed from a frontend JSON object
   * @return a JsonResponse object with a success message
   */
  @RequestMapping(method = RequestMethod.POST, value = "/farm/timer/remove", 
      consumes = "application/json", 
      headers = { "content-type=application/json", "accept=application/json" })
  @ResponseBody
  public JsonResponse removeTimer(@RequestBody Timer timer) {
    farmService.removeTimer(timer);
    return new JsonResponseBuilder()
                    .message("success")
                    .build();
  }

}
