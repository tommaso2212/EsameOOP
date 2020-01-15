package EsameOOP;

import java.util.*;
import com.google.gson.*;

/**
 * Extends Print class. Defines the method that are used to print stats on the values.
 * @author Tommaso2212
 *
 */
public class PrintStats extends Print{
	
	/**
	 * Constructor.
	 * @param data
	 */
	public PrintStats(Data data){
		super(data);
	}
	
	/**
	 * Override parent method, returns stats of given attributes.
	 */
	@Override
	protected String getDataToPrint(List<String> attribute) {
		JsonArray jsonArray = new JsonArray();	//Json array used to store the stats to print
		if(attribute==null) {	//if attribute list is empty
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("error", "Attribute required. Please pass one or more attributes with /printStats?attribute=id");	//set error
			jsonArray.add(jsonObject);	//return error 
		}
		else {
			List<Stats> stats = new ArrayList<>();	//create a list to store stats, each object contains the stats of one attribute
			for(int i=0; i<attribute.size(); i++) {
				stats.add(new Stats(super.data,  attribute.get(i)));
				if (stats.get(i).calcStats()) {	//calculate stats and return false if the attribute doesn't exist
					jsonArray.add(stats.get(i).print());	//add stats to the list of the stats to print
				}
				else {
					JsonObject jsonObject = new JsonObject();
					jsonObject.addProperty(attribute.get(i), "Error: Invalid attribute");	//set error
					jsonArray.add(jsonObject);	//return error
				}
			}
		}
		return jsonArray.toString();	//return printable stats
	}
}
