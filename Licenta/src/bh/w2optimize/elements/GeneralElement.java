package bh.w2optimize.elements;

import java.io.Serializable;

public class GeneralElement extends Element implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5585714780386931738L;
	
	private int lengthCode;
	private int widthCode;
	private int lengthPercent;
	private int widthPercent;

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

	public int getLengthPercent() {
		return lengthPercent;
	}

	public void setLengthPercent(int percent) {
		this.lengthPercent = Math.abs(percent);
		if( this.lengthPercent > 100 ){
			this.lengthPercent = 100;
		}
	}

	public int getWidthPercent() {
		return widthPercent;
	}

	public void setWidthPercent(int percent) {
		this.widthPercent = Math.abs(percent);
		if( this.widthPercent > 100 ){
			this.widthPercent = 100;
		}
	}

	public GeneralElement(double length, double width, boolean rotate,
			int lengthCode, int widthCode, int percent1, int percent2) {
		super(length, width, rotate);
		setLengthCode(lengthCode);
		setWidthCode(widthCode);
		setLengthPercent(percent1);
		setWidthPercent(percent2);
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
				+ widthCode + ", percent1=" + lengthPercent + ", percent2="
				+ widthPercent + "]";
	}

	
}
