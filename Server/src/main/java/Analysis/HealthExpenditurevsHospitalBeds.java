package Analysis;

import java.util.*;
import org.json.simple.parser.ParseException;
import DataHandler.AnalysisBDO;
import DataHandler.AnalysisResult;
import DataHandler.LinkAdapter;
import DataHandler.WBAdapter;

public class HealthExpenditurevsHospitalBeds extends Analysis {

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
	public HealthExpenditurevsHospitalBeds(AnalysisBDO analysisBDO) {
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
		 * 1 series ratio Current health expenditure (per 1000 people) (current US$)
		 * SH.XPD.CHEX.PC.CD Hospital beds (per 1,000 people) SH.MED.BEDS.ZS
		 */

		/*
		 * Data fetching Link Adapter is used to fetch data from the respective bank
		 */
		LinkAdapter newLink = new WBAdapter();
		HashMap<String, HashMap<Integer, Double>> theData = new HashMap<String, HashMap<Integer, Double>>();
		HashMap<Integer, Double> healthExpData = newLink.analysisParameter(country, "SH.XPD.CHEX.PC.CD", fromDate,
				toDate);
		HashMap<Integer, Double> healthcareData = newLink.analysisParameter(country, "SH.MED.BEDS.ZS", fromDate,
				toDate);

		HashMap<Integer, Double> healthData = new HashMap<Integer, Double>();

		int start = Integer.parseInt(fromDate);
		int end = Integer.parseInt(toDate);
		for (int year = start; year <= end; year++) {

			double healthExpVal = healthExpData.get(year);
			double healthcareAccVal = healthcareData.get(year);
			double ratio;

			if (healthcareAccVal != 0)
				ratio = healthExpVal / healthcareAccVal;
			else
				ratio = 0.0;

			// adding ratio onto hashMap
			healthData.put(year, ratio);
		}

		// 1st analysis data
		String analysisOne = "Health expenditure (per 1000 people) vs Hospital beds (per 1,000 people)";
		// analysis and yearPerValue is the hashmap.
		theData.put(analysisOne, healthData);

		this.analysisResult.setTheData(theData);

		return this.analysisResult;

	}
}