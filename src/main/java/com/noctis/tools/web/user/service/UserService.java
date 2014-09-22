package com.noctis.tools.web.user.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.noctis.tools.web.user.User;
import com.noctis.tools.web.user.exception.DuplicateUsernameException;

public interface UserService {

  public void registerNewUser(User user) throws DuplicateUsernameException,
      NoSuchAlgorithmException, InvalidKeySpecException;

  public boolean isUserValid(User user) throws NoSuchAlgorithmException,
      InvalidKeySpecException;

  public void addActiveUser(String username, String token);

}
