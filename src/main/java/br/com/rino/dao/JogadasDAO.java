package br.com.rino.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.rino.entity.Jogadas;
import br.com.rino.entity.Whell;
import br.com.rino.util.HibernateUtil;

public class JogadasDAO {
	
	private Session session;
	private Transaction transaction;
	private List<Jogadas> jogadasList;

	public List<Jogadas> getList() {
		Criteria criteria;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(Jogadas.class);
			jogadasList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return jogadasList;
	}

	public Jogadas edit(Long codJogadas) {
		Criteria criteria;
		Jogadas jogadasResult = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			String hql = "FROM Jogadas J WHERE J.codJogadas = :codJogadas";
			Query query = session.createQuery(hql);
			query.setParameter("codJogadas", codJogadas);
			List<Jogadas> results = query.list();
			jogadasResult = results.get(0);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return jogadasResult;
	}

	public void insert(Jogadas j) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(j);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteAll() {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			String hql = "DELETE FROM Jogadas";
		    Query query = session.createQuery(hql);
		    query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void delete(Jogadas j) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(j);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}


	public void update(Jogadas j) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(j);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
