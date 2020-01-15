# EsameOOP

### Run
Compile and run the project with:
<pre><code>./gradlew bootRun </code></pre>

### Stats
The stats are implemented using a support list of values.
First we build this list taking, from the column, only the values with a value discarding the empty ones.
Thanks to the Java List primitive methods, we can easily sort the list and then take the minimum and the maximum as the first and the last element of the list. Moreover we can find the count of numbers as the size of the list.
The sum is calculated using an ausiliar variable that are updated at each element saved in the support list and the average is calculated dividig the sum by the count of numbers at the end of the cycle.
The standard deviation is calculated implementing the formula described at https://en.wikipedia.org/wiki/Standard_deviation.

### Request
* Download the dataset - http://localhost:8080/download
* Print all data - http://localhost:8080/printAll
* Print metadata 
  * Print all metadata - http://localhost:8080/printMetadata
  * Print metadata for given attribute - http://localhost:8080/printMetadata?attribute=name
  * Print metadata for more attributes - http://localhost:8080/printMetadata?attribute=name1&attribute=name2&...
* Print stats
  * Print stats for given attribute - http://localhost:8080/printStats?attribute=name
  * Print stats for more attributes - http://localhost:8080/printStats?attribute=name1&attribute=name2&...
  
### Test example

  
