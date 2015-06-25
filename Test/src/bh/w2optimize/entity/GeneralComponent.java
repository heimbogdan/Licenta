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

	public GeneralComponent(final String name, final String code,
			final double length, final double width, final double height,
			final ElementList elements) {
		super(name, code, elements);
		this.length = length;
		this.width = width;
		this.height = height;
	}

	public GeneralComponent() {
		super();
	}

	public void rescaleElements(final double length, final double width,
			final double height) {
		final double _length = length - this.length;
		final double _width = width - this.width;
		final double _height = height - this.height;

		for (Element elem : getElements()) {
			GeneralElement el = (GeneralElement) elem;
			double newLength;
			double newWidth;
			double percent = el.getLengthPercent();
			switch (el.getLengthCode()) {
			case 1:
				newLength = el.getLength() + (_length * (percent / 100));
				break;
			case 2:
				newLength = el.getLength() + (_width * (percent / 100));
				break;
			case 3:
				newLength = el.getLength() + (_height * (percent / 100));
				break;
			default:
				newLength = el.getLength();
				break;
			}
			percent = el.getWidthPercent();
			switch (el.getWidthCode()) {
			case 1:
				newWidth = el.getWidth() + (_length * (percent / 100));
				break;
			case 2:
				newWidth = el.getWidth() + (_width * (percent / 100));
				break;
			case 3:
				newWidth = el.getWidth() + (_height * (percent / 100));
				break;
			default:
				newWidth = el.getWidth();
				break;
			}
			el.setLength(newLength);
			el.setWidth(newWidth);
		}
		this.length = length;
		this.width = width;
		this.height = height;
	}

	@Override
	public String toString() {
		return "GeneralComponent [ " + super.toString() + " length=" + length
				+ ", width=" + width + ", height=" + height + "]";
	}

}
