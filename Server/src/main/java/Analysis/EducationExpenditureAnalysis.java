package Analysis;

import java.util.HashMap;
import org.json.simple.parser.ParseException;
import DataHandler.AnalysisBDO;
import DataHandler.AnalysisResult;
import DataHandler.LinkAdapter;
import DataHandler.WBAdapter;

public class EducationExpenditureAnalysis extends Analysis {

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
	public EducationExpenditureAnalysis(AnalysisBDO analysisBDO) {
		super(analysisBDO);
	}

	/*
	 * @return AnalysisResult type
	 * 
	 * @throws ParseException
	 */
	@Override
	public AnalysisResult analysisData() throws ParseException {
		/*
		 * 1 series Government expenditure on education, total (% of GDP)
		 * SE.XPD.TOTL.GD.ZS
		 */

		/*
		 * Data fetching Link Adapter is used to fetch data from the respective bank
		 */
		LinkAdapter newLink = new WBAdapter();
		HashMap<String, HashMap<Integer, Double>> theData = new HashMap<String, HashMap<Integer, Double>>();
		HashMap<Integer, Double> analysisData = newLink.analysisParameter(country, "SE.XPD.TOTL.GD.ZS", fromDate,
				toDate);
		String analysis = "Government expenditure on education, total (% of GDP)";
		// analysis and yearPerValue is the hashmap.
		theData.put(analysis, analysisData);

		this.analysisResult.setTheData(theData);

		return this.analysisResult;
	}
}
