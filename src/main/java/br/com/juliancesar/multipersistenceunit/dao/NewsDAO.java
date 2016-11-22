package br.com.juliancesar.multipersistenceunit.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import br.com.juliancesar.multipersistenceunit.entity.News;

public class NewsDAO {

	@PersistenceContext(unitName = "NewsPU")
	protected EntityManager entityManager;

	public void create(News noticia) {
		entityManager.persist(noticia);
	}

	public List<News> list() {
		CriteriaQuery<News> q = entityManager.getCriteriaBuilder().createQuery(News.class);
		q.select(q.from(News.class));
		return entityManager.createQuery(q).getResultList();
	}

}
