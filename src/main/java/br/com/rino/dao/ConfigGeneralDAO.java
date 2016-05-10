package br.com.rino.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.rino.entity.ConfigGeneral;
import br.com.rino.entity.Panel;
import br.com.rino.util.HibernateUtil;

public class ConfigGeneralDAO {

	private Session session;
	private Transaction transaction;
	private List<ConfigGeneral> configGeneralList;
	
	public List<ConfigGeneral> getList(){
		Criteria criteria;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(ConfigGeneral.class);
			configGeneralList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return configGeneralList;
	}
	
	public ConfigGeneral edit(Long codConfigGeneral){
		Criteria criteria;
		ConfigGeneral configGeneralResult = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
					
			String hql = "FROM ConfigGeneral G WHERE G.codConfig = :codConfigGeneral";
			Query query = session.createQuery(hql);
			query.setParameter("codConfigGeneral", codConfigGeneral);
			List<ConfigGeneral> results = query.list();
			configGeneralResult = results.get(0);
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return configGeneralResult;
	}
	
	public void insert(ConfigGeneral g) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(g);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void delete(ConfigGeneral g) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(g);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void update(ConfigGeneral g) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(g);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
