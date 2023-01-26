package Analysis;

import java.util.HashMap;
import org.json.simple.parser.ParseException;
import DataHandler.AnalysisBDO;
import DataHandler.AnalysisResult;
import DataHandler.LinkAdapter;
import DataHandler.WBAdapter;

public class AirPollutionvsForestArea extends Analysis {

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
	public AirPollutionvsForestArea(AnalysisBDO analysisBDO) {
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
		 * 2 series PM2.5 air pollution, mean annual exposure (micrograms per cubic
		 * meter) EN.ATM.PM25.MC.M3 Forest area (% of land area) AG.LND.FRST.ZS
		 */

		/*
		 * Data fetching Link Adapter is used to fetch data from the respective bank
		 */
		LinkAdapter newLink = new WBAdapter();

		HashMap<Integer, Double> airPollutionData = newLink.analysisParameter(country, "EN.ATM.PM25.MC.M3", fromDate,
				toDate);

		HashMap<Integer, Double> forestAreaData = newLink.analysisParameter(country, "AG.LND.FRST.ZS", fromDate,
				toDate);

		HashMap<String, HashMap<Integer, Double>> theData = new HashMap<String, HashMap<Integer, Double>>();
		// 1st analysis data
		String analysisOne = "PM2.5 air pollution";
		// analysis and yearPerValue is the hashmap.
		theData.put(analysisOne, airPollutionData);

		// 2st analysis data
		String analysisTwo = "Forest area";
		// analysis and yearPerValue is the hashmap.
		theData.put(analysisTwo, forestAreaData);

		System.out.println("Stroting the data into the result object");
		this.analysisResult.setTheData(theData);

		return this.analysisResult;
	}

}
