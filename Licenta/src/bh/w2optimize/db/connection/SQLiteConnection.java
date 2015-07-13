package bh.w2optimize.db.connection;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Settings;
import org.hibernate.connection.C3P0ConnectionProvider;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.impl.SessionFactoryImpl;
import org.sqlite.SQLite;
import org.w3c.dom.Document;

/**
 * Class used to initialize the connection to the database
 * 
 * @author bogdan.heim
 * @version 1.0.1
 * @since 22.04.2015
 *
 */
public final class SQLiteConnection {

	private static SQLiteConnection instance;

	private final static Logger log = Logger.getLogger(SQLiteConnection.class);
	
	private SessionFactory factory;
	
	private SQLiteConnection() {
		try {
			Configuration cfg=new Configuration();
			cfg.configure("hibernate.cfg.xml");
			factory=cfg.buildSessionFactory();
		} catch (Exception e) {
			if(log.isDebugEnabled()){
				log.error(e.getStackTrace().toString());
			}
		}
	}

	public static SQLiteConnection getInstance() {
		if (instance == null) {
			instance = new SQLiteConnection();
		}
		return instance;
	}
	
	public static void closeConnection(){
		instance = null;
	}

	public StatelessSession getSession() {
		return this.factory.openStatelessSession();
	}
}
