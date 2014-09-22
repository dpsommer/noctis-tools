package com.noctis.tools.guild;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class ResourceRequest {
  
  @Id
  private String id;

  private String title;
  
  private String requester;
  
  private List<RequestItem> items;
  
  private Date datePosted;
  
  public void init() {
    datePosted = new Date();
  }
  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRequester() {
    return requester;
  }

  public void setRequester(String requester) {
    this.requester = requester;
  }

  public List<RequestItem> getItems() {
    return items;
  }

  public void setItems(List<RequestItem> items) {
    this.items = items;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getDatePosted() {
    return datePosted;
  }

  public void setDatePosted(Date datePosted) {
    this.datePosted = datePosted;
  }
  
}
