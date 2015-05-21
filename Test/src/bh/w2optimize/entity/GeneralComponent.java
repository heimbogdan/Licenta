package bh.w2optimize.entity;

public class GeneralComponent extends Component {

	private double length;
	private double width;
	private double height;

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

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public GeneralComponent(final String name, final String code, final double length,
			final double width, final double height, final ElementList elements) {
		super(name, code, elements);
		this.length = length;
		this.width = width;
		this.height = height;
	}

	public GeneralComponent() {
		super();
	}

	@Override
	public String toString() {
		return "GeneralComponent [ " + super.toString() + " length=" + length + ", width=" + width
				+ ", height=" + height + "]";
	}
 
	
}
