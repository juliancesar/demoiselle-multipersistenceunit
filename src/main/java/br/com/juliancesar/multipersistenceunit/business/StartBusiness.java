package br.com.juliancesar.multipersistenceunit.business;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.UserTransaction;

import br.com.juliancesar.multipersistenceunit.dao.NewsCategoryDAO;
import br.com.juliancesar.multipersistenceunit.dao.NewsDAO;
import br.com.juliancesar.multipersistenceunit.dao.UserDAO;
import br.com.juliancesar.multipersistenceunit.entity.News;
import br.com.juliancesar.multipersistenceunit.entity.NewsCategory;
import br.com.juliancesar.multipersistenceunit.entity.User;

@TransactionManagement(value = TransactionManagementType.BEAN)
public class StartBusiness {

	@Inject
	private NewsDAO newsDao;

	@Inject
	private NewsCategoryDAO newsCategoryDao;

	@Inject
	private UserDAO userDao;

	@Resource
	private UserTransaction userTransaction;

	@Inject
	private static final Logger log = Logger.getLogger(StartBusiness.class.getName());

	public boolean start(boolean makeThrowsStep1, boolean makeThrowsStep2, boolean makeThrowsStep3) {

		NewsCategory category = new NewsCategory("Categoria 0001");
		News news = new News("Título Notícia 0001", category);
		User user = new User("Julian");

		try {

			// BEGIN ALL
			log.info("Begin Transaction");
			userTransaction.begin();

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

			// COMMIT ALL
			log.info("Commit transaction");
			userTransaction.commit();
			
			return true;

		} catch (Exception e) {
			
			try {
				userTransaction.rollback();
				log.severe("Made ROLLBACK all queries!");
			} catch (Exception ex) {		
				log.log(Level.SEVERE, "Error when made ROLLBACK!", ex);
			}
			
			log.log(Level.SEVERE, "Business Error: ", e.getMessage());
			
			return false;
		}

	}

	public void startEasyPeasy() {

		NewsCategory category = new NewsCategory("Categoria 0001");
		News news = new News("Título Notícia 0001", category);
		User user = new User("Julian");

		try {

			// PU News
			userTransaction.begin();
			newsCategoryDao.create(category);
			newsDao.create(news);
			userTransaction.commit();

			// PU User
			userTransaction.begin();
			userDao.create(user);
			userTransaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
