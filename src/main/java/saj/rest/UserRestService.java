package saj.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

import com.stripe.exception.CardException;
import com.stripe.model.Charge;

import saj.domain.Order;
import saj.domain.User;
import saj.service.UserService;


@Path("/users")
public class UserRestService {

    @POST
		
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userreg")
	public String register(User user) throws Exception {
	
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
			return "2";
			
		}
		
    	
	}
   

    @POST
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userlogin")
	public String login(User user) throws Exception {
	
    	UserService userService = new UserService();
    	JSONObject json = new JSONObject();
    	
    	try {
    		
			boolean isLoginSuccess = userService.login(user);
			
			if(isLoginSuccess){
				return "true";
			}else{
				return "false";
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
    	
    	return "false";
    	
	}
   
  
    

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
  	@Path("/checkout")
  	public String checkout(Order order) throws Exception {
  	


    	// Create a charge: this will charge the user's card
    	try {
    	  Map<String, Object> chargeParams = new HashMap<String, Object>();
    	  
    	  chargeParams.put("amount", order.getPrice()); // Amount in cents
    	  chargeParams.put("currency", "usd");
    	  chargeParams.put("source", order.getTokenID());
    	  chargeParams.put("description", "Sanskrithi Jewelry");

    	  Charge charge = Charge.create(chargeParams);
    	} catch (CardException e) {
    	  return "false";
    	}
    	  
    	  
    	  
    	  return "true";	
  			
  			
  	}
    

    
    
    
}

