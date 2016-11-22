package br.com.juliancesar.multipersistenceunit.business;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.juliancesar.multipersistenceunit.dao.NewsDAO;
import br.com.juliancesar.multipersistenceunit.entity.News;

public class NewsBusiness {

	@Inject
	private NewsDAO dao;

	@Transactional
	public void create(News noticia) {
		dao.create(noticia);
	}

	public List<News> list() {
		return dao.list();
	}

}
