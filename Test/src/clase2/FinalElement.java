package clase2;

import java.util.ArrayList;

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

	private ArrayList<Double> individualLoss;

	public double getArea() {
		return area;
	}

	public void setArea(final double area) {
		this.area = area;
	}

	public double getLostArea() {
		return lostArea;
	}

	public void setLostArea(final double lostArea) {
		this.lostArea = lostArea;
	}

	public double getUsebleArea() {
		return usebleArea;
	}

	public void setUsebleArea(final double usebleArea) {
		this.usebleArea = usebleArea;
	}

	public ArrayList<Double> getIndividualLoss() {
		return individualLoss;
	}

	public void setIndividualLoss(final ArrayList<Double> individualLoss) {
		this.individualLoss = individualLoss;
	}

	public FinalElement(final double length, final double width) {
		super(length, width);
		this.area = 0;
		this.lostArea = 0;
		this.usebleArea = 0;
		this.individualLoss = new ArrayList<Double>();
	}

	/**
	 * Functie ce calculeaza suprafata totala a placiilor de pal initiala.
	 */
	public void calculateArea() {
		double area = 0;
		if (!this.getChildrens().isEmpty()) {
			for (final Element elementl : this.getChildrens()) {
				final FinalElement finalElementl = FinalElement
						.deepCopy(elementl);
				finalElementl.calculateArea();
				area += finalElementl.getArea();
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
	public void calculateUsebleArea(final Element root) {
		if (!root.getChildrens().isEmpty()) {
			for (final Element elementl : root.getChildrens()) {
				calculateUsebleArea(elementl);
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
		for (final Element root : this.getChildrens()) {
			calculateUsebleArea(root);
			totalArea += root.area();
		}
		this.lostArea = totalArea - this.area - this.usebleArea;
	}

	public void calculateIndividualLoss() {
		for (final Element elementl : this.getChildrens()) {
			final FinalElement fel = FinalElement.deepCopy(elementl);
			fel.calculateArea();
			fel.calculateLostArea();
			this.individualLoss.add(fel.getLostArea());
		}
	}

	public static FinalElement deepCopy(final Element element) {
		final FinalElement finalElementl = new FinalElement(
				element.getLength(), element.getWidth());
		finalElementl.setChildrens(element.getChildrens());
		finalElementl.setPosition(element.getPosition());
		finalElementl.setParent(element.getParent());
		finalElementl.setPoint(element.getPoint());
		finalElementl.setUsed(element.isUsed());
		return finalElementl;
	}
}
