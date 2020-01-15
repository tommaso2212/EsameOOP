package EsameOOP;

import java.io.File;
import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class, each method maps a request.
 * @author Tommaso2212
 *
 */
@Controller
public class AppController {
	
	/**
	 * Instance of Data.
	 */
	private Data data = null;
	
	/**
	 * Downloads dataset from url.
	 * @return
	 */
	@RequestMapping("/download")
	@ResponseBody
	public String getDataset() {
		this.data = new DatasetParser().getdataset("https://data.europa.eu/euodp/data/api/3/action/package_show?id=esener-1", "TSV");	//Find and then parse dataset 
		if(data == null) {	//if there is error
			return "[{\"error\" : \"Error download file. Try to download it again.\"}]";	//print error
		}
		return "[{\"result\" : \"Download executed.\"}]";	//print user response
	}
	
	/**
	 * Prints all dataset.
	 * @return 
	 */
	@RequestMapping("/printAll")
	@ResponseBody
	public String printAll() {
		if(this.data == null) {	//if this is the first time
			if(new File("dataset.tab").exists()) {	//checks if dataset was already downloaded
				DatasetOperation datasetOperation = new DatasetOperation("dataset.tab");	
				this.data = datasetOperation.parseFile();	//parse file and create data object
				return new PrintAll(this.data).printData(null);	//print all data on the page
			}
			return "[{\"error\" : \"Dataset not found. Try to download it first. (/download)\"}]";	//print error 
		}
		return new PrintAll(this.data).printData(null);	//print all data on the page
	}
	
	/**
	 * Prints dataset's metadata.
	 * @param List of attributes (optional)
	 * @return
	 */
	@RequestMapping("/printMetadata")
	@ResponseBody
	public String printMetadata(@RequestParam(required = false) List<String> attribute) {
		if(this.data == null) {	//if this is the first time
			if(new File("dataset.tab").exists()) {	//checks if dataset was already downloaded
				DatasetOperation datasetOperation = new DatasetOperation("dataset.tab");
				this.data = datasetOperation.parseFile();	//parse file and create data object
				return new PrintMetadata(this.data).printData(attribute);	//print metadata on the page
			}
			return "[{\"error\" : \"Dataset not found. Try to download it first. (/download)\"}]";	//print error
		}
		return new PrintMetadata(this.data).printData(attribute);	//print metadata on the page
	}
	
	/**
	 * Prints stats based on dataset.
	 * @param attribute
	 * @return
	 */
	@RequestMapping("/printStats")
	@ResponseBody
	public String printStats(@RequestParam(required = false) List<String> attribute) {
		if(this.data == null) {	//if this is the first time
			if(new File("dataset.tab").exists()) {	//checks if dataset was already downloaded
				DatasetOperation datasetOperation = new DatasetOperation("dataset.tab");
				this.data = datasetOperation.parseFile();	//parse file and create data object
				return new PrintStats(this.data).printData(attribute);	//print stats on the page
			}
			return "[{\"error\" : \"Dataset not found. Try to download it first. (/download)\"}]";	//print error
		}
		return new PrintStats(this.data).printData(attribute);	//print stats on the page
	}
}
