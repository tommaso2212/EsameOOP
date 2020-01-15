package EsameOOP;

/**
 * Contains metadata's attributes.
 * @author Tommaso2212
 *
 */
public class Metadata {
	
	private String field;	//name of column
	private String type;	//type of attribute
	
	/**
	 * Setter method for field attribute.
	 * @param field
	 */
	public void setField(String field) {
		this.field = field;
	}
	
	/**
	 * Getter method for field attribute.
	 * @return
	 */
	public String getField() {
		return field;
	}
	
	/**
	 * Setter method for type attribute.
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Getter method for type attribute.
	 * @return
	 */
	public String getType() {
		return type;
	}
}
