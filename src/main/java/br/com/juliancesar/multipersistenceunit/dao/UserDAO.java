package br.com.juliancesar.multipersistenceunit.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import br.com.juliancesar.multipersistenceunit.entity.User;

public class UserDAO {

	@PersistenceContext(unitName = "UserPU")
	protected EntityManager entityManager;

	public void create(User noticia) {
		entityManager.persist(noticia);
	}

	public List<User> list() {
		CriteriaQuery<User> q = entityManager.getCriteriaBuilder().createQuery(User.class);
		q.select(q.from(User.class));
		return entityManager.createQuery(q).getResultList();
	}

}
