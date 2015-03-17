package teste;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import clase.Permutari;
import clase.Placa;

public class TestV1 {

	@Test
	public void testPermuta() {
		ArrayList<Placa> placi = new ArrayList<Placa>();
		Placa PAL = new Placa(285, 250);
		placi.add(new Placa(50, 20));
		placi.add(new Placa(142, 80));
		placi.add(new Placa(100, 50));
		placi.add(new Placa(75, 100));
		placi.add(new Placa(90, 60));
		placi.add(new Placa(100, 40));
		placi.add(new Placa(85, 30));
		placi.add(new Placa(43, 38));
		placi.add(new Placa(20, 15));
		placi.add(new Placa(20, 14));
		placi.add(new Placa(23, 60));
		placi.add(new Placa(20, 20));
		placi.add(new Placa(60, 100));
		placi.add(new Placa(25.5, 21.5));
		placi.add(new Placa(50.6, 33.4));
		placi.add(new Placa(20, 30.6));
		placi.add(new Placa(100, 85.7));
		placi.add(new Placa(23, 15));
		ArrayList<ArrayList<Placa>> expect = new ArrayList<ArrayList<Placa>>();
		ArrayList<Placa> set1 = new ArrayList<Placa>();
		ArrayList<Placa> set2 = new ArrayList<Placa>();
		ArrayList<Placa> set3 = new ArrayList<Placa>();
		ArrayList<Placa> set4 = new ArrayList<Placa>();
		set1.add(new Placa(50, 20));
		set1.add(new Placa(142, 80));
		set1.add(new Placa(75, 100));
		set2.add(new Placa(100, 50));
		set2.add(new Placa(90, 60));
		set2.add(new Placa(85, 30));
		set3.add(new Placa(100, 40));
		set3.add(new Placa(43, 38));
		set3.add(new Placa(20, 15));
		set3.add(new Placa(20, 14));
		set3.add(new Placa(23, 60));
		set3.add(new Placa(20, 20));
		set3.add(new Placa(50.6, 33.4));
		set4.add(new Placa(60, 100));
		set4.add(new Placa(25.5, 21.5));
		set4.add(new Placa(20, 30.6));
		set4.add(new Placa(100, 85.7));
		set4.add(new Placa(23, 15));
		expect.add(set1);
		expect.add(set2);
		expect.add(set3);
		expect.add(set4);
		try {
			ArrayList<ArrayList<Placa>> result = Permutari.permuta(placi, PAL);
			assertEquals(expect.toString(), result.toString());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

}
