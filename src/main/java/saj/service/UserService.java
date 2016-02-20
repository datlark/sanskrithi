package saj.service;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import saj.domain.User;
import saj.util.HibernateUtil;

public class UserService {

	/**
	 * Register new User
	 * 
	 * @param productID
	 * @return
	 * @throws Exception
	 */
	public int register(User user) throws Exception {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		StatelessSession session = factory.openStatelessSession();
		
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Query query = session.createQuery("FROM User where email=:id");
			query.setString("id", user.getEmail());
			User dbUser = (User) query.uniqueResult();

			if (dbUser != null) {
				//already exits
				return 1;
			} else {
				session.insert(user);
			}

			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return 2;
		} finally {
			session.close();
		}
		//newly registered
		return 0;

	}


	/**
	 * Register new User
	 * 
	 * @param productID
	 * @return
	 * @throws Exception
	 */
	public boolean login(User user) throws Exception {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		StatelessSession session = factory.openStatelessSession();
		
		try {
		
			Query query = session.createQuery("FROM User where email=:id and password=:pass");
			query.setString("id", user.getEmail());
			query.setString("pass", user.getPassword());
			User dbUser = (User) query.uniqueResult();

			if (dbUser != null) {
				return true;
			} 
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return false;

	}

	
	
}
