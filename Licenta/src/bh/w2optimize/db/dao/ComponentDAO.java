package bh.w2optimize.db.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import bh.w2optimize.db.connection.SQLiteConnection;
import bh.w2optimize.entity.Component;

public class ComponentDAO {
	
	private final static Logger log = Logger.getLogger(ComponentDAO.class);
	
	private ComponentDAO(){
		
	}

	public static void insert(Component component){
		SQLiteConnection conn = SQLiteConnection.getInstance();
		StatelessSession session = conn.getSession();
		try {
			session.connection().setAutoCommit(true);
		} catch (SQLException e1) {
			if(log.isDebugEnabled()){
				log.error(e1.getMessage());
			}
		}
		Transaction transaction = session.beginTransaction();
		try {
			session.insert(component);
//			session.persist(component);
			transaction.commit();
		} catch (Exception e) {
			if(log.isDebugEnabled()){
				log.error(e.getMessage());
			}
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	public static void update(Component component){
		SQLiteConnection conn = SQLiteConnection.getInstance();
		StatelessSession session = conn.getSession();
		try {
			session.connection().setAutoCommit(true);
		} catch (SQLException e1) {
			if(log.isDebugEnabled()){
				log.error(e1.getMessage());
			}
		}
		Transaction transaction = session.beginTransaction();
		try {
			session.update(component);
			transaction.commit();
		} catch (Exception e) {
			if(log.isDebugEnabled()){
				log.error(e.getMessage());
			}
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	public static void delete(Component component){
		SQLiteConnection conn = SQLiteConnection.getInstance();
		StatelessSession session = conn.getSession();
		try {
			session.connection().setAutoCommit(true);
		} catch (SQLException e1) {
			if(log.isDebugEnabled()){
				log.error(e1.getMessage());
			}
		}
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(component);
			transaction.commit();
		} catch (Exception e) {
			if(log.isDebugEnabled()){
				log.error(e.getMessage());
			}
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Component> getAll(){
		List<Component> list = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		StatelessSession session = conn.getSession();
		try {
			session.connection().setAutoCommit(true);
		} catch (SQLException e1) {
			if(log.isDebugEnabled()){
				log.error(e1.getMessage());
			}
		}
		try {
			SQLQuery q4 = session.createSQLQuery("select * from component");
			q4.addEntity(Component.class);
			list = q4.list();
		} catch (Exception e) {
			if(log.isDebugEnabled()){
				log.error(e.getMessage());
			}
		}finally {
			session.close();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static Component getById(int id){
		Component board = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		StatelessSession session = conn.getSession();
		try {
			session.connection().setAutoCommit(true);
		} catch (SQLException e1) {
			if(log.isDebugEnabled()){
				log.error(e1.getMessage());
			}
		}
		try {
			SQLQuery q4 = session.createSQLQuery("select * from component where id=" + id);
			q4.addEntity(Component.class);
			List<Component> list = q4.list();
			if(list != null && !list.isEmpty()){
				board = list.get(0);
			}
		} catch (Exception e) {
			if(log.isDebugEnabled()){
				log.error(e.getMessage());
			}
		}finally {
			session.close();
		}
		return board;
	}
	
	@SuppressWarnings("unchecked")
	public static Component getByCode(String code){
		Component board = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		StatelessSession session = conn.getSession();
		try {
			session.connection().setAutoCommit(true);
		} catch (SQLException e1) {
			if(log.isDebugEnabled()){
				log.error(e1.getMessage());
			}
		}
		try {
			SQLQuery q4 = session.createSQLQuery("select * from component where code='" + code + "'");
			q4.addEntity(Component.class);
			List<Component> list = q4.list();
			if(list != null && !list.isEmpty()){
				board = list.get(0);
			}
		} catch (Exception e) {
			if(log.isDebugEnabled()){
				log.error(e.getMessage());
			}
		}finally {
			session.close();
		}
		return board;
	}
}
