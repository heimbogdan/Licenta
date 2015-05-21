package bh.w2optimize.entity;

import java.io.Serializable;

public class GeneralElement extends Element implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5585714780386931738L;
	
	private int lengthCode;
	private int widthCode;
	private int percent1;
	private int percent2;

	public int getLengthCode() {
		return lengthCode;
	}

	public void setLengthCode(int lengthCode) {
		this.lengthCode = lengthCode;
	}

	public int getWidthCode() {
		return widthCode;
	}

	public void setWidthCode(int widthCode) {
		this.widthCode = widthCode;
	}

	public int getPercent1() {
		return percent1;
	}

	public void setPercent1(int percent1) {
		this.percent1 = percent1;
	}

	public int getPercent2() {
		return percent2;
	}

	public void setPercent2(int percent2) {
		this.percent2 = percent2;
	}

	public GeneralElement(double length, double width, boolean rotate,
			int lengthCode, int widthCode, int percent1, int percent2) {
		super(length, width, rotate);
		this.lengthCode = lengthCode;
		this.widthCode = widthCode;
		this.percent1 = percent1;
		this.percent2 = percent2;
	}
	
	
	
	public GeneralElement() {
		super();
	}

	public Element toElement(){
		Element el = new Element(getLength(), getWidth(), isRotate());
		el.setComponentCode(getComponentCode());
		el.setName(getName());
		return el;
	}

	@Override
	public String toString() {
		return "GeneralElement [ " + super.toString() + " lengthCode=" + lengthCode + ", widthCode="
				+ widthCode + ", percent1=" + percent1 + ", percent2="
				+ percent2 + "]";
	}

	
}
