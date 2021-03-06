package bh.w2optimize.elements;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.Logger;

/**
 * Class used to create an element, regardless of final utility
 * 
 * @author bogdan.heim
 * @version 1.0
 * @since 22.04.2015
 */
public class Element implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9209757999479173396L;
	
	private final static Logger log = Logger.getLogger(Element.class);
	
	private int id;
	private String componentCode;
	private String name;
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

	/**
	 * Boolean that says if the current element can be rotated or not
	 */
	private boolean rotate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComponentCode() {
		return componentCode;
	}

	public void setComponentCode(String componentCode) {
		this.componentCode = componentCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLength() {
		return length;
	}

	public void setLength(final double length ) {
		this.length = Math.abs(length);
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(final double width) {
		this.width = Math.abs(width);
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

	public boolean isRotate() {
		return rotate;
	}

	public void setRotate(boolean canRotate) {
		this.rotate = canRotate;
	}

	public Element(final double length, final double width, final boolean rotate) {
		super();
		this.length = Math.abs(length);
		this.width = Math.abs(width);
		this.childrens = new ArrayList<Element>();
		this.point = new Point2D.Double();
		this.used = false;
		this.rotate = rotate;
		this.name = "";
		this.componentCode = "";
	}

	public Element() {
		super();
		childrens = new ArrayList<Element>();
		point = new Point2D.Double();
		this.name = "";
		this.componentCode = "";
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
		return new Element(this.length, this.width, this.rotate);
	}

	public Element cloneElement() {
		final Element element = new Element(this.length, this.width, this.rotate);
		element.setChildrens(this.getChildrens());
		element.setParent(this.parent);
		element.setPoint(this.point);
		element.setLoss(this.loss);
		element.setUsed(this.used);
		element.setComponentCode(this.componentCode);
		element.setName(this.name);
		element.setId(this.id);
		return element;
	}

	/**
	 * The method to add a child element. Add reference to the parent and child
	 * element and sets its position.
	 * 
	 * @param element
	 *            - The element will be added to the list of children of the
	 *            current element
	 */
	public void addChild(final Element element) {
		if (element != null) {
			double p = 0;
			final Point2D point = new Point2D.Double();
			if (!this.childrens.isEmpty()) {
				if (this.position == 'V') {
					for (final Element s : this.childrens) {
						p += s.getLength();
					}
					point.setLocation(p + this.getPoint().getX(), this
							.getPoint().getY());
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
	}

	public void addRoot(final Element child) {
		child.setParent(this);
		this.childrens.add(child);
	}
	
	@Override
	public boolean equals(Object obj) {
		Element el = (Element) obj;
		if(!this.name.equals(el.getName())){
			return false;
		} else if(this.length != el.getLength()){
			return false;
		} else if (this.width != el.getWidth()){
			return false;
		} else if ( this.rotate != el.isRotate()){
			return false;
		} else if ( !this.componentCode.equals(el.getComponentCode())){
			return false;
		}
		return true;
	}
}
