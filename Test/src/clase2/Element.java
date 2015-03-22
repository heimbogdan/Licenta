package clase2;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Element {

	private double length;
	private double width;
	private Element parent;
	private ArrayList<Element> childrens;
	private char position;
	private Point2D point;
	private boolean used;

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

	public double area() {
		return this.length * this.width;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Element(this.length, this.width);
	}

	public void addChild(Element e) {
		double p = 0;
		Point2D point = new Point2D.Double();
		if (!this.childrens.isEmpty()) {
			if (this.position == 'V') {
				for (Element s : this.childrens) {
					p += s.getLength();
				}
				point.setLocation(p, this.getPoint().getY());
			} else {
				for (Element s : this.childrens) {
					p += s.getWidth();
				}
				point.setLocation(this.getPoint().getX(), p);
			}
			e.setPoint(point);
		} else {
			e.setPoint(this.point);
		}
		e.setParent(this);
		this.childrens.add(e);
	}
}
