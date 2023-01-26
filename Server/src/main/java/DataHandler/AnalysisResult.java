package DataHandler;

import java.util.HashMap;

/*
 * (BDO) This object will hold the data of the analysis and be used in the graph 
 */
public class AnalysisResult {

	private HashMap<String, HashMap<Integer, Double>> theData;

	public HashMap<String, HashMap<Integer, Double>> getTheData() {
		return theData;
	}

	public void setTheData(HashMap<String, HashMap<Integer, Double>> theData) {
		this.theData = theData;
	}

}
