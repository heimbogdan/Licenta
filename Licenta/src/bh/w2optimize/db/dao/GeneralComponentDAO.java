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
import bh.w2optimize.entity.GeneralComponent;

public class GeneralComponentDAO {
	
	private final static Logger log = Logger.getLogger(GeneralComponentDAO.class);
	
	private GeneralComponentDAO(){
		
	}

	public static void insert(GeneralComponent generalComponent){
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
			session.insert(generalComponent);
//			session.persist(generalComponent);
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
	
	public static void update(GeneralComponent generalComponent){
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
			session.update(generalComponent);
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
	
	public static void delete(GeneralComponent generalComponent){
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
			session.delete(generalComponent);
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
	public static List<GeneralComponent> getAll(){
		List<GeneralComponent> list = null;
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
			SQLQuery q4 = session.createSQLQuery("select * from generalcomponent");
			q4.addEntity(GeneralComponent.class);
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
	public static GeneralComponent getById(int id){
		GeneralComponent board = null;
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
			SQLQuery q4 = session.createSQLQuery("select * from generalcomponent where id=" + id);
			q4.addEntity(GeneralComponent.class);
			List<GeneralComponent> list = q4.list();
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
	public static GeneralComponent getByCode(String code){
		GeneralComponent board = null;
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
			SQLQuery q4 = session.createSQLQuery("select * from generalcomponent where code='" + code + "'");
			q4.addEntity(GeneralComponent.class);
			List<GeneralComponent> list = q4.list();
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
