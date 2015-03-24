package clase2;

/**
 * Clasa ce extinde clasa {@link Element}. Obiectul de tip FinalElement este
 * incadrarea finala.
 * 
 * @author bogdan.heim
 *
 */
public class FinalElement extends Element {

	/**
	 * Suprafata totala a placilor initiale asupra carora se vor efectua
	 * taierile.
	 */
	private double area;

	/**
	 * Suprafata rezultata din taieri care pot fi reutilizate.
	 */
	private double usebleArea;

	/**
	 * Suprafata rezultata din taieri care este incadrata ca pierdere.
	 */
	private double lostArea;

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
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
		this.area = 0;
		this.lostArea = 0;
		this.usebleArea = 0;
	}

	/**
	 * Functie ce calculeaza suprafata totala a placiilor de pal initiala.
	 */
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
		this.area += area;
	}

	/**
	 * Functie ce calculeaza suprafata totala a placiilor de pal initiala care
	 * poate fi refolosita.
	 */
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

	/**
	 * Functie ce calculeaza suprafata totala a placiilor de pal initiala
	 * incadrata ca pierdere.
	 */
	public void calculateLostArea() {
		double totalArea = 0;
		for (Element root : this.getChildrens()) {
			calculateUsebleArea(root);
			totalArea += root.area();
		}
		this.lostArea = totalArea - this.area - this.usebleArea;
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
