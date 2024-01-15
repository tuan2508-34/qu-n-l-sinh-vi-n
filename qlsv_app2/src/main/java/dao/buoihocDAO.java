package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.buoihoc;
import model.lop;
import model.sinhvien;
import util.HibernateUtil;

public class buoihocDAO implements DAO_interface<buoihoc> {
	SessionFactory sessionFactory  = HibernateUtil.getSessionFactory();
	@Override
	public int insert(buoihoc t) {
		try {
			SessionFactory sessionFactory  = HibernateUtil.getSessionFactory();
			if(sessionFactory!=null) {
					Session session = sessionFactory.openSession();
					Transaction tr = session.beginTransaction();
					
					tr.commit();
					session.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	public int update(buoihoc t) {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "UPDATE buoihoc SET sobuoivang = :sbv WHERE mabuoihoc = :id";
			javax.persistence.Query query = session.createQuery(hql);
			query.setParameter("sbv", t.getSobuoivang());
			query.setParameter("id", t.getMabuoihoc());
			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);
			tr.commit();
			// session.close();
			return 1;
		}
		return 0;
	}

	@Override
	public int delete(buoihoc t) {
		try {
			SessionFactory sessionFactory  = HibernateUtil.getSessionFactory();
			if(sessionFactory!=null) {
					Session session = sessionFactory.openSession();
					Transaction tr = session.beginTransaction();
					
					tr.commit();
					session.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	public ArrayList<buoihoc> selectAll() {
		try {
			SessionFactory sessionFactory  = HibernateUtil.getSessionFactory();
			if(sessionFactory!=null) {
					Session session = sessionFactory.openSession();
					Transaction tr = session.beginTransaction();
					
					tr.commit();
					session.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public buoihoc selectById(buoihoc t) {
		try {
			SessionFactory sessionFactory  = HibernateUtil.getSessionFactory();
			if(sessionFactory!=null) {
					Session session = sessionFactory.openSession();
					Transaction tr = session.beginTransaction();
					
					tr.commit();
					session.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public ArrayList<buoihoc> selectByCondition(String condition) {
		try {
			SessionFactory sessionFactory  = HibernateUtil.getSessionFactory();
			if(sessionFactory!=null) {
					Session session = sessionFactory.openSession();
					Transaction tr = session.beginTransaction();
					
					tr.commit();
					session.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public List<buoihoc> selectByLop(lop l) {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "FROM buoihoc AS u WHERE u.lop = :l";
			List<buoihoc> arr;
			arr=session.createQuery(hql, buoihoc.class).setParameter("l",l).list();
			tr.commit();
			// session.close()
			return arr;
		}
		return null;
	}
	public List<buoihoc> selectBySv(sinhvien l) {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			String hql = "FROM buoihoc AS u WHERE u.sinhvien = :l";
			List<buoihoc> arr;
			arr=session.createQuery(hql, buoihoc.class).setParameter("l",l).list();
			tr.commit();
			// session.close()
			return arr;
		}
		return null;
	}

}
