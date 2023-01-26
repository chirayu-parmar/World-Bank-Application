package Analysis;

import java.util.*;
import org.json.simple.parser.ParseException;
import DataHandler.AnalysisBDO;
import DataHandler.AnalysisResult;
import DataHandler.LinkAdapter;
import DataHandler.WBAdapter;

public class GDPCO2Analysis extends Analysis {

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
	public GDPCO2Analysis(AnalysisBDO analysisBDO) {
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
		 * 1 series graph Ratio of GDP per capita (current US$) NY.GDP.PCAP.CD CO2
		 * emissions (metric tons per capita) EN.ATM.CO2E.PC
		 */

		/*
		 * Data fetching Link Adapter is used to fetch data from the respective bank
		 */
		LinkAdapter newLink = new WBAdapter();
		HashMap<String, HashMap<Integer, Double>> theData = new HashMap<String, HashMap<Integer, Double>>();

		HashMap<Integer, Double> gdpData = newLink.analysisParameter(country, "NY.GDP.PCAP.CD", fromDate, toDate);
		HashMap<Integer, Double> co2Data = newLink.analysisParameter(country, "EN.ATM.CO2E.PC", fromDate, toDate);

		HashMap<Integer, Double> yearPerValue = new HashMap<Integer, Double>();

		int start = Integer.parseInt(fromDate);
		int end = Integer.parseInt(toDate);
		for (int year = start; year <= end; year++) {

			double gdpValue = gdpData.get(year);
			double co2Value = co2Data.get(year);
			double ratio;
			if (co2Value != 0)
				ratio = gdpValue / co2Value;
			else
				ratio = 0.0;

			yearPerValue.put(year, ratio);

		}

		String analysis = "GDP Per Capita vs CO2 Emissions";
		// analysis and yearPerValue is the hashmap.
		theData.put(analysis, yearPerValue);

		this.analysisResult.setTheData(theData);

		return this.analysisResult;
	}
}