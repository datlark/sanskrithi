package saj.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import saj.domain.Product;
import saj.service.ProductService;

import com.mysql.jdbc.StringUtils;


@Path("/")
public class ProductRestService {

    @GET
    @Path("/products/ids")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts (@QueryParam("id") List<String> ids, @QueryParam("type") String type, 
    		@QueryParam("size")List<String> size) {
        ProductService productService = new ProductService();
        try {
        	if (ids != null && !ids.isEmpty()){
        		return productService.getProducts(ids);
        	}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    
    @GET
    @Path("/products/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts (@QueryParam("type") String type, @QueryParam("size")List<String> size,@QueryParam("price")List<String> price) {
        ProductService productService = new ProductService();
        try {
        	if( (size != null && size.size()> 0) || !StringUtils.isNullOrEmpty(type) || (price != null && price.size()> 0)){
        		return productService.getProducts(type, size, price);
        		
        	}else{
        		return productService.getAllProducts();
        	}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/product")
	public Product getProductById(@PathParam("id") String id) {

    	ProductService productService = new ProductService();
        
    	try {
  			return productService.getProduct(id);
  		} catch (Exception e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		return null;
	}

   
   

    
    
    
}

