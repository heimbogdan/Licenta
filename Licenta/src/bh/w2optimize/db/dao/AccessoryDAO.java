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
import bh.w2optimize.entity.Accessory;

public class AccessoryDAO {
	
	private final static Logger log = Logger.getLogger(AccessoryDAO.class);
	
	private AccessoryDAO(){
		
	}

	public static void insert(Accessory accessory){
		SQLiteConnection conn = SQLiteConnection.getInstance();
		StatelessSession session = conn.getSession();
		try {
			session.connection().setAutoCommit(true);
		} catch (SQLException e1) {
			if(log.isDebugEnabled()){
				log.error(e1.getStackTrace().toString());
			}
		}
		Transaction transaction = session.beginTransaction();
		try {
			session.insert(accessory);
//			session.persist(accessory);
			transaction.commit();
		} catch (Exception e) {
			if(log.isDebugEnabled()){
				log.error(e.getStackTrace().toString());
			}
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	public static void update(Accessory accessory){
		SQLiteConnection conn = SQLiteConnection.getInstance();
		StatelessSession session = conn.getSession();
		try {
			session.connection().setAutoCommit(true);
		} catch (SQLException e1) {
			if(log.isDebugEnabled()){
				log.error(e1.getStackTrace().toString());
			}
		}
		Transaction transaction = session.beginTransaction();
		try {
			session.update(accessory);
			transaction.commit();
		} catch (Exception e) {
			if(log.isDebugEnabled()){
				log.error(e.getStackTrace().toString());
			}
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	public static void delete(Accessory accessory){
		SQLiteConnection conn = SQLiteConnection.getInstance();
		StatelessSession session = conn.getSession();
		try {
			session.connection().setAutoCommit(true);
		} catch (SQLException e1) {
			if(log.isDebugEnabled()){
				log.error(e1.getStackTrace().toString());
			}
		}
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(accessory);
			transaction.commit();
		} catch (Exception e) {
			if(log.isDebugEnabled()){
				log.error(e.getStackTrace().toString());
			}
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Accessory> getAll(){
		List<Accessory> list = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		StatelessSession session = conn.getSession();
		try {
			session.connection().setAutoCommit(true);
		} catch (SQLException e1) {
			if(log.isDebugEnabled()){
				log.error(e1.getStackTrace().toString());
			}
		}
		try {
			SQLQuery q4 = session.createSQLQuery("select * from accessory");
			q4.addEntity(Accessory.class);
			list = q4.list();
		} catch (Exception e) {
			if(log.isDebugEnabled()){
				log.error(e.getStackTrace().toString());
			}
		}finally {
			session.close();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static Accessory getById(int id){
		Accessory board = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		StatelessSession session = conn.getSession();
		try {
			session.connection().setAutoCommit(true);
		} catch (SQLException e1) {
			if(log.isDebugEnabled()){
				log.error(e1.getStackTrace().toString());
			}
		}
		try {
			SQLQuery q4 = session.createSQLQuery("select * from accessory where id=" + id);
			q4.addEntity(Accessory.class);
			List<Accessory> list = q4.list();
			if(list != null && !list.isEmpty()){
				board = list.get(0);
			}
		} catch (Exception e) {
			if(log.isDebugEnabled()){
				log.error(e.getStackTrace().toString());
			}
		}finally {
			session.close();
		}
		return board;
	}
	
	@SuppressWarnings("unchecked")
	public static Accessory getByCode(String code){
		Accessory board = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		StatelessSession session = conn.getSession();
		try {
			session.connection().setAutoCommit(true);
		} catch (SQLException e1) {
			if(log.isDebugEnabled()){
				log.error(e1.getStackTrace().toString());
			}
		}
		try {
			SQLQuery q4 = session.createSQLQuery("select * from accessory where code='" + code + "'");
			q4.addEntity(Accessory.class);
			List<Accessory> list = q4.list();
			if(list != null && !list.isEmpty()){
				board = list.get(0);
			}
		} catch (Exception e) {
			if(log.isDebugEnabled()){
				log.error(e.getStackTrace().toString());
			}
		}finally {
			session.close();
		}
		return board;
	}
}
