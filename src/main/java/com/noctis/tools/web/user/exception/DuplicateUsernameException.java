package com.noctis.tools.web.user.exception;

public class DuplicateUsernameException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  
  private String username;
  
  public DuplicateUsernameException(String username) {
    this.username = username;
  }
  
  @Override
  public String getMessage() {
    String message = username + " is already in use.";
    return message;
  }

}
