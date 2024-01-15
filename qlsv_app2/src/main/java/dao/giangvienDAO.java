package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.giangvien;
import util.HibernateUtil;

public class giangvienDAO implements DAO_interface<giangvien> {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	@Override
	public int insert(giangvien t) {
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
	public int update(giangvien t) {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "UPDATE giangvien SET hovaten = :hovaten WHERE magiangvien = :id";
			javax.persistence.Query query = session.createQuery(hql);
			query.setParameter("hovaten", t.getHovaten());
			query.setParameter("id", t.getMagiangvien());
			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);
			tr.commit();
			// session.close();
			return 1;
		}
		return 0;
	}

	@Override
	public int delete(giangvien t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<giangvien> selectAll() {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "FROM giangvien";
			//javax.persistence.Query query = session.createQuery(hql);
			List<giangvien> arr;
			arr=session.createQuery(hql, giangvien.class).list();
			tr.commit();
			// session.close()
			return arr;
		}
		return null;
	}

	@Override
	public giangvien selectById(giangvien t) {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "FROM giangvien AS u WHERE u.magiangvien = :id";
			giangvien sv = session.createQuery(hql,giangvien.class).setParameter("id",t.getMagiangvien()).uniqueResult();
			tr.commit();
			// session.close()
			return sv;
		}
		return null;
	}

	@Override
	public ArrayList<giangvien> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
