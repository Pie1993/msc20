package msc20.components.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DbManager {

	@Autowired
	private SessionFactory sessionFactory;

	public boolean save(Object obj) {
		final Session session = sessionFactory.openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(obj);
			tx.commit();
			return true;
		} catch (final Exception e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		} finally {
			session.close();
		}

	}

	public boolean update(Object obj) {
		final Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(obj);
			tx.commit();
			return true;
		} catch (final Exception e) {
			tx.rollback();
			return false;
		} finally {
			session.close();
		}
	}

	public boolean delete(Object obj) {
		final Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(obj);
			tx.commit();
			return true;
		} catch (final Exception e) {
			tx.rollback();
			return false;
		} finally {
			session.close();
		}
	}
	
	public boolean remove(Object obj) {
		final Session session = this.sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.remove(obj);
			tx.commit();
			return true;
		} catch (final Exception e) {
			tx.rollback();
			return false;
		} finally {
			session.close();
		}
	}

}