package bh.w2optimize.entity;

/**
 * Used to group multiple elements ({@link Element}) that together make a
 * furniture component
 * 
 * @author bogdan.heim
 * @version 1.0
 * @since 22.04.2015
 */
public class Component {

	private int id;
	private String name;
	private String code;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * Used to get the name of the component
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Used to set the name of the component
	 * 
	 * @param name
	 *            - The new name of the component
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Used to get the code of the component
	 * 
	 * @return String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Used to set the code of the component
	 * 
	 * @param name
	 *            - The new code of the component
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * Makes a new component
	 * @param name - The name of the component
	 * @param code - The code of the component
	 */
	public Component(final String name,final String code) {
		super();
		this.name = name;
		this.code = code;
	}

	public Component(){
		super();
	}

	@Override
	public String toString() {
		return "Component [id=" + id + ", name=" + name + ", code=" + code
				+ "]";
	}
	
}
