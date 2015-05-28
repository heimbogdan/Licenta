package test.guillotine.entity.element;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import bh.w2optimize.entity.Element;
import bh.w2optimize.entity.ElementList;
import bh.w2optimize.entity.FinalElement;
import bh.w2optimize.guillotine.algorithm.GuillotineCut;

public class TestFinalElement {

	private static FinalElement fi;

	@SuppressWarnings("resource")
	@BeforeClass
	public static void initializare() throws IOException,
			ClassNotFoundException {
		InputStream file = new FileInputStream("./finalElement.ser");
		fi = (FinalElement) ((ObjectInput) new ObjectInputStream(
				new BufferedInputStream(file))).readObject();
	}

	@Test
	public void testFinalElement() {
		FinalElement finEl = new FinalElement(100, 100);
		assertNotNull(finEl);
	}

	@Test
	public void testDeepCopy() {
		FinalElement finEl = FinalElement.deepCopy(new Element(10, 30, false));
		assertNotNull(finEl);

	}

	@Test
	public void testDeepCopyLimite() {

		FinalElement finEl = FinalElement.deepCopy(null);
		assertNull(finEl);
	}

	@SuppressWarnings("resource")
	@Test
	public void testDeserialization() {
		InputStream file;
		try {
			file = new FileInputStream("./finalElement.ser");
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			FinalElement fie = (FinalElement) input.readObject();
			assertNotNull(fie);

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSetArea() {
		fi.setArea(1234567);
		assertEquals(1234567, fi.getArea(), 0.0001);
	}
	
	@Test
	public void testSetAreaLimite1() {
		fi.setArea(0);
		assertEquals(0, fi.getArea(), 0);
	}
	
	@Test
	public void testSetAreaLimite2() {
		fi.setArea(-100);
		assertEquals(0, fi.getArea(), 0);
	}
	
	@Test
	public void testSetLostArea() {
		fi.setLostArea(1234567);
		assertEquals(1234567, fi.getLostArea(), 0.0001);
	}
	
	@Test
	public void testSetLostAreaLimite1() {
		fi.setLostArea(0);
		assertEquals(0, fi.getLostArea(), 0);
	}
	
	@Test
	public void testSetLostAreaLimite2() {
		fi.setLostArea(-100);
		assertEquals(0, fi.getLostArea(), 0);
	}
	
	@Test
	public void testCalculateArea(){
		fi.calculateArea();
		assertEquals(10075, fi.getArea(), 0.0001);
	}
	
	@Test
	public void testCalculateUsebleArea(){
		fi.calculateUsebleArea(fi.getChildrens().get(0));
		assertEquals(95770, fi.getUseableArea(), 0.0001);
	}
	
	@Test
	public void testCalculateLostArea(){
		fi.setUsebleArea(0);
		fi.calculateLostArea();
		assertEquals(10075, fi.getLostArea(), 0.0001);
	}
	
	@AfterClass
	public static void clear(){
		fi = null;
	}
}
