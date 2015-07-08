package bh.w2optimize.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bh.w2optimize.db.connection.SQLiteConnection;
import bh.w2optimize.entity.WoodBoard;

public class WoodBoardDAO {
	
	private WoodBoardDAO(){
		
	}

	public static void insert(WoodBoard board){
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.persist(board);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	public static void update(WoodBoard board){
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(board);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	public static void delete(WoodBoard board){
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(board);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<WoodBoard> getAll(){
		List<WoodBoard> list = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		try {
			SQLQuery q4 = session.createSQLQuery("select * from woodboard");
			q4.addEntity(WoodBoard.class);
			list = q4.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static WoodBoard getById(int id){
		WoodBoard board = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		try {
			SQLQuery q4 = session.createSQLQuery("select * from woodboard where id=" + id);
			q4.addEntity(WoodBoard.class);
			List<WoodBoard> list = q4.list();
			if(list != null && !list.isEmpty()){
				board = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return board;
	}
	
	@SuppressWarnings("unchecked")
	public static WoodBoard getByCode(String code){
		WoodBoard board = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		try {
			SQLQuery q4 = session.createSQLQuery("select * from woodboard where code='" + code + "'");
			q4.addEntity(WoodBoard.class);
			List<WoodBoard> list = q4.list();
			if(list != null && !list.isEmpty()){
				board = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return board;
	}
	
	
}
