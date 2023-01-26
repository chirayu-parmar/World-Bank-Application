package LineState;

import java.util.HashMap;

import org.jfree.chart.ChartPanel;

//Sate Design Pattern
/*
* This interface is implemented by the different Line states (LineOne, LineTwo, LineThree)
*/
public interface LineState {
	/*
	 * @param analysisResults type HashMap<String, HashMap<Integer, Double>> -
	 * Stores the name of the analysis as key then another nested hashmap with dates
	 * and data values
	 * 
	 * @param fromDate type String - Start date of the analysis
	 * 
	 * @param toDate type String - end date of the analysis
	 * 
	 * @param analysis type String - name of the analysis
	 * 
	 * @return ChartPanel
	 */
	public ChartPanel getGraph(HashMap<String, HashMap<Integer, Double>> analysisResults, String fromDate,
			String toDate, String analysis);
}
