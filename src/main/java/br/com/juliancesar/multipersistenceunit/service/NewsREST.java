package br.com.juliancesar.multipersistenceunit.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.juliancesar.multipersistenceunit.business.NewsBusiness;
import br.com.juliancesar.multipersistenceunit.entity.News;

@Path("news")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NewsREST {

	@Inject
	private NewsBusiness business;

	@POST
	@Transactional
	public Response create(News noticia) {
		business.create(noticia);
		return Response.ok().build();
	}

	@GET
	public List<News> list() {
		return business.list();
	}

}
