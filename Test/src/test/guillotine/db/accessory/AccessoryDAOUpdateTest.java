package test.guillotine.db.accessory;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bh.w2optimize.db.dao.AccessoryDAO;
import bh.w2optimize.entity.Accessory;

public class AccessoryDAOUpdateTest {

	private static Accessory accessory;
	
	@Before
	public void initialiare(){
		
		accessory = AccessoryDAO.getByCode("EUR_S");
		
	}
	
	@After
	public void clear(){
		accessory = null;
	}
	
	@Test
	public void testUpdate() {
		
		accessory.setName("Surub");
		
		AccessoryDAO.update(accessory);
		Accessory acc = AccessoryDAO.getByCode(accessory.getCode());
		
		assertEquals("Id-ul nu este corect!",accessory.getId(), acc.getId());
		assertEquals("Codul nu este corect!",accessory.getCode(), acc.getCode());
		assertEquals("Numele nu este corect!",accessory.getName(), acc.getName());
		assertEquals("Pretul nu este corect!",accessory.getPrice(), acc.getPrice(),0.0001);
	}

	
}
