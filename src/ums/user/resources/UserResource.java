package ums.user.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import ums.dao.UserDao;
import ums.model.User;


public class UserResource {
  @Context
  UriInfo uriInfo;
  @Context
  Request request;
  String id;
  public UserResource(UriInfo uriInfo, Request request, String id) {
    this.uriInfo = uriInfo;
    this.request = request;
    this.id = id;
  }
  
  //Application integration     
  @GET
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public User getUser() {
    User user = UserDao.instance.getModel().get(id);
    if(user==null)
      throw new RuntimeException("Get: User with " + id +  " not found");
    return user;
  }
  
  // For the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public User getUserHTML() {
    User user = UserDao.instance.getModel().get(id);
    if(user==null)
      throw new RuntimeException("Get: User with " + id +  " not found");
    return user;
  }
  
  @PUT
  @Consumes(MediaType.APPLICATION_XML)
  public Response putUser(JAXBElement<User> user) {
    User c = user.getValue();
    return putAndGetResponse(c);
  }
  
  @DELETE
  public void deleteUser() {
    User c = UserDao.instance.getModel().remove(id);
    if(c==null)
      throw new RuntimeException("Delete: User with " + id +  " not found");
  }
  
  private Response putAndGetResponse(User user) {
    Response res;
    if(UserDao.instance.getModel().containsKey(user.getId())) {
      res = Response.noContent().build();
    } else {
      res = Response.created(uriInfo.getAbsolutePath()).build();
    }
    UserDao.instance.getModel().put(user.getUsername(), user);
    return res;
  }
  
  

} 