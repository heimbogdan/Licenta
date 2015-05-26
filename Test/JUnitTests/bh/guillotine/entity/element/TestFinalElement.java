package bh.guillotine.entity.element;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import bh.w2optimize.entity.Element;
import bh.w2optimize.entity.ElementList;
import bh.w2optimize.entity.FinalElement;
import bh.w2optimize.guillotine.algorithm.GuillotineCut;

public class TestFinalElement {

	private static FinalElement fi;

	@BeforeClass
	public static void initializare() {

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
	public void testDeserialization(){
		InputStream file;
		try {
			file = new FileInputStream("./finalElement.ser");
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			fi = (FinalElement) input.readObject();
			assertNotNull(fi);
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
