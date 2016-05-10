package br.com.rino.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.rino.entity.Photo;
import br.com.rino.util.HibernateUtil;

public class PhotoDAO {

	private Session session;
	private Transaction transaction;
	private List<Photo> photoList;

	public List<Photo> getList() {
		Criteria criteria;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(Photo.class);
			photoList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return photoList;
	}

	public Photo edit(String nomeFoto) {
		Criteria criteria;
		Photo photoResult = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			String hql = "FROM Photo P WHERE P.nomeFoto = :nomeFoto";
			Query query = session.createQuery(hql);
			query.setParameter("nomeFoto", nomeFoto);
			List<Photo> results = query.list();
			photoResult = results.get(0);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return photoResult;
	}

	public void insert(Photo p) {
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

	public void delete(Photo p) {
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
	
	public void update(Photo p){
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(p);
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
