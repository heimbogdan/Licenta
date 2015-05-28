package test.guillotine.entity.element;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import bh.w2optimize.entity.ElementList;
import bh.w2optimize.entity.Element;

public class TestElementList {

	private static ElementList list;

	@BeforeClass
	public static void initializare() {
		try {
			list = new ElementList();
			List<String> scan = Files.readAllLines(new File(
					"./ListaElemente.txt").toPath());
			for (String s : scan) {
				String[] res = s.split(";");
				list.addMore(Double.valueOf(res[0]), Double.valueOf(res[1]),
						Boolean.valueOf(res[2]), Integer.parseInt(res[3]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testElementList() {
		ElementList list = new ElementList();
		assertNotNull(list);
	}

	@Test
	public void testComparator() {
		ElementList lista = new ElementList();
		lista.add(new Element(100, 25, true));
		lista.add(new Element(25, 50, false));
		Element el = new Element(200, 100, false);
		lista.add(el);

		lista.sort(lista.comparator);
		assertEquals(el, lista.get(0));

		list.sort(list.comparator);
	}

	@Test
	public void testAddMore() {
		ElementList li = new ElementList();
		assertNotNull(li);
		
		li.addMore(10, 10, false, 1);
		assertEquals(1, li.size());
	}
	
	@Test
	public void testAddMore2() {
		ElementList li = new ElementList();
		assertNotNull(li);
		
		li.addMore(10, 10, false, 4);
		assertEquals(4, li.size());
		li.addMore(20, 20, true, 5);
		assertEquals(9, li.size());
	}
	
	@Test
	public void testAddMoreLimite() {
		ElementList li = new ElementList();
		assertNotNull(li);
		
		li.addMore(10, 10, false, 4);
		assertEquals(4, li.size());
		li.addMore(20, 20, true, 0);
		assertEquals(4, li.size());
	}
	
	// mock
	@Test
	public void testIsAllUsed(){
		Element el1 = mock(Element.class);
		when(el1.isUsed()).thenReturn(true);
		ElementList li = new ElementList();
		li.add(el1);
		
		assertTrue(li.isAllUsed());
	}
	
	@Test
	public void testIsAllUsed2(){
		Element el1 = mock(Element.class);
		Element el2 = mock(Element.class);
		when(el1.isUsed()).thenReturn(true);
		when(el2.isUsed()).thenReturn(false);
		ElementList li = new ElementList();
		li.add(el1);
		li.add(el2);
		assertFalse(li.isAllUsed());
	}


	@Test
	public void testClone() {
		ElementList list1 = (ElementList) list.clone();
		for (int i = 0; i < list.size(); i++) {
			assertEquals("Error at index " + i + " at getLength()", list.get(i)
					.getLength(), list1.get(i).getLength(), 0.0001);
			assertEquals("Error at index " + i + " at getLWidth()", list.get(i)
					.getWidth(), list1.get(i).getWidth(), 0.0001);
			assertEquals(list.get(i).isRotate(), list1.get(i).isRotate());
		}
	}
}
