package com.noctis.tools.web.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.noctis.tools.core.util.JsonResponse;
import com.noctis.tools.core.util.JsonResponse.JsonResponseBuilder;
import com.noctis.tools.web.security.TokenGenerator;
import com.noctis.tools.web.user.User;
import com.noctis.tools.web.user.service.UserService;

@RestController
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(@Qualifier("userService") UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/user/register")
  @ResponseBody
  public JsonResponse registerUser(@RequestBody User user) throws Exception {
    userService.registerNewUser(user);
    return new JsonResponseBuilder()
                    .message("success")
                    .build();
  }

  @RequestMapping(method = RequestMethod.POST, value = "/user/login/validate")
  @ResponseBody
  public JsonResponse validateUser(@RequestBody User user) throws Exception {
    userService.isUserValid(user);
    return new JsonResponseBuilder()
                    .message("success")
                    .build();
  }
  
  @RequestMapping(method = RequestMethod.POST, value = "/user/session/token")
  @ResponseBody
  public JsonResponse getSessionToken(String username) throws Exception {
    String token = TokenGenerator.getNewToken();
    userService.addActiveUser(username, token);
    return new JsonResponseBuilder()
                    .message("success")
                    .data(token)
                    .build();
  }

}
