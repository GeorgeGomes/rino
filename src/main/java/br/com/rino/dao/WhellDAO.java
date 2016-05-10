package br.com.rino.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.rino.entity.Whell;
import br.com.rino.util.HibernateUtil;

public class WhellDAO {
	private Session session;
	private Transaction transaction;
	private List<Whell> whellList;
	
	public List<Whell> getList(){
		Criteria criteria;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(Whell.class);
			whellList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return whellList;
	}
	
	public Whell edit(Long codWhell){
		Criteria criteria;
		Whell whellResult = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
					
			String hql = "FROM Whell W WHERE W.codWhell = :codWhell";
			Query query = session.createQuery(hql);
			query.setParameter("codWhell", codWhell);
			List<Whell> results = query.list();
			whellResult = results.get(0);
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return whellResult;
	}
	
	public void insert(Whell w) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(w);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void delete(Whell w) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(w);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void update(Whell w) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(w);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
}
