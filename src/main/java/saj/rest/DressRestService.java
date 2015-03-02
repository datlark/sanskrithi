package saj.rest;

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
    public Dress getDefaultUserInJSON() {
        DressService dressService = new DressService();
        return dressService.getDefaultUser();
    }
}
