package br.com.juliancesar.multipersistenceunit.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import br.com.juliancesar.multipersistenceunit.entity.NewsCategory;

public class NewsCategoryDAO {

	@PersistenceContext(unitName = "NewsPU")
	protected EntityManager entityManager;

	public void create(NewsCategory noticia) {
		entityManager.persist(noticia);
	}

	public List<NewsCategory> list() {
		CriteriaQuery<NewsCategory> q = entityManager.getCriteriaBuilder().createQuery(NewsCategory.class);
		q.select(q.from(NewsCategory.class));
		return entityManager.createQuery(q).getResultList();
	}

}
