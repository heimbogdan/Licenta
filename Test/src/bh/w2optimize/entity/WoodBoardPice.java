package bh.w2optimize.entity;

public class WoodBoardPice extends WoodBoard{

	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public WoodBoardPice(String code, String name, String material,
			double length, double width, double price, int number) {
		super(code, name, material, length, width, price);
		this.number = number;
		recalculatePrice();
	}
	
	private void recalculatePrice(){
		
	}
}
