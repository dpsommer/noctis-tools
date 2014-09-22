package com.noctis.tools.web.user.exception;

public class InvalidUserInformationException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public InvalidUserInformationException() {
  }

  @Override
  public String getMessage() {
    String message = "Invalid username or password.";
    return message;
  }

}
