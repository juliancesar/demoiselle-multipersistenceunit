package br.com.juliancesar.multipersistenceunit.business;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.juliancesar.multipersistenceunit.dao.UserDAO;
import br.com.juliancesar.multipersistenceunit.entity.User;

public class UserBusiness {

	@Inject
	private UserDAO dao;

	@Transactional
	public void create(User noticia) {
		dao.create(noticia);
	}

	public List<User> list() {
		return dao.list();
	}

}
