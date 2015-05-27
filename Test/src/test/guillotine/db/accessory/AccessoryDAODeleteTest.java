package test.guillotine.db.accessory;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import bh.w2optimize.db.dao.AccessoryDAO;
import bh.w2optimize.entity.Accessory;

public class AccessoryDAODeleteTest {

	private static Accessory accessory;
	
	@BeforeClass
	public static void initialiare(){
		
		accessory = AccessoryDAO.getByCode("EUR_S");
		
	}
	
	@Test
	public void testDelete() {

		AccessoryDAO.delete(accessory);
		Accessory acc = AccessoryDAO.getByCode(accessory.getCode());
		
		assertTrue(acc == null);
	}

}
