package EsameOOP;

import java.util.*;
import com.google.gson.*;

/**
 * Extends Print class. Defines the method that are used to print all data.
 * @author Tommaso2212
 *
 */
public class PrintAll extends Print{

	/**
	 * Constructor.
	 * @param data
	 */
	public PrintAll(Data data) {
		super(data);
	}
	
	/**
	 * Overrides parent method, returns the entire dataset.
	 */
	@Override
	protected String getDataToPrint(List<String> attribute) {
		JsonArray jsonArray = new JsonArray();	//Json array used to store data to print
		for(int i=0; i<super.data.getData().size(); i++) {	
			List<Double> field = super.data.getData().get(i);	//get row
			JsonObject jsonObject = new JsonObject();
			for(int j=0; j<field.size(); j++) {	//for each column
				if (field.get(j) == null) {	//if the cell is empty
					jsonObject.addProperty(super.data.getMetadata().get(j).getField(), (Double) null);	//print value as null
				}
				else {
					if (super.data.getMetadata().get(j).getType() == "int") {	//if the type of value is an integer
						jsonObject.addProperty(super.data.getMetadata().get(j).getField(), field.get(j).intValue());	//print value as integer
					}
					else {
						jsonObject.addProperty(super.data.getMetadata().get(j).getField(), field.get(j));	//print value as double
					}
				}
			}
			jsonArray.add(jsonObject);	//add row to the array
		}
		return jsonArray.toString();	//return printable data
	}
	
}
