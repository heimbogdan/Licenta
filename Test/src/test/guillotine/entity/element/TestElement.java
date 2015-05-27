package test.guillotine.entity.element;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import bh.w2optimize.entity.Element;

public class TestElement{

	private static ArrayList<Element> lista;
	
	@BeforeClass
	public static void initializare(){
		lista = new ArrayList<Element>();
		lista.add(new Element(10, 10, true));
		lista.add(new Element(123, 24, false));
	}
	
	@Test
	public void testElementConstructor(){
		Element el = new Element();
		assertNotNull(el);
		
	}
	
	@Test
	public void testElementConstructor2(){
		Element el2 = new Element(10, 10, true);
		assertNotNull(el2);
		assertEquals(10, el2.getLength(),0.0001);
		assertEquals(true, el2.isRotate());
	}
	
	@Test
	public void testElementConstructorLimit(){
		Element el3 = new Element(-10, -100, false);
		assertNotNull(el3);
		assertEquals(10, el3.getLength(), 0.0001);
		assertEquals(100, el3.getWidth(),0.0001);
	}
	
	@Test
	public void testAddChild(){
		Element el = new Element();
		assertNotNull(el);
		for(Element e : lista){
			el.addChild(e);
		}
		
		assertArrayEquals(lista.toArray(), el.getChildrens().toArray());
		
		
		el.addChild(null);
		assertArrayEquals(lista.toArray(), el.getChildrens().toArray());
	}

}
