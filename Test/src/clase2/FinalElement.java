package clase2;

public class FinalElement extends Element {

	private double Area;

	public double getArea() {
		return Area;
	}

	public void setArea(double Area) {
		this.Area = Area;
	}

	public FinalElement(double length, double width) {
		super(length, width);
		this.Area = 0;
	}

	public double calculateArea() {
		double area = 0;
		if (!this.getChildrens().isEmpty()) {
			for (Element el : this.getChildrens()) {
				FinalElement fel = FinalElement.deepCopy(el);
				fel.setArea(fel.calculateArea());
				area += fel.getArea();
			}
		} else if (this.isUsed()) {
			area = this.getLength() * this.getWidth();
		}
		return area;
	}

	public static FinalElement deepCopy(Element element) {
		FinalElement fel = new FinalElement(element.getLength(),
				element.getWidth());
		fel.setChildrens(element.getChildrens());
		fel.setPosition(element.getPosition());
		fel.setParent(element.getParent());
		fel.setPoint(element.getPoint());
		fel.setUsed(element.isUsed());
		return fel;
	}
}
