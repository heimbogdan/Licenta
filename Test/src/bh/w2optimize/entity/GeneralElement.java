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
		if(lengthCode >= 1 && lengthCode <=3){
			this.lengthCode = lengthCode;
		} else {
			throw new NumberFormatException("Number is not in range!");
		}
	}

	public int getWidthCode() {
		return widthCode;
	}

	public void setWidthCode(int widthCode) {
		if(widthCode >= 1 && widthCode <=3){
			this.widthCode = widthCode;
		} else {
			throw new NumberFormatException("Number is not in range!");
		}
	}

	public int getPercent1() {
		return percent1;
	}

	public void setPercent1(int percent1) {
		this.percent1 = Math.abs(percent1);
		if( this.percent1 > 100 ){
			this.percent1 = 100;
		}
	}

	public int getPercent2() {
		return percent2;
	}

	public void setPercent2(int percent2) {
		this.percent2 = Math.abs(percent2);
		if( this.percent2 > 100 ){
			this.percent2 = 100;
		}
	}

	public GeneralElement(double length, double width, boolean rotate,
			int lengthCode, int widthCode, int percent1, int percent2) {
		super(length, width, rotate);
		setLengthCode(lengthCode);
		setWidthCode(widthCode);
		setPercent1(percent1);
		setPercent2(percent2);
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
