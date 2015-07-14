package bh.w2optimize.entity;

import java.util.Comparator;

public class WoodBoardPice extends WoodBoard{

	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public WoodBoardPice(){
		super();
	}
	
	public WoodBoardPice(String code, String name, String material,
			double length, double width, double price, int number) {
		super(code, name, material, length, width, price);
		this.number = number;
		recalculatePrice();
	}
	
	private void recalculatePrice(){
		//TODO de facut motoda de recalculat
	}
	
	private double area(){
		return getLength() * getWidth();
	}
	
	public static Comparator<WoodBoardPice> comparator = new Comparator<WoodBoardPice>() {

		@Override
		public int compare(WoodBoardPice o1, WoodBoardPice o2) {
			int val;
			if (o1.area() == o2.area()) {
				final double length = o1.getLength() - o2.getLength();
				final double width = o1.getWidth() - o2.getWidth();
				final double length1 = length < 0 ? length * (-1) : length;
				final double width1 = width < 0 ? width * (-1) : width;
				if (length1 > width1) {
					val = length < 0 ? -1 : 1;
				} else if (width1 > length1) {
					val = width < 0 ? -1 : 1;
				} else {
					val = 0;
				}
			} else if (o1.area() > o2.area()) {
				val = 1;
			} else {
				val = -1;
			}
			return val;
		}
	};
}
