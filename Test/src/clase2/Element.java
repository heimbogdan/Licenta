package clase2;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Clasa folosita pentru a crea o placa, indiferent de utilitatea finala
 * 
 * @author bogdan.heim
 * 
 */
public class Element {

	/**
	 * Variabila ce retine lungimea placii
	 */
	private double length;

	/**
	 * Variabila ce retine latimea placii
	 */
	private double width;

	/**
	 * Referinta catre placa parinte, placa din care a fost taiata placa curenta
	 */
	private Element parent;

	/**
	 * ArrayList ce contine copii rezultati din aplicarea unei taieturi pe placa
	 * curenta
	 */
	private ArrayList<Element> childrens;

	/**
	 * Variabila ce ia valoarea de 'V' (verticala) sau 'H' (orizontala) ce
	 * exprima orientarea taieturii aplicate pe placa
	 */
	private char position;

	/**
	 * Point2D ce retine coordonatele varfului din stanga sus a placii
	 */
	private Point2D point;

	/**
	 * Variabila boleana ce declara daca placa curenta este una din placiile
	 * necesare
	 */
	private boolean used;

	/**
	 * Variabila boleana ce declara daca placa curenta este perceputa ca
	 * pierdere
	 */
	private boolean loss;

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public Element getParent() {
		return parent;
	}

	public void setParent(Element parent) {
		this.parent = parent;
	}

	public ArrayList<Element> getChildrens() {
		return childrens;
	}

	public void setChildrens(ArrayList<Element> childrens) {
		this.childrens = childrens;
	}

	public Point2D getPoint() {
		return point;
	}

	public void setPoint(Point2D point) {
		this.point = point;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public char getPosition() {
		return position;
	}

	public void setPosition(char position) {
		this.position = position;
	}

	public boolean isLoss() {
		return loss;
	}

	public void setLoss(boolean loss) {
		this.loss = loss;
	}

	public Element(double length, double width) {
		super();
		this.length = length;
		this.width = width;
		this.parent = null;
		this.childrens = new ArrayList<Element>();
		this.point = new Point2D.Double();
		this.used = false;
	}

	@Override
	public String toString() {
		return this.length + " x " + this.width;
	}

	/**
	 * Metoda ce calculeaza suprafata placii
	 * 
	 * @return Suprafata
	 */
	public double area() {
		return this.length * this.width;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Element(this.length, this.width);
	}

	/**
	 * Metoda ce adauga un copil placii initiale. Adauga referinta catre parinte
	 * a placii copil si ii seteaza pozitia.
	 * 
	 * @param e
	 *            - Placa care va fi adaugata in lista de copii a placii curente
	 */
	public void addChild(Element e) {
		double p = 0;
		Point2D point = new Point2D.Double();
		if (!this.childrens.isEmpty()) {
			if (this.position == 'V') {
				for (Element s : this.childrens) {
					p += s.getLength();
				}
				point.setLocation(p + this.getPoint().getX(), this.getPoint()
						.getY());
			} else {
				for (Element s : this.childrens) {
					p += s.getWidth();
				}
				point.setLocation(this.getPoint().getX(), p
						+ this.getPoint().getY());
			}
			e.setPoint(point);
		} else {
			e.setPoint(this.point);
		}
		e.setParent(this);
		this.childrens.add(e);
	}

	public void addRoot(Element e) {
		e.setParent(this);
		this.childrens.add(e);
	}
}
