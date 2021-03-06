package br.com.juliancesar.multipersistenceunit.service;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.juliancesar.multipersistenceunit.business.StartContainerBusiness;

@Path("startContainer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StartContainerREST {

	@Inject
	private StartContainerBusiness business;

	@GET
	@Path("{commitCategory}/{makeThrowsStep1}/{makeThrowsStep2}/{makeThrowsStep3}")
	public Response start(@PathParam(value = "commitCategory") boolean commitCategory,@PathParam(value = "makeThrowsStep1") boolean makeThrowsStep1,
			@PathParam(value = "makeThrowsStep2") boolean makeThrowsStep2,
			@PathParam(value = "makeThrowsStep3") boolean makeThrowsStep3) throws Exception {

		// Call business layer
		boolean success = business.start(commitCategory, makeThrowsStep1, makeThrowsStep2, makeThrowsStep3);

		if (success) {
			return Response.ok().build();
		} else {
			return Response.serverError().build();
		}
	}

}
