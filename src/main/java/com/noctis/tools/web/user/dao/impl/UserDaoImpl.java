package com.noctis.tools.web.user.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.noctis.tools.web.user.User;
import com.noctis.tools.web.user.dao.UserDao;
import com.noctis.tools.web.user.exception.DuplicateUsernameException;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

  private static final Logger logger = LoggerFactory
      .getLogger(UserDaoImpl.class);

  private final MongoOperations mongoOps;

  static final String USER_COLLECTION = "users";

  @Autowired
  public UserDaoImpl(@Qualifier("mongoOperations") MongoOperations mongoOps) {
    this.mongoOps = mongoOps;
  }

  @Override
  public void addUser(User user) throws DuplicateUsernameException {
    User check = getUser(user.getUsername());
    if (check != null) {
      throw new DuplicateUsernameException(user.getUsername());
    }

    try {
      mongoOps.insert(user, USER_COLLECTION);
      logger.info("Successfully registered user " + user.getUsername());
    } catch (Exception e) {
      logger.error("Failed to register user " + user.getUsername() + ":\n", e);
    }
  }

  @Override
  public User getUser(String username) {
    User user = null;
    Query searchQuery = new Query(Criteria.where("username").is(username));

    try {
      user = mongoOps.findOne(searchQuery, User.class, USER_COLLECTION);
      logger.info("Successfully retrieved user " + user.getUsername());
    } catch (Exception e) {
      logger.error("Failed to retrieve user:\n", e);
    }

    return user;
  }

}
