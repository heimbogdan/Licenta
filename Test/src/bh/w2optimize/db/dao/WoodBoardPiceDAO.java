package bh.w2optimize.db.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bh.w2optimize.db.connection.SQLiteConnection;
import bh.w2optimize.entity.WoodBoard;
import bh.w2optimize.entity.WoodBoardPice;

public class WoodBoardPiceDAO {
	
	private WoodBoardPiceDAO(){
		
	}

	public static void insert(WoodBoardPice boardPice){
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.persist(boardPice);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	public static void update(WoodBoardPice boardPice){
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(boardPice);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	public static void delete(WoodBoardPice boardPice){
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(boardPice);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<WoodBoardPice> getAll(){
		List<WoodBoardPice> list = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		try {
			SQLQuery q4 = session.createSQLQuery("select * from woodboardpice");
			q4.addEntity(WoodBoardPice.class);
			list = q4.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static WoodBoard getById(int id){
		WoodBoardPice board = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		try {
			SQLQuery q4 = session.createSQLQuery("select * from woodboardpice where id=" + id);
			q4.addEntity(WoodBoardPice.class);
			List<WoodBoardPice> list = q4.list();
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
	public static List<WoodBoardPice> getByCode(String code){
		List<WoodBoardPice> list = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		try {
			SQLQuery q4 = session.createSQLQuery("select * from woodboardpice where code='" + code + "'");
			q4.addEntity(WoodBoardPice.class);
			list = q4.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return list;
	}
}
