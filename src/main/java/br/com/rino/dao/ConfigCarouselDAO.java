package br.com.rino.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.rino.entity.ConfigCarousel;
import br.com.rino.util.HibernateUtil;

public class ConfigCarouselDAO {

	private Session session;
	private Transaction transaction;
	private List<ConfigCarousel> configCarouselList;

	public List<ConfigCarousel> getList(){
		Criteria criteria;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(ConfigCarousel.class);
			configCarouselList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return configCarouselList;
	}
	
	public ConfigCarousel edit(Long codConfigCarousel){
		Criteria criteria;
		ConfigCarousel configCarouselResult = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
					
			String hql = "FROM ConfigCarousel C WHERE C.codConfigCarousel = :codConfigCarousel";
			Query query = session.createQuery(hql);
			query.setParameter("codConfigCarousel", codConfigCarousel);
			List<ConfigCarousel> results = query.list();
			configCarouselResult = results.get(0);
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return configCarouselResult;
	}
	
	public void insert(ConfigCarousel c) {
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
	
	public void delete(ConfigCarousel c) {
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
	
	public void update(ConfigCarousel c) {
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