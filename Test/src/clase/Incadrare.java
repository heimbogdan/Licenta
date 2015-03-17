package clase;

import java.util.ArrayList;

public class Incadrare extends ArrayList<ArrayList<ArrayList<Placa>>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public double totalLat(int index) {
		ArrayList<ArrayList<Placa>> set = this.get(index);
		double sum = 0;
		for (ArrayList<Placa> set2 : set) {
			double max = 0;
			for (Placa pl : set2) {
				double latime = pl.getLatime();
				max = max < latime ? latime : max;
			}
			sum += max;
		}
		return sum;
	}

	@Override
	public String toString() {
		String str = "";
		int nrPlaca = 1;
		for (ArrayList<ArrayList<Placa>> placa : this) {
			str += "Placa " + nrPlaca + " :\n";
			int nrSet = 1;
			for (ArrayList<Placa> set : placa) {
				str += "	- Set " + nrSet + " : " + set + "\n";
				nrSet++;
			}
			nrPlaca++;
		}
		return str;
	}
}
