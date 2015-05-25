package bh.w2optimize.db.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bh.w2optimize.db.connection.SQLiteConnection;
import bh.w2optimize.entity.WoodBoard;
import bh.w2optimize.entity.WoodBoardPiece;

public class WoodBoardPiceDAO {

	public static void insert(WoodBoardPiece boardPice){
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
	
	public static void update(WoodBoardPiece boardPice){
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
	
	public static void delete(WoodBoardPiece boardPice){
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
	public static List<WoodBoardPiece> getAll(){
		List<WoodBoardPiece> list = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		try {
			SQLQuery q4 = session.createSQLQuery("select * from woodboard");
			q4.addEntity(WoodBoardPiece.class);
			list = q4.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static WoodBoard getById(int id){
		WoodBoardPiece board = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		try {
			SQLQuery q4 = session.createSQLQuery("select * from woodboard where id=" + id);
			q4.addEntity(WoodBoardPiece.class);
			List<WoodBoardPiece> list = q4.list();
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
	public static List<WoodBoardPiece> getByCode(String code){
		List<WoodBoardPiece> list = null;
		SQLiteConnection conn = SQLiteConnection.getInstance();
		Session session = conn.getSession();
		try {
			SQLQuery q4 = session.createSQLQuery("select * from woodboard where code='" + code + "'");
			q4.addEntity(WoodBoardPiece.class);
			list = q4.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return list;
	}
}
