package EsameOOP;

import java.io.*;
import java.util.*;

/**
 * Defines the operations to create Data object starting from dataset file
 * @author Tommaso2212
 *
 */
public class DatasetOperation {
	
	/**
	 * Instance of Data
	 */
	private Data data = null;
	
	/**
	 * Name of dataset file
	 */
	private String fileName = null;
	
	/**
	 * Constructor
	 * @param Name of dataset file
	 */
	public DatasetOperation(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Parse dataset file and return a Data object filled with dataset values.
	 * @return Data instance
	 */
	public Data parseFile() {
		this.data = new Data(this.fileName);	//instance data object
		try {
			Scanner file = new Scanner(new File(fileName));	//Open input stream
			data.setMetadata(parseMetadata(file.nextLine()));	//fill list of metadata
			while (file.hasNext()) {	//get all line
				data.getData().add(parseData(file.nextLine()));	//fill list of data
			}
			file.close();	//close input stream
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return data;	//return data
	}
	
	/**
	 * Parse the first line of the dataset to find the metadata
	 * @param line
	 * @return
	 */
	private List<Metadata> parseMetadata(String line){
		List<Metadata> fields = new ArrayList<>();	//create list of fields
		String[] str = line.split("\t");	//split line by tab
		for(int i=0; i<str.length; i++) {
			Metadata meta = new Metadata();	//instance metadata object
			meta.setField(str[i]);	//set name of field
			meta.setType("double");	//set defult type of attribute
			fields.add(meta);	//add metadata to list
		}
		return fields;	//return filled list of metadata
	}
	
	/**
	 * Parse row of the dataset and store in a list of value.
	 * @param Row of dataset
	 * @return List of value
	 */
	private List<Double> parseData(String line){
		List<Double> fields = new ArrayList<>();	//create list of value
		String[] str = line.split("\t");	//split line by tab
		for(int i=0; i<str.length; i++) {
			if (str[i].equals(" ")) {
				fields.add(null);	//if it is an empty field, save as null value
			}
			else {
				double num = Double.parseDouble(str[i]);	//cast value from string to double
				if ((num/(int) num) == 1) {	//if the value is an integer
					this.data.getMetadata().get(i).setType("int");	//overwrite metadata 
				}
				fields.add(num);	//add value to list
			}
		}
		return fields;	//return list of value
	}
}
