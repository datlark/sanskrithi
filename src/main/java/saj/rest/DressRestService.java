package saj.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import saj.domain.Dress;
import saj.service.DressService;


@Path("/dress")
public class DressRestService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dress> get () {
        DressService dressService = new DressService();
        try {
			return dressService.getAllDresses();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}
