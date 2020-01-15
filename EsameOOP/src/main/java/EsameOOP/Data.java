package EsameOOP;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Contains dataset value stored using lists of values.
 * @author Tommaso2212
 *
 */
public class Data {
	
	private String fileName = null;	//name of dataset file
	
	private List<Metadata> metadata = new ArrayList<>();	//list of metadata
	
	private List<List<Double>> data = new ArrayList<>();	//list of dataset row
	
	/**
	 * Constructor
	 * @param Name of dataset file.
	 */
	public Data(String fileName){
		this.fileName = fileName;
	}
	
	/**
	 * Get method for the name of dataset file.
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Set method for metadata's list.
	 * @return
	 */
	public void setMetadata(List<Metadata> metadata) {
		this.metadata = metadata;
	}
	
	/**
	 * Get method for data's list.
	 * @return
	 */
	public List<List<Double>> getData() {
		return data;
	}
	
	/**
	 * Get method for metadata's list.
	 * @return
	 */
	public List<Metadata> getMetadata() {
		return metadata;
	} 
}
