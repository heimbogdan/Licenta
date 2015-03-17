package clase;

public class Placa {

	private double lungime;
	private double latime;

	public double getLungime() {
		return lungime;
	}

	public void setLungime(double lungime) {
		this.lungime = lungime;
	}

	public double getLatime() {
		return latime;
	}

	public void setLatime(double latime) {
		this.latime = latime;
	}

	public Placa(double lungime, double latime) {
		super();
		this.lungime = lungime;
		this.latime = latime;
	}

	@Override
	public String toString() {
		return lungime + " X " + latime;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Placa(this.lungime, this.latime);
	}
}
