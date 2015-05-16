package bh.w2optimize.entity;

public class WoodBoard {

	private int id;
	private String code;
	private String name;
	private String material;
	private double length;
	private double width;
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

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public WoodBoard(int id, String code, String name, String material,
			double length, double width, double price) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.material = material;
		this.length = length;
		this.width = width;
		this.price = price;
	}

}
