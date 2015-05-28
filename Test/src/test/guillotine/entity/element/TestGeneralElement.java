package test.guillotine.entity.element;

import static org.junit.Assert.*;

import org.junit.Test;

import bh.w2optimize.entity.Element;
import bh.w2optimize.entity.GeneralElement;

public class TestGeneralElement {

	@Test
	public void testGeneralElement() {
		
		GeneralElement gen = new GeneralElement();
		assertNotNull(gen);
	}

	@Test
	public void testGeneralElement2() {
		
		GeneralElement gen = new GeneralElement(100, 100, false, 1, 2, 60, 100);
		assertNotNull(gen);
		assertEquals(60, gen.getPercent1(), 0.001);
		assertEquals(100, gen.getPercent2(), 0.001);
	}

	@Test
	public void testGeneralElementLimite1() {
		
		GeneralElement gen = new GeneralElement(0, 0, false, 1, 2, 0, 0);
		assertNotNull(gen);
		assertEquals(0, gen.getPercent1(), 0);
		assertEquals(0, gen.getPercent2(), 0);
	}
	
	@Test
	public void testGeneralElementLimite2() {
		
		GeneralElement gen = new GeneralElement(100, 100, false, 1, 2, -60, -100);
		assertNotNull(gen);
		assertEquals(60, gen.getPercent1(), 0.001);
		assertEquals(100, gen.getPercent2(), 0.001);
	}
	
	@Test
	public void testGeneralElementLimite3() {
		
		GeneralElement gen = new GeneralElement(-100, -100, false, 1, 2, -60, -100);
		assertNotNull(gen);
		assertEquals(60, gen.getPercent1(), 0.001);
		assertEquals(100, gen.getPercent2(), 0.001);
		assertEquals(100, gen.getLength(), 0.001);
		assertEquals(100, gen.getWidth(), 0.001);
	}
	
	@Test
	public void testToElement() {
		GeneralElement gen = new GeneralElement(100, 100, false, 1, 2, 60, 100);
		assertNotNull(gen);
		assertTrue(gen instanceof GeneralElement);
		assertTrue(gen instanceof Element);
		
		Element el = gen.toElement();
		assertTrue(el instanceof Element);
		assertFalse(el instanceof GeneralElement);
	}
	
	@Test
	public void testSetLengthCode(){
		GeneralElement gen = new GeneralElement(100, 100, false, 1, 2, 60, 100);
		assertNotNull(gen);
		
		assertEquals(1, gen.getLengthCode());
		gen.setLengthCode(2);
		assertEquals(2, gen.getLengthCode());
	}
	
	@Test
	public void testSetWidthCode(){
		GeneralElement gen = new GeneralElement(100, 100, false, 1, 2, 60, 100);
		assertNotNull(gen);
		
		assertEquals(2, gen.getWidthCode());
		gen.setWidthCode(1);
		assertEquals(1, gen.getWidthCode());
	}
	
	// errors
	
	@Test(expected = NumberFormatException.class)
	public void testSetLengthCodeExcention(){
		GeneralElement gen = new GeneralElement(100, 100, false, 1, 2, 60, 100);
		assertNotNull(gen);
		gen.setLengthCode(5);
		fail("NumberFormatException not thrown");
	}
	
	@Test(expected = NumberFormatException.class)
	public void testSetWidthCodeExcention(){
		GeneralElement gen = new GeneralElement(100, 100, false, 1, 2, 60, 100);
		assertNotNull(gen);
		
		gen.setWidthCode(5);
		fail("NumberFormatException not thrown");
	}
	
	@Test(expected = NumberFormatException.class)
	public void testGeneralElementException() {
		
		GeneralElement gen = new GeneralElement(100, 100, false, 5, 5, 60, 100);
		fail("NumberFormatException not thrown");
	}
}
