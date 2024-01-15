package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import model.monhoc;
import util.HibernateUtil;

public class monhocDAO implements DAO_interface<monhoc> {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public int insert(monhoc t) {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			session.save(t);
			tr.commit();
			return 1;
		}
		return 0;
	}

	@Override
	public int update(monhoc t) {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "UPDATE monhoc SET tenmonhoc = :ten, sotinchi= :stc WHERE mamonhoc = :id";
			javax.persistence.Query query = session.createQuery(hql);
			query.setParameter("ten", t.getTenmonhoc());
			query.setParameter("stc", t.getSotinchi());
			query.setParameter("id", t.getMamonhoc());
			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);
			tr.commit();
			// session.close();
			return 1;
		}
		return 0;
	}

	@Override
	public int delete(monhoc t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<monhoc> selectAll() {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "FROM monhoc";
			// javax.persistence.Query query = session.createQuery(hql);
			List<monhoc> arr;
			arr = session.createQuery(hql, monhoc.class).list();
			tr.commit();
			// session.close()
			return arr;
		}
		return null;
	}

	@Override
	public monhoc selectById(monhoc t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<monhoc> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
