package bh.w2optimize.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLiteConnection {

	private static SQLiteConnection instance;
	
	private Connection connection;
	
	private SQLiteConnection(){
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager.getConnection("jdbc:sqlite:w2o_data.db");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SQLiteConnection getInstance(){
		if(instance == null){
			instance = new SQLiteConnection();
		}
		return instance;
	}
	
	public Connection getConnection(){
		return this.connection;
	}
}
