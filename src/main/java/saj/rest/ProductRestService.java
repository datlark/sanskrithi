package saj.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import saj.domain.Product;
import saj.service.ProductService;


@Path("/product")
public class ProductRestService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> get () {
        ProductService productService = new ProductService();
        try {
			return productService.getAllProducts();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}
