package Analysis;

import java.util.HashMap;
import org.json.simple.parser.ParseException;
import DataHandler.AnalysisBDO;
import DataHandler.AnalysisResult;
import DataHandler.LinkAdapter;
import DataHandler.WBAdapter;

public class GDPvsEducationExpenditurevsHealthExpenditure extends Analysis {

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
	public GDPvsEducationExpenditurevsHealthExpenditure(AnalysisBDO analysisBDO) {
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
		 * 3 series GDP per 1000 capita (current US$) NY.GDP.PCAP.CD Government
		 * expenditure on education, total (% of GDP) SE.XPD.TOTL.GD.ZS Current health
		 * expenditure (% of GDP) SH.XPD.CHEX.GD.ZS
		 */

		/*
		 * Data fetching Link Adapter is used to fetch data from the respective bank
		 */
		LinkAdapter newLink = new WBAdapter();
		HashMap<String, HashMap<Integer, Double>> theData = new HashMap<String, HashMap<Integer, Double>>();

		HashMap<Integer, Double> gdpData = newLink.analysisParameter(country, "NY.GDP.PCAP.CD", fromDate, toDate);
		// GDP per 1000 capita so divide each data by 100 to show everything properly on
		// graph
		int start = Integer.parseInt(fromDate);
		int end = Integer.parseInt(toDate);
		for (int year = start; year <= end; year++) {
			double refector = gdpData.get(year);
			// Refectoring the gdp data by 1000
			gdpData.put(year, refector / 1000);
		}

		HashMap<Integer, Double> educationData = newLink.analysisParameter(country, "SE.XPD.TOTL.GD.ZS", fromDate,
				toDate);
		HashMap<Integer, Double> healthData = newLink.analysisParameter(country, "SH.XPD.CHEX.GD.ZS", fromDate, toDate);

		// 1st analysis data
		String analysisOne = "GDP per 1000 capita";
		// analysis and yearPerValue is the hashmap.
		theData.put(analysisOne, gdpData);

		// 2st analysis data
		String analysisTwo = "Education Expenditure";
		// analysis and yearPerValue is the hashmap.
		theData.put(analysisTwo, educationData);

		// 3st analysis data
		String analysisThree = "Health Expenditure";
		// analysis and yearPerValue is the hashmap.
		theData.put(analysisThree, healthData);

		this.analysisResult.setTheData(theData);

		return this.analysisResult;
	}
}
