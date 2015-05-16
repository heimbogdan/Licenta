package bh.w2optimize.entity;

public class Accessory {

	private int id;
	private String code;
	private String name;
	private double price;

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

	public Accessory(String code, String name, double price) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Accessory [id=" + id + ", code=" + code + ", name=" + name
				+ ", price=" + price + "]";
	}

	
}
