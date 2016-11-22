package br.com.juliancesar.multipersistenceunit.business;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.juliancesar.multipersistenceunit.dao.ExampleDAO;
import br.com.juliancesar.multipersistenceunit.dao.NewsCategoryDAO;
import br.com.juliancesar.multipersistenceunit.dao.NewsDAO;
import br.com.juliancesar.multipersistenceunit.dao.UserDAO;
import br.com.juliancesar.multipersistenceunit.entity.Example;
import br.com.juliancesar.multipersistenceunit.entity.News;
import br.com.juliancesar.multipersistenceunit.entity.NewsCategory;
import br.com.juliancesar.multipersistenceunit.entity.User;

@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class StartContainerBusiness {

	@Inject
	private NewsDAO newsDao;

	@Inject
	private NewsCategoryDAO newsCategoryDao;

	@Inject
	private UserDAO userDao;

	@Inject
	private ExampleDAO exampleDao;

	@Inject
	private static final Logger log = Logger.getLogger(StartContainerBusiness.class.getName());

	@Transactional
	public boolean start(boolean commitCategory, boolean makeThrowsStep1, boolean makeThrowsStep2,
			boolean makeThrowsStep3) {

		log.info("===== Starting Process WITH CONTAINER MANAGED transaction =====");

		NewsCategory category = new NewsCategory("Categoria 0001");
		News news = new News("Título Notícia 0001", category);
		User user = new User("Julian");
		Example example = new Example("Example");

		try {
			// PU News
			log.info("Create News Category (PU News)");
			newsCategoryDao.create(category);

			if (makeThrowsStep1)
				throw new Exception("Erro forçado para ROLLBACK de tudo! STEP 1");

			log.info("Create News with Category (PU News)");
			newsDao.create(news);

			if (makeThrowsStep2)
				throw new Exception("Erro forçado para ROLLBACK de tudo! STEP 2");

			// PU User
			log.info("Create User (PU User)");
			userDao.create(user);

			if (makeThrowsStep3)
				throw new Exception("Erro forçado para ROLLBACK de tudo! STEP 3");

			// PU Example
			log.info("Create Example (PU Example)");
			exampleDao.create(example);

			return true;

		} catch (Exception e) {

			log.severe("Made ROLLBACK all queries!");

			log.log(Level.SEVERE, "Business Error");

			return false;
		}

	}

}
