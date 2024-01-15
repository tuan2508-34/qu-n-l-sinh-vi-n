package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.giangvien;
import model.lop;
import util.HibernateUtil;

public class lopDAO implements DAO_interface<lop> {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	@Override
	public int insert(lop t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(lop t) {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "UPDATE lop SET buoi = :b,ca =:c WHERE malop = :id";
			javax.persistence.Query query = session.createQuery(hql);
			query.setParameter("b", t.getBuoi());
			query.setParameter("c", t.getCa());
			query.setParameter("id", t.getMalop());
			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);
			tr.commit();
			// session.close();
			return 1;
		}
		return 0;
	}

	@Override
	public int delete(lop t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<lop> selectAll() {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "FROM lop";
			//javax.persistence.Query query = session.createQuery(hql);
			List<lop> arr;
			arr=session.createQuery(hql, lop.class).list();
			tr.commit();
			// session.close()
			return arr;
		}
		return null;
	}

	@Override
	public lop selectById(lop t) {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "FROM lop AS u WHERE u.malop = :id";
			lop sv = session.createQuery(hql,lop.class).setParameter("id",t.getMalop()).uniqueResult();
			tr.commit();
			// session.close()
			return sv;
		}
		return null;
	}

	@Override
	public ArrayList<lop> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public  List<lop> selectByGv(giangvien t) {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "FROM lop AS u WHERE u.giangvien = :t";
			List<lop> arr;
			arr=session.createQuery(hql, lop.class).setParameter("t",t).list();
			tr.commit();
			// session.close()
			return arr;
		}
		return null;
	}

}
