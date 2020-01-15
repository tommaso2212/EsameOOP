package EsameOOP;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;
import java.util.*;
import com.google.gson.*;


/**
 * This class contains method to get dataset from an http request.
 * @author Tommaso2212
 *
 */
public class DatasetParser {
	
	private Data data = null;
	
	private String datasetUrl = null;
	
	private String datasetExtension = null;
	
	/**
	 * Finds dataset url and extension of it searching the right resource in a json, obtained from an http request.
	 * @param Url of http request
	 * @param Extension of dataset to find
	 */
	private void findUrl(String url, String extension) {
		try {
			InputStream inputStream = new URL(url).openStream();	//Open InputStream from the url
			Scanner scanner = new Scanner(inputStream);	
			String str = scanner.nextLine();	//Get response from url
			
			JsonObject json = (JsonObject) JsonParser.parseString(str);	//Parse String response to Json object 
			json = json.getAsJsonObject("result");	//Get result object
			JsonArray arr = json.getAsJsonArray("resources");	//Get array of resources
			
			for(int i=0; i<arr.size(); i++) {
				json = (JsonObject) arr.get(i);	//Get resource
				String[] format = json.get("format").getAsString().split("/");	//Get format resource and split it
				if(format[format.length-1].equals(extension)) {	//check format resource
					this.datasetUrl = json.get("url").getAsString();	//Get url of resource
				}
			}
			
			//Close 
			scanner.close();
			inputStream.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		if(datasetUrl != null) {
			String[] https = this.datasetUrl.split(":");	//replace "http" with "https"
			if(https[0].equals("http")) {
				https[0] = https[0] + "s:";	//add s to "http" string
				StringBuffer stringBuffer = new StringBuffer();
				for(int i=0; i<https.length; i++) {
					stringBuffer.append(https[i]);	//convert array of string into string
				}
				this.datasetUrl = stringBuffer.toString();	//update url
			}
			String[] format = this.datasetUrl.split("\\.");	//find extension of dataset
			datasetExtension = "." + format[format.length-1];	//save extension of dataset file
		}
	}
	
	/**
	 * Downloads dataset from the url.
	 */
	private void downloadDataset() {
		try {
			InputStream in = new URL(this.datasetUrl).openStream();	//open input stream
			Files.copy(in, Paths.get("dataset"+this.datasetExtension), StandardCopyOption.REPLACE_EXISTING);	//save dataset file or replace it if already exists.
			DatasetOperation datasetOperation = new DatasetOperation("dataset"+this.datasetExtension);	
			this.data = datasetOperation.parseFile();	//parse file and create data object
			in.close();	//close input stream
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns a data object.
	 * @param Url of http request
	 * @param Extension of dataset to find
	 * @return
	 */
	public Data getdataset(String url, String extension) {
		this.findUrl(url, extension);
		this.downloadDataset();
		return this.data;
	}
}
