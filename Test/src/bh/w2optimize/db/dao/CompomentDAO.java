package bh.w2optimize.db.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bh.w2optimize.db.connection.SQLiteConnection;
import bh.w2optimize.entity.Component;

public class CompomentDAO {

	public static void insert(Component component){
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.persist(component);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	public static void update(Component component){
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(component);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	public static void delete(Component component){
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(component);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Component> getAll(){
		List<Component> list = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		try {
			SQLQuery q4 = session.createSQLQuery("select * from woodboard");
			q4.addEntity(Component.class);
			list = q4.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static Component getById(int id){
		Component board = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		try {
			SQLQuery q4 = session.createSQLQuery("select * from woodboard where id=" + id);
			q4.addEntity(Component.class);
			List<Component> list = q4.list();
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
	public static Component getByCode(String code){
		Component board = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		try {
			SQLQuery q4 = session.createSQLQuery("select * from woodboard where code='" + code + "'");
			q4.addEntity(Component.class);
			List<Component> list = q4.list();
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
