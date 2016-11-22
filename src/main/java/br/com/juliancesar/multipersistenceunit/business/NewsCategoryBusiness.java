package br.com.juliancesar.multipersistenceunit.business;

import java.util.List;

import javax.inject.Inject;

import br.com.juliancesar.multipersistenceunit.dao.NewsCategoryDAO;
import br.com.juliancesar.multipersistenceunit.entity.NewsCategory;

public class NewsCategoryBusiness {

	@Inject
	private NewsCategoryDAO dao;

	public void create(NewsCategory noticia) {
		dao.create(noticia);
	}

	public List<NewsCategory> list() {
		return dao.list();
	}

}
