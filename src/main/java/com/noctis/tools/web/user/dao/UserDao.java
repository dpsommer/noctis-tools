package com.noctis.tools.web.user.dao;

import com.noctis.tools.web.user.User;
import com.noctis.tools.web.user.exception.DuplicateUsernameException;

public interface UserDao {

  public void addUser(User user) throws DuplicateUsernameException;

  public User getUser(String username);

}
