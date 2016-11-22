package br.com.juliancesar.multipersistenceunit.business;

import java.util.List;

import javax.inject.Inject;

import br.com.juliancesar.multipersistenceunit.dao.ExampleDAO;
import br.com.juliancesar.multipersistenceunit.entity.Example;

public class ExampleBusiness {

	@Inject
	private ExampleDAO dao;

	public void create(Example noticia) {
		dao.create(noticia);
	}

	public List<Example> list() {
		return dao.list();
	}

}
