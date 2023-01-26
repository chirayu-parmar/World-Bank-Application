package Analysis;

import java.util.HashMap;

import org.json.simple.parser.ParseException;
import DataHandler.AnalysisBDO;
import DataHandler.AnalysisResult;
import DataHandler.LinkAdapter;
import DataHandler.OpenCovidAdapter;

/*
 * This class performs the covidDeaths analysis
 */
public class CovidDeaths extends Analysis {

	/*
	 * @param Country type String - Country value on which the analysis will be
	 * performed .
	 * 
	 * @param fromDate type String - Start date of the analysis
	 * 
	 * @param toDate type String - end date of the analysis
	 * 
	 * @param bank type String - Bank from which data will be indicated
	 */

	public CovidDeaths(AnalysisBDO analysisBDO) {
		super(analysisBDO);
		// TODO Auto-generated constructor stub
	}

	/*
	 * @return AnalysisResult type
	 * 
	 * @throws ParseException
	 */
	public AnalysisResult analysisData() throws ParseException {
		// linked will call the factory return type will be based on the bank name

		/*
		 * Data fetching Link Adapter is used to fetch data from the respective bank
		 */
		LinkAdapter newLink = new OpenCovidAdapter();
		HashMap<String, HashMap<Integer, Double>> theData = new HashMap<String, HashMap<Integer, Double>>();
		HashMap<Integer, Double> analysisData = newLink.analysisParameter("CAN", "deaths", fromDate, toDate);

		String analysis = "Covid Deaths Per Year in Canada";
		// analysis and yearPerValue is the hashmap.
		theData.put(analysis, analysisData);

		this.analysisResult.setTheData(theData);

		return this.analysisResult;
	}
}
