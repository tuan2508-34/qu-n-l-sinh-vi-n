package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import model.tksv;
import util.HibernateUtil;

public class tksvDAO implements DAO_interface<tksv> {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public int insert(tksv t) {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			session.save(t);
			tr.commit();
			// session.close();
			return 1;
		}
		return 0;
	}

	@Override
	public int update(tksv t) {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "UPDATE tksv SET matkhau = :mk WHERE masinhvien = :id";
			javax.persistence.Query query = session.createQuery(hql);
			query.setParameter("mk", t.getMatkhau());
			query.setParameter("id", t.getMasinhvien());
			query.executeUpdate();
			tr.commit();
			// session.close();
			return 1;
		}
		return 0;
	}

	@Override
	public int delete(tksv t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<tksv> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public tksv selectById(tksv t) {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "FROM tksv AS u WHERE u.masinhvien = :id";

			tksv tk = session.createQuery(hql, tksv.class).setParameter("id", t.getMasinhvien()).uniqueResult();
			tr.commit();
			// session.close()
			return tk;

		}
		return null;
	}

	@Override
	public ArrayList<tksv> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
