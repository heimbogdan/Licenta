package bh.guillotine.entity.element;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import bh.w2optimize.entity.ElementList;
import bh.w2optimize.entity.Element;
import bh.w2optimize.entity.FinalElement;

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
			// TODO Auto-generated catch block
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
		// testare addMore
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
