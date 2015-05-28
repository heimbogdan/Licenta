package test.guillotine.db;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Test;

import bh.w2optimize.db.connection.SQLiteConnection;

public class SQLiteConnectionTest {

	@Test
	public void testGetInstance() {
		SQLiteConnection sql = SQLiteConnection.getInstance();
		SQLiteConnection sql2 = SQLiteConnection.getInstance();
		assertEquals(sql, sql2);
	}

	@Test (expected = IllegalAccessException.class)
	public void testGetForcedInstance() throws InstantiationException, IllegalAccessException {
		
		SQLiteConnection sql2 = SQLiteConnection.class.newInstance();
	}
	
	@Test
	public void testGetSession(){
		SQLiteConnection sql = SQLiteConnection.getInstance();
		assertNotNull(sql);
		
		Session ses = sql.getSession();
		assertNotNull(ses);
	}

}
