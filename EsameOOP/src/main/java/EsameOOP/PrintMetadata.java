package EsameOOP;

import java.util.List;
import com.google.gson.*;

/**
 * Extends Print class. Defines the method that are used to print all data.
 * @author Tommaso2212
 *
 */
public class PrintMetadata extends Print{

	/**
	 * Constructor.
	 * @param data
	 */
	public PrintMetadata(Data data) {
		super(data);
	}
	
	/**
	 * Overrides parent method, returns metadata of the given attributes.
	 */
	@Override
	protected String getDataToPrint(List<String> attribute) {
		JsonArray jsonArray = new JsonArray();	//Json array used to store metadatas to print
		if(attribute == null) {
			return new Gson().toJson(super.data.getMetadata());	//if there is no attributes, return all metadatas
		}
		for(int i=0; i<attribute.size(); i++) {	//for each attribute
			JsonObject jsonObject = new JsonObject();
			int column = -1;
			for(int j = 0; j<super.data.getMetadata().size(); j++) {
				if(super.data.getMetadata().get(j).getField().equals(attribute.get(i))) {
					column = i;
					break;
				}
			}
			if(column == -1) {	//if the attribute doesn't exist
				jsonObject.addProperty(attribute.get(i), "Error: Invalid attribute");	//return error for the attribute
			}
			else {	//return metadata of the attribute
				jsonObject.addProperty("attribute", attribute.get(i));
				jsonObject.addProperty("field", super.data.getMetadata().get(i).getField());
				jsonObject.addProperty("type", super.data.getMetadata().get(i).getType());
			}
			jsonArray.add(jsonObject);	//add metadata/error to the array
		}
		return jsonArray.toString();	//return printable metadata
	}
}
