package br.com.juliancesar.multipersistenceunit.service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.juliancesar.multipersistenceunit.business.UserBusiness;
import br.com.juliancesar.multipersistenceunit.entity.User;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserREST {

	@Inject
	private UserBusiness business;

	@POST	
	public Response create(User noticia) {
		business.create(noticia);
		return Response.ok().build();
	}

	@GET
	public List<User> list() {
		return business.list();
	}

}
