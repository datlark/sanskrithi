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


@Path("/products")
public class ProductRestService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts (@QueryParam("id") List id) {
        ProductService productService = new ProductService();
        try {
        	if (id == null || id.isEmpty()){
        		return productService.getAllProducts();
        	}else{
        		return productService.getProducts(id);
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
//    
//    
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//	@Path("/product")
//    @PathParam("{ids}")
//
//	public List<Product> getProducts(@PathParam("ids") List id) {
//
//    	ProductService productService = new ProductService();
//        
//    	try {
//  			return productService.getProducts(id);
//  		} catch (Exception e) {
//  			// TODO Auto-generated catch block
//  			e.printStackTrace();
//  		}
//  		return null;
//	   
//
//	}
    
}

