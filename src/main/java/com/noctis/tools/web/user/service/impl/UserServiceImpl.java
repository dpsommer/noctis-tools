package com.noctis.tools.web.user.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.noctis.tools.web.security.PasswordHash;
import com.noctis.tools.web.user.User;
import com.noctis.tools.web.user.dao.UserDao;
import com.noctis.tools.web.user.exception.DuplicateUsernameException;
import com.noctis.tools.web.user.exception.InvalidUserInformationException;
import com.noctis.tools.web.user.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

  private UserDao userDao;
  private Map<String, String> activeUsers = new HashMap<>();

  @Autowired
  public UserServiceImpl(@Qualifier("userDao") UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void registerNewUser(User user) throws DuplicateUsernameException,
      NoSuchAlgorithmException, InvalidKeySpecException {
    user.setPassword(PasswordHash.createHash(user.getPassword()));
    userDao.addUser(user);
  }

  @Override
  public boolean isUserValid(User user) throws InvalidUserInformationException,
      NoSuchAlgorithmException, InvalidKeySpecException {
    User check = userDao.getUser(user.getUsername());
    if (!PasswordHash.validatePassword(user.getPassword(), check.getPassword()))
      throw new InvalidUserInformationException();
    return true;
  }

  @Override
  public void addActiveUser(String username, String token) {
    activeUsers.put(username, token);
  }

}
