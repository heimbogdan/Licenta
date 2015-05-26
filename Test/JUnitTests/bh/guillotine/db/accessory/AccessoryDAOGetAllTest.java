package bh.guillotine.db.accessory;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import bh.w2optimize.db.dao.AccessoryDAO;
import bh.w2optimize.entity.Accessory;

public class AccessoryDAOGetAllTest {

	
	@Test
	public void testGetAll() {
		
		List<Accessory> list = new ArrayList<Accessory>();
		list.add(new Accessory("C23D", "Balama", 2.6));
		list.add(new Accessory("FR3S", "Maner", 3.4));
		list.add(new Accessory("GHRE", "Cui", 0.05));
		list.add(new Accessory("BAR4", "Bara haine", 9.4));
		list.add(new Accessory("AERS", "Suport haine", 12.2));
		
		for(Accessory acc : list){
			AccessoryDAO.insert(acc);
		}
		List<Accessory> list2 = AccessoryDAO.getAll();

		assertTrue(list.size() == list2.size());
		
		for(int i=0; i<list.size(); i++){
			Accessory accessory = list.get(i);
			Accessory acc = list2.get(i);
			assertEquals("Id-ul nu este corect!",accessory.getId(), acc.getId());
			assertEquals("Codul nu este corect!",accessory.getCode(), acc.getCode());
			assertEquals("Numele nu este corect!",accessory.getName(), acc.getName());
			assertEquals("Pretul nu este corect!",accessory.getPrice(), acc.getPrice(),0.0001);
		}
		
		for(Accessory acc : list2){
			AccessoryDAO.delete(acc);
		}
		
		assertTrue(AccessoryDAO.getAll().isEmpty());
	}

}
