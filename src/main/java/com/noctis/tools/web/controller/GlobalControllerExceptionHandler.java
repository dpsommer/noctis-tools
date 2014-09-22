package com.noctis.tools.web.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.noctis.tools.core.util.JsonResponse;
import com.noctis.tools.core.util.JsonResponse.JsonResponseBuilder;
import com.noctis.tools.web.user.exception.DuplicateUsernameException;
import com.noctis.tools.web.user.exception.InvalidUserInformationException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

  private static final Logger logger = LoggerFactory
      .getLogger(GlobalControllerExceptionHandler.class);
  
  private static final String GENERIC_ERROR_MESSAGE = "There was an error processing your request";

  @ResponseStatus(HttpStatus.CONFLICT) // 409
  @ExceptionHandler(DuplicateUsernameException.class)
  @ResponseBody
  public JsonResponse handleDuplicateUsername(DuplicateUsernameException e) {
    logger.error("Registration failed: ", e);
    return new JsonResponseBuilder()
                    .message(e.getMessage())
                    .build();
  }
  
  @ResponseStatus(HttpStatus.FORBIDDEN) // 403
  @ExceptionHandler(InvalidUserInformationException.class)
  @ResponseBody
  public JsonResponse handleDuplicateUsername(InvalidUserInformationException e) {
    logger.error("Login failed: ", e);
    return new JsonResponseBuilder()
                    .message(e.getMessage())
                    .build();
  }
  
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
  @ExceptionHandler(NoSuchAlgorithmException.class)
  @ResponseBody
  public JsonResponse handleInternalError(NoSuchAlgorithmException e) {
    logger.error("Hashing error: ", e);
    return new JsonResponseBuilder()
                    .message(GENERIC_ERROR_MESSAGE)
                    .build();
  }
  
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
  @ExceptionHandler(InvalidKeySpecException.class)
  @ResponseBody
  public JsonResponse handleInternalError(InvalidKeySpecException e) {
    logger.error("Hashing error: ", e);
    return new JsonResponseBuilder()
                    .message(GENERIC_ERROR_MESSAGE)
                    .build();
  }

}
