package bh.w2optimize.db.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bh.w2optimize.db.connection.SQLiteConnection;
import bh.w2optimize.entity.GeneralComponent;

public class GeneralComponentDAO {

	public static void insert(GeneralComponent generalComponent){
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.persist(generalComponent);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	public static void update(GeneralComponent generalComponent){
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(generalComponent);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	public static void delete(GeneralComponent generalComponent){
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(generalComponent);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<GeneralComponent> getAll(){
		List<GeneralComponent> list = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		try {
			SQLQuery q4 = session.createSQLQuery("select * from woodboard");
			q4.addEntity(GeneralComponent.class);
			list = q4.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static GeneralComponent getById(int id){
		GeneralComponent board = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		try {
			SQLQuery q4 = session.createSQLQuery("select * from woodboard where id=" + id);
			q4.addEntity(GeneralComponent.class);
			List<GeneralComponent> list = q4.list();
			if(list != null && !list.isEmpty()){
				board = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return board;
	}
	
	@SuppressWarnings("unchecked")
	public static GeneralComponent getByCode(String code){
		GeneralComponent board = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		try {
			SQLQuery q4 = session.createSQLQuery("select * from woodboard where code='" + code + "'");
			q4.addEntity(GeneralComponent.class);
			List<GeneralComponent> list = q4.list();
			if(list != null && !list.isEmpty()){
				board = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return board;
	}
}
