package clase2;

public class FinalElement extends Element {

	private double Area;
	private double usebleArea;
	private double lostArea;

	public double getArea() {
		return Area;
	}

	public void setArea(double Area) {
		this.Area = Area;
	}

	public double getLostArea() {
		return lostArea;
	}

	public void setLostArea(double lostArea) {
		this.lostArea = lostArea;
	}

	public double getUsebleArea() {
		return usebleArea;
	}

	public void setUsebleArea(double usebleArea) {
		this.usebleArea = usebleArea;
	}

	public FinalElement(double length, double width) {
		super(length, width);
		this.Area = 0;
		this.lostArea = 0;
		this.usebleArea = 0;
	}

	public void calculateArea() {
		double area = 0;
		if (!this.getChildrens().isEmpty()) {
			for (Element el : this.getChildrens()) {
				FinalElement fel = FinalElement.deepCopy(el);
				fel.calculateArea();
				area += fel.getArea();
			}
		} else if (this.isUsed()) {
			area = this.getLength() * this.getWidth();
		}
		this.Area += area;
	}

	public void calculateUsebleArea(Element root) {
		if (!root.getChildrens().isEmpty()) {
			for (Element el : root.getChildrens()) {
				calculateUsebleArea(el);
			}
		} else {
			if (!root.isUsed() && !root.getParent().isLoss()) {
				this.usebleArea += root.area();
			}
		}

	}

	public void calculateLostArea() {
		double totalArea = 0;
		for (Element root : this.getChildrens()) {
			calculateUsebleArea(root);
			totalArea += root.area();
		}
		this.lostArea = totalArea - this.Area - this.usebleArea;
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
