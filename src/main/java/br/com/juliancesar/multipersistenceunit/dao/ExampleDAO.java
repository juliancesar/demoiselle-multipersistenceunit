package br.com.juliancesar.multipersistenceunit.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import br.com.juliancesar.multipersistenceunit.entity.Example;

public class ExampleDAO {

	@PersistenceContext(unitName = "ExamplePU")
	protected EntityManager entityManager;

	public void create(Example noticia) {
		entityManager.persist(noticia);
	}

	public List<Example> list() {
		CriteriaQuery<Example> q = entityManager.getCriteriaBuilder().createQuery(Example.class);
		q.select(q.from(Example.class));
		return entityManager.createQuery(q).getResultList();
	}

}
