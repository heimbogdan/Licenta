package bh.w2optimize.language;

public enum LanguageEN {

	//A
	ABOUT("About"),
	ADD("Add"),
	//B
	BEST_SOLUTION("Best Solution"),
	BROWSE("Browse"),
	//C
	CANCEL("Cancel"),
	CHOOSE_COMP("Choose Component"),
	CODE("Code"),
	COMPONENT("Component"),
	COMPONENT_BROWSER("Component Browser"),
	COMPONENT_CREATOR("Component Creator"),
	//D
	DELETE("Delete"),
	//E
	EDIT("Edit"),
	EDIT_COMPONENTS("Edit Compoments"),
	EDIT_STOCKS("Edit Stocks"),
	EDIT_WOOD_BOARDS("Edit Wood Boards"),
	ELEMENT("Element"),
	ELEMENTS("Elements"),
	ELEMENTS_NO("Elements No."),
	EXIT("Exit"),
	//F
	FILE("File"),
	//G
	GEN_COMPONENT("General Component"),
	GET_PRICE("Get Price"),
	//H
	HEIGHT("Height"),
	HELP("Help"),
	//I
	//J
	//K
	//L
	LENGTH("Length"),
	LENGTH_CODE("L Code"),
	LENGTH_PERCENT("L Percent"),
	//M
	MATERIAL("Material"),
	//N
	NAME("Name"),
	NEW("New"),
	NEW_COMP("New Component"),
	NEXT("Next"),
	NO("No."),
	NONE("Nome"),
	NUMBER("Number"),
	//O
	OK("OK"),
	OPEN("Open"),
	//P
	PREFERENCES("Preferences"),
	PREV("Previous"),
	PRICE("Price"),
	//Q
	//R
	RESTRICTIONS("Restrictions"),
	ROTATE("Rotate"),
	//S
	SAVE("Save"),
	SELECT("Select"),
	SETTINGS("Settings"),
	START("Start"),
	STOCKS("Stocks"),
	STOP("Stop"),
	//T
	TIME("Time"),
	TRIES("Tries"),
	//U
	USER_MANUAL("User Manual"),
	//V
	VALUE("Value"),
	//W
	WIDTH("Width"),
	WIDTH_CODE("W Code"),
	WIDTH_PERCENT("W Percent"),
	WOOD_BOARD_NAME("Wood Board Name");
	//X
	//Y
	//Z
	
	
	private String desc;
	
	private LanguageEN(String desc){
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return this.desc;
	}
}
