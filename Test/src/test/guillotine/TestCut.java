package test.guillotine;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;
import bh.w2optimize.entity.Element;
import bh.w2optimize.entity.ElementList;
import bh.w2optimize.guillotine.algorithm.GuillotineCut;

public class TestCut {

	private static GuillotineCut cutter;
	
	@BeforeClass
	public static void initializare(){
		cutter = new GuillotineCut(true);
	}

	@Test
	public void testRotate(){
		Element elem = new Element(10, 40, true);
		assertNotNull(elem);
		
		cutter.rotate(elem);
		assertNotNull(elem);
		
		assertEquals(40, elem.getLength(),39.9999);
		assertEquals(10, elem.getWidth(), 9.9999);
	}
	
	
	
}
