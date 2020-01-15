package EsameOOP;

import java.util.List;

/**
 * Defines the method that are used to print the values.
 * @author Tommaso2212
 *
 */
public class Print {
	//Instance of Data
	protected Data data;
	
	/**
	 * Constructor.
	 * @param Data object
	 */
	public Print(Data data){
		this.data = data;
	}
	
	/**
	 * This method select data to print.
	 * @param List of attribute to print.
	 * @return
	 */
	protected String getDataToPrint(List<String> attribute) {
		return null;
	}
	
	/**
	 * Returns the data to print.
	 * @param attribute
	 * @return
	 */
	public String printData(List<String> attribute) {
		return getDataToPrint(attribute);
	}
}
