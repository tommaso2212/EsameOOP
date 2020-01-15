package EsameOOP;

import java.util.*;
import com.google.gson.JsonObject;

/**
 * Defines methods to calculate stats.
 * @author Tommaso2212
 *
 */
public class Statistiche {
	
	private Data data;	//instance of data
	private String attribute;	//name of attribute
	private Double sum = null;	//total sum
	private Double max = null;	//maximum
	private Double min = null;	//minimum
	private Double std = null;	//standard deviation
	private Double avg = null;	//average
	private int count = 0;	//count of numbers
	private boolean exist = false;	//true if the ettribute exists
	
	/**
	 * Constructor.
	 * @param data
	 * @param attribute
	 */
	public Statistiche(Data data, String attribute) {
		this.data = data;
		this.attribute = attribute;
	}
	
	/**
	 * Calculates stats and return true if the attribute exists.
	 * @return 
	 */
	public boolean calcStats(){
		
		List<Double> list = new ArrayList<>();	//Create list to store a column
		
		double sum = 0.0;	//declare sum of value and set it as 0
		int column = -1;	//declare index of attribute's column as -1 (out of index range)
		for(int i = 0; i<data.getMetadata().size(); i++) {
			if(data.getMetadata().get(i).getField().equals(attribute)) {	//if attribute is found
				column = i;	//save index
				break;
			}
		}
		if(column == -1) {
			return false;	//if the attribute doesn't exist, return false
		}
		
		for(int i=0; i<data.getData().size(); i++) {
			if (data.getData().get(i).get(column) != null) {
				sum += data.getData().get(i).get(column);	//update total sum
				list.add(data.getData().get(i).get(column));	//add value to list
			}
		}
		Collections.sort(list);	//sort the list
		
		if (!list.isEmpty()) {	//if the list isn't empty
			this.count = list.size();	//the count of numbers is the lenght of the column
			this.sum = sum;	//total sum
			this.min = list.get(0);	//the minimum value is the first element of the sorted list
			this.max = list.get(list.size()-1);	//the maximum value is the last element of the sorted list
			this.avg = this.sum/this.count;	//the average is the total sum divided by the count of numbers
			this.std = standardDeviation(list);	//calculate the standard deviation
		}
		this.exist = true;	//set exist flag as true
		return true;
	}
	
	/**
	 * Calculates the standard deviation.
	 * @param Column
	 * @return 
	 */
	public double standardDeviation(List<Double> column) {
		double dev = 0;	//initial value of square deviation 
		for(int i=0; i<column.size(); i++) {
			dev += Math.pow(column.get(i)-this.avg, 2);	//calc square deviation for each element of the column
		}
		return Math.sqrt(dev/this.count);	//return medium square deviation
	}
	
	/**
	 * Returns stats values as JsonObject
	 * @return
	 */
	public JsonObject print() {
		JsonObject jsonObject = new JsonObject();
		if(exist) {
			jsonObject.addProperty("attribute", this.attribute);	//set attribute name
			jsonObject.addProperty("avg", this.avg);	//set average value
			jsonObject.addProperty("min", this.min);	//set minimum value
			jsonObject.addProperty("max", this.max);	//set maximum value
			jsonObject.addProperty("std", this.std);	//set standard deviation value
			jsonObject.addProperty("sum", this.sum);	//set sum value
			jsonObject.addProperty("count", this.count);	//set count value
		}
		else {
			jsonObject.addProperty("attribute", "Error: Invalid attribute");	//set error
		}
		return jsonObject;	//return values
	}
}
