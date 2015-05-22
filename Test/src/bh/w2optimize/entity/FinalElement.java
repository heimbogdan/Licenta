package bh.w2optimize.entity;

import java.util.ArrayList;

/**
 * Class that extends the class {@link Element}. Final Element object is the
 * final arrangement.
 * 
 * 
 * @author bogdan.heim
 * @version 1.0
 * @since 22.04.2015
 */
public class FinalElement extends Element {

	/**
	 * Total surface of initial elements over which cuts will be made.
	 */
	private double area;

	/**
	 * Surface offcuts that can be reused.
	 */
	private double useableArea;

	private int useablePices;
	/**
	 * Surface offcuts which is seen as a loss.
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
		return useableArea;
	}

	public void setUsebleArea(final double usebleArea) {
		this.useableArea = usebleArea;
	}

	public ArrayList<Double> getIndividualLoss() {
		return individualLoss;
	}

	public void setIndividualLoss(final ArrayList<Double> individualLoss) {
		this.individualLoss = individualLoss;
	}

	
	public double getUseableArea() {
		return useableArea;
	}

	public int getUseablePices() {
		return useablePices;
	}

	public FinalElement(final double length, final double width) {
		super(length, width, false);
		this.area = 0;
		this.lostArea = 0;
		this.useableArea = 0;
		this.useablePices = 0;
		this.individualLoss = new ArrayList<Double>();
	}

	/**
	 * Function that calculates the total area of the page pale tiles.
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
	 * Function that calculates the total area of the page pale tiles that can
	 * be reused.
	 */
	public void calculateUsebleArea(final Element root) {
		if (!root.getChildrens().isEmpty()) {
			for (final Element elementl : root.getChildrens()) {
				calculateUsebleArea(elementl);
			}
		} else {
			if (!root.isUsed() && root.getLength() > 30 && root.getWidth() > 30) {
				this.useableArea += root.area();
				this.useablePices++;
			}
		}

	}

	/**
	 * Function that calculates the total area of the page pale tiles framed as
	 * a loss.
	 */
	public void calculateLostArea() {
		double totalArea = 0;
		for (final Element root : this.getChildrens()) {
			calculateUsebleArea(root);
			totalArea += root.area();
		}
		this.lostArea = totalArea - this.area - this.useableArea;
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
