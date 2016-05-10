package br.com.rino.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.rino.entity.Facebook;
import br.com.rino.entity.Whell;
import br.com.rino.util.HibernateUtil;

public class FacebookDAO {
	private Session session;
	private Transaction transaction;
	private List<Facebook> facebookList;
	
	public List<Facebook> getList(){
		Criteria criteria;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(Facebook.class);
			facebookList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return facebookList;
	}
	
	public Facebook edit(Long codFacebook){
		Criteria criteria;
		Facebook facebookResult = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
					
			String hql = "FROM Facebook F WHERE F.codFacebook = :codFacebook";
			Query query = session.createQuery(hql);
			query.setParameter("codFacebook", codFacebook);
			List<Facebook> results = query.list();
			facebookResult = results.get(0);
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return facebookResult;
	}
	
	public void insert(Facebook f) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(f);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void delete(Facebook f) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(f);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void update(Facebook f) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(f);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
