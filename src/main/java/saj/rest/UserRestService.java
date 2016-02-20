package saj.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

import saj.domain.User;
import saj.service.UserService;


@Path("/users")
public class UserRestService {

    @POST
		
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userreg")
	public int register(User user) throws Exception {
	
    	UserService userService = new UserService();
    	JSONObject json = new JSONObject();
    	
    	try {
    		System.out.println("User REgistration");
    		json.put("status", "success");
    		
			return userService.register(user);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			json.put("status", "fail");
			return 2;
			
		}
    	
    	
    	
	}
   

    @POST
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userlogin")
	public boolean login(User user) throws Exception {
	
    	UserService userService = new UserService();
    	JSONObject json = new JSONObject();
    	
    	try {
    		
			return userService.login(user);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
    	
    	return false;
    	
	}
   
    
    
    
}

