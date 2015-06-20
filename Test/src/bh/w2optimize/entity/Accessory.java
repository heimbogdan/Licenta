package bh.w2optimize.entity;

public class Accessory {

	private int id;
	private String code;
	private String name;
	private double price;
	private int number;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNumber(){
		return this.number;
	}
	
	public void setNumber(int number){
		this.number = number;
	}
	
	public Accessory(String code, String name, double price, int number) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.number = number;
	}

	public Accessory(){
		
	}
	
	@Override
	public String toString() {
		return "Accessory [id=" + id + ", code=" + code + ", name=" + name
				+ ", price=" + price + ", number=" + number + "]";
	}

	
}
