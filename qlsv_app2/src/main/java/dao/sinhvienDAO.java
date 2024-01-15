package dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import model.sinhvien;
import util.HibernateUtil;

public class sinhvienDAO implements DAO_interface<sinhvien> {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public int insert(sinhvien t) {
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
	public int update(sinhvien t) {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "UPDATE sinhvien SET ten = :ten," + "hovadem = :hvd," + "date = :date,gioitinh = :gt,"
					+ "quequan = :qq," + "trangthaihoc = :tth " + "WHERE masinhvien = :id";
			javax.persistence.Query query = session.createQuery(hql);
			query.setParameter("ten", t.getTen());
			query.setParameter("qq", t.getQuequan());
			query.setParameter("hvd", t.getHovadem());
			query.setParameter("date", t.getDate());
			query.setParameter("gt", t.getGioitinh());
			query.setParameter("tth", t.getTrangthaihoc());
			query.setParameter("id", t.getMasinhvien());
			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);
			tr.commit();
			// session.close();
			return 1;
		}
		return 0;
	}

	@Override
	public int delete(sinhvien t) {
		return 0;	
	}

	@Override
	public List<sinhvien> selectAll() {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "FROM sinhvien";
			//javax.persistence.Query query = session.createQuery(hql);
			List<sinhvien> arr;
			arr=session.createQuery(hql, sinhvien.class).list();
			tr.commit();
			// session.close()
			return arr;
		}
		return null;
	}
	

	@Override
	public sinhvien selectById(sinhvien t) {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "FROM sinhvien AS u WHERE u.masinhvien = :id";
			sinhvien sv = session.createQuery(hql,sinhvien.class).setParameter("id",t.getMasinhvien()).uniqueResult();
			tr.commit();
			// session.close()
			return sv;
		}
		return null;
	}
	
	public  List<sinhvien> selectByName(String t) {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "FROM sinhvien AS u WHERE u.ten = :ten";
			List<sinhvien> arr;
			arr=session.createQuery(hql, sinhvien.class).setParameter("ten",t).list();
			tr.commit();
			// session.close()
			return arr;
		}
		return null;
	}

	@Override
	public ArrayList<sinhvien> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
