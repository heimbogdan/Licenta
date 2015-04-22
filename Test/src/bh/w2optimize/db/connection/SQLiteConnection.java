package bh.w2optimize.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class used to initialize the connection to the database
 * 
 * @author bogdan.heim
 * @version 1.0.1
 * @since 22.04.2015
 *
 */
public class SQLiteConnection {

	private static SQLiteConnection instance;

	private Connection connection;

	private SQLiteConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager
					.getConnection("jdbc:sqlite:w2o_data.db");
			checkDataBase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SQLiteConnection getInstance() {
		if (instance == null) {
			instance = new SQLiteConnection();
		}
		return instance;
	}

	public Connection getConnection() {
		return this.connection;
	}

	private void checkDataBase() {
		try {
			Statement statement = this.connection.createStatement();
			statement
					.executeUpdate("create table if not exists elements (id integer primary key autoincrement, component_id integer, length double, width double)");
			statement
					.executeUpdate("create table if not exists component (id integer primary key autoincrement, name text, code text)");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
