package BarState;

import java.util.HashMap;

import org.jfree.chart.ChartPanel;

//Sate Design Pattern
/*
 * This interface is implemented by the different Bar states (BarOne, BarTwo,BarThree)
 */
public interface BarState {

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
	ChartPanel getGraph(HashMap<String, HashMap<Integer, Double>> analysisResults, String fromDate, String toDate,
			String analysis);
}
