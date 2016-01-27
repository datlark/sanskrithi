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
	public boolean createUser(User user) throws Exception {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		StatelessSession session = factory.openStatelessSession();
		
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Query query = session.createQuery("FROM User where email=:id");
			query.setString("id", user.getEmail());
			user = (User) query.uniqueResult();

			if (user != null) {
				return false;
			} else {

		
				session.insert(user);

			}

			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return true;

	}

	
}
