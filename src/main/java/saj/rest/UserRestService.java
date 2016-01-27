package saj.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import saj.domain.User;
import saj.service.UserService;


@Path("/users")
public class UserRestService {

    @POST
		
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userreg")
	public void createUser(User user) {
	
    	UserService userService = new UserService();
    	
    	try {
			userService.createUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
	}
   

    
    
    
}

