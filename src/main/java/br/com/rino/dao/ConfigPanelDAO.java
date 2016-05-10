package br.com.rino.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.rino.entity.ConfigPanel;
import br.com.rino.util.HibernateUtil;

public class ConfigPanelDAO {

	private Session session;
	private Transaction transaction;
	private List<ConfigPanel> panelList;

	public List<ConfigPanel> getList(){
		Criteria criteria;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(ConfigPanel.class);
			panelList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return panelList;
	}
	
	public ConfigPanel edit(Long codConfigPanel){
		Criteria criteria;
		ConfigPanel panelResult = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
					
			String hql = "FROM ConfigPanel P WHERE P.codConfigPanel = :codConfigPanel";
			Query query = session.createQuery(hql);
			query.setParameter("codConfigPanel", codConfigPanel);
			List<ConfigPanel> results = query.list();
			panelResult = results.get(0);
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return panelResult;
	}
	
	public void insert(ConfigPanel p) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(p);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void delete(ConfigPanel p) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(p);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void update(ConfigPanel p) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(p);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
