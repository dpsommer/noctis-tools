package com.noctis.tools.map.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.noctis.tools.core.util.JsonResponse;
import com.noctis.tools.core.util.JsonResponse.JsonResponseBuilder;
import com.noctis.tools.map.MapData;
import com.noctis.tools.map.service.MapService;

@RestController
public class MapController {

  private MapService mapService;
  
  @Autowired
  public MapController(@Qualifier("mapService") MapService mapService) {
    this.mapService = mapService;
  }
  
  /**
   * REST GET request to pull all map names from the database for reference
   * 
   * @return a List of the names
   */
  @RequestMapping(method = RequestMethod.GET, value = "/map/names")
  public List<String> getMapNames() {
    return mapService.getMapNames();
  }
  
  /**
   * REST POST request to load the map corresponding to the given name
   * 
   * @return the MapData object associated with the map
   */
  @RequestMapping(method = RequestMethod.POST, value = "/map/load")
  public MapData getMap(@RequestBody String name) {
    return mapService.getMap(name);
  }
  
  /**
   * REST POST request to save the current map
   * 
   * @return a JsonRespone object
   */
  @RequestMapping(method = RequestMethod.POST, value = "/map", 
      consumes = "application/json", 
      headers = { "content-type=application/json", "accept=application/json" })
  public JsonResponse saveMap(@RequestBody MapData mapData) {
    mapService.saveData(mapData);
    return new JsonResponseBuilder()
                    .message("success")
                    .build();
  }
  
}
