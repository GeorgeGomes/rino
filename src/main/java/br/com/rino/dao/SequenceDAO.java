package br.com.rino.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.rino.entity.Sequence;
import br.com.rino.util.HibernateUtil;

public class SequenceDAO {
	private Session session;
	private Transaction transaction;
	private List<Sequence> sequenceList;
	
	public List<Sequence> getList(){
		Criteria criteria;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(Sequence.class);
			sequenceList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return sequenceList;
	}
	
	public Sequence edit(Long codSequence){
		Criteria criteria;
		Sequence sequenceResult = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
					
			String hql = "FROM Sequence S WHERE S.codSequence = :codSequence";
			Query query = session.createQuery(hql);
			query.setParameter("codSequence", codSequence);
			List<Sequence> results = query.list();
			sequenceResult = results.get(0);
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return sequenceResult;
	}
	
	public void insert(Sequence s) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(s);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void delete(Sequence s) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(s);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void update(Sequence s) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(s);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
