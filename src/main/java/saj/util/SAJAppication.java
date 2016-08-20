package saj.util;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import saj.rest.ProductRestService;
import saj.rest.UserRestService;


@ApplicationPath("/rest")
public class SAJAppication extends Application {

	 @Override
	    public Set<Class<?>> getClasses() {
	        Set<Class<?>> s = new HashSet<Class<?>>();
	        s.add(ProductRestService.class);
	        s.add(UserRestService.class);
	        return s;
	    }	
}
