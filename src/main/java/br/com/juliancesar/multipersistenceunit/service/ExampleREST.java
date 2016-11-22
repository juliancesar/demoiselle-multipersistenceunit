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

import br.com.juliancesar.multipersistenceunit.business.ExampleBusiness;
import br.com.juliancesar.multipersistenceunit.entity.Example;

@Path("example")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExampleREST {

	@Inject
	private ExampleBusiness business;

	@POST
	public Response create(Example noticia) {
		business.create(noticia);
		return Response.ok().build();
	}

	@GET
	public List<Example> list() {
		return business.list();
	}

}
