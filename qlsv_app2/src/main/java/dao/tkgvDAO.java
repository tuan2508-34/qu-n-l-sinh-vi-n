package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.tkgv;
import util.HibernateUtil;

public class tkgvDAO implements DAO_interface<tkgv> {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	@Override
	public int insert(tkgv t) {
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
	public int update(tkgv t) {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "UPDATE tkgv SET matkhau = :mk WHERE magiangvien = :id";
			javax.persistence.Query query = session.createQuery(hql);
			query.setParameter("mk", t.getMatkhau());
			query.setParameter("id", t.getMagiangvien());
			query.executeUpdate();
			tr.commit();
			// session.close();
			return 1;
		}
		return 0;
	}

	@Override
	public int delete(tkgv t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<tkgv> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public tkgv selectById(tkgv t) {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "FROM tkgv AS u WHERE u.magiangvien = :id";

			tkgv tk = session.createQuery(hql, tkgv.class).setParameter("id", t.getMagiangvien()).uniqueResult();
			tr.commit();
			// session.close()
			return tk;

		}
		return null;
	}

	@Override
	public ArrayList<tkgv> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
