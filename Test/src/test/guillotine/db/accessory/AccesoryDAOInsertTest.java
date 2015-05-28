package test.guillotine.db.accessory;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import bh.w2optimize.db.connection.SQLiteConnection;
import bh.w2optimize.db.dao.AccessoryDAO;
import bh.w2optimize.entity.Accessory;

public class AccesoryDAOInsertTest {

	private static Accessory accessory;
	
	@BeforeClass
	public static void initialiare(){
		accessory = new Accessory("EUR_S", "Euro Surub", 0.09);
		SQLiteConnection.getInstance();
	}
	
	@Test
	public void testInsert() {
		
		assertNotNull(accessory);
		
		AccessoryDAO.insert(accessory);		
		
		Accessory acc = AccessoryDAO.getByCode("EUR_S");
		
		assertEquals("Id-ul nu este corect!",accessory.getId(), acc.getId());
		assertEquals("Codul nu este corect!",accessory.getCode(), acc.getCode());
		assertEquals("Numele nu este corect!",accessory.getName(), acc.getName());
		assertEquals("Pretul nu este corect!",accessory.getPrice(), acc.getPrice(),0.0001);
	}

	@AfterClass
	public static void clear(){
		accessory = null;
	}
}
