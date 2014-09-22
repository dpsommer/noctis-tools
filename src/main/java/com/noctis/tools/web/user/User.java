package com.noctis.tools.web.user;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.noctis.tools.web.security.Permissions;

@Component
public class User {

  @Id
  private String id;
  
  /** Login credentials */
  private String username;
  private String password;
  
  /** The permissions object containing the user's permission matrix */
  private Permissions permissionLevel;
  
  /** Display name to show on the site */
  private String displayName;
  
  /** The user's guild affiliation */
  private String guild;

  /** Accessors/Mutators */
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Permissions getPermissionLevel() {
    return permissionLevel;
  }

  public void setPermissionLevel(Permissions permissionLevel) {
    this.permissionLevel = permissionLevel;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getGuild() {
    return guild;
  }

  public void setGuild(String guild) {
    this.guild = guild;
  }

}
