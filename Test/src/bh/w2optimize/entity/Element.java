package bh.w2optimize.entity;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Class used to create an element, regardless of final utility
 * 
 * @author bogdan.heim
 * @version 1.0
 * @since 22.04.2015
 */
public class Element {

	/**
	 * Variable that holds the element's length
	 */
	private double length;

	/**
	 * Variable that holds the element's width
	 */
	private double width;

	/**
	 * Reference to parent element, which the current board was cut from
	 */
	private Element parent;

	/**
	 * ArrayList containing the children resulting from the cut applied to the
	 * current element
	 */
	private ArrayList<Element> childrens;

	/**
	 * The variable takes the value of 'V' (vertical) or 'H' (horizontal), that
	 * expresses the cut orientation applied to the element
	 */
	private char position;

	/**
	 * Point2D coordinates that holds the top left tip of the element
	 */
	private Point2D point;

	/**
	 * Boolean that says if the current element is one of the necessary
	 */
	private boolean used;

	/**
	 * Boolean that says if the current element is perceived as a loss
	 */
	private boolean loss;

	public double getLength() {
		return length;
	}

	public void setLength(final double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(final double width) {
		this.width = width;
	}

	public Element getParent() {
		return parent;
	}

	public void setParent(final Element parent) {
		this.parent = parent;
	}

	public ArrayList<Element> getChildrens() {
		return childrens;
	}

	public void setChildrens(final ArrayList<Element> childrens) {
		this.childrens = childrens;
	}

	public Point2D getPoint() {
		return point;
	}

	public void setPoint(final Point2D point) {
		this.point = point;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(final boolean used) {
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

	public void setLoss(final boolean loss) {
		this.loss = loss;
	}

	public Element(final double length, final double width) {
		super();
		this.length = length;
		this.width = width;
		// this.parent = null;
		this.childrens = new ArrayList<Element>();
		this.point = new Point2D.Double();
		this.used = false;
	}

	@Override
	public String toString() {
		return this.length + " x " + this.width;
	}

	/**
	 * The method to calculate the element's surface
	 * 
	 * @return Surface
	 */
	public double area() {
		return this.length * this.width;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Element(this.length, this.width);
	}

	public Element cloneElement() {
		final Element element = new Element(this.length, this.width);
		element.setChildrens(this.getChildrens());
		element.setParent(this.parent);
		element.setPoint(this.point);
		element.setLoss(this.loss);
		element.setUsed(this.used);
		return element;
	}

	/**
	 * The method to add a child element. Add reference to the parent and child element and sets its position.
	 * 
	 * @param element
	 *            - The element will be added to the list of children of the current element
	 */
	public void addChild(final Element element) {
		double p = 0;
		final Point2D point = new Point2D.Double();
		if (!this.childrens.isEmpty()) {
			if (this.position == 'V') {
				for (final Element s : this.childrens) {
					p += s.getLength();
				}
				point.setLocation(p + this.getPoint().getX(), this.getPoint()
						.getY());
			} else {
				for (final Element s : this.childrens) {
					p += s.getWidth();
				}
				point.setLocation(this.getPoint().getX(), p
						+ this.getPoint().getY());
			}
			element.setPoint(point);
		} else {
			element.setPoint(this.point);
		}
		element.setParent(this);
		this.childrens.add(element);
	}

	public void addRoot(final Element e) {
		e.setParent(this);
		this.childrens.add(e);
	}
}
