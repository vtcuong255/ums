package ums.dao;

import java.util.HashMap;
import java.util.Map;

import ums.model.User;


public enum UserDao {
  instance;
  
  private Map<String, User> contentProvider = new HashMap<String, User>();
  
  private UserDao() {
    
    User user = new User(1l, "1", "Learn REST","");
    user.setEmail("@@");
    contentProvider.put("1", user);
    user = new User(2L, "2", "","Do something");
    user.setEmail("@@@");
    contentProvider.put("2", user);
    
  }
  public Map<String, User> getModel(){
    return contentProvider;
  }
  
} 