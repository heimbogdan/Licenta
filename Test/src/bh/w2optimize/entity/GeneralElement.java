package bh.w2optimize.entity;

public class GeneralElement extends Element {

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
	
	public Element toElement(){
		return new Element(getLength(), getWidth(), isRotate());
	}

}
