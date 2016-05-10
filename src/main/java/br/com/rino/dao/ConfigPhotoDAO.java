package br.com.rino.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.rino.entity.ConfigPhoto;
import br.com.rino.util.HibernateUtil;

public class ConfigPhotoDAO {
	private Session session;
	private Transaction transaction;
	private List<ConfigPhoto> confPhotoList;
	
	public List<ConfigPhoto> getList(){
		Criteria criteria;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(ConfigPhoto.class);
			confPhotoList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return confPhotoList;
	}
	
	public ConfigPhoto edit(Long codConfigPhoto){
		Criteria criteria;
		ConfigPhoto confPhotoResult = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
					
			String hql = "FROM ConfigPhoto C WHERE C.codConfigPhoto = :codConfigPhoto";
			Query query = session.createQuery(hql);
			query.setParameter("codConfigPhoto", codConfigPhoto);
			List<ConfigPhoto> results = query.list();
			confPhotoResult = results.get(0);
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return confPhotoResult;
	}
	
	public void insert(ConfigPhoto c) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(c);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void delete(ConfigPhoto c) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(c);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void update(ConfigPhoto c) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(c);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
