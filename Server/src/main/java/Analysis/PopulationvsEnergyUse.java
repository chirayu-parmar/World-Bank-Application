package Analysis;

import java.util.*;
import org.json.simple.parser.ParseException;
import DataHandler.AnalysisBDO;
import DataHandler.AnalysisResult;
import DataHandler.LinkAdapter;
import DataHandler.WBAdapter;

/*
 * This class perform the Energy Analysis. 
 */
public class PopulationvsEnergyUse extends Analysis {

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
	public PopulationvsEnergyUse(AnalysisBDO analysisBDO) {
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
		 * 2 series Total Population SP.POP.TOTL Energy use (kg of oil equivalent per
		 * capita) EG.USE.PCAP.KG.OE
		 */

		/*
		 * Data fetching Link Adapter is used to fetch data from the respective bank
		 */
		LinkAdapter newLink = new WBAdapter();
		HashMap<String, HashMap<Integer, Double>> theData = new HashMap<String, HashMap<Integer, Double>>();
		HashMap<Integer, Double> popData = newLink.analysisParameter(country, "SP.POP.TOTL", fromDate, toDate);
		HashMap<Integer, Double> energyData = newLink.analysisParameter(country, "EG.USE.PCAP.KG.OE", fromDate, toDate);

		// Population we get in millions so divide each data by 1000000 to show
		// everything properly on graph
		int start = Integer.parseInt(fromDate);
		int end = Integer.parseInt(toDate);
		for (int year = start; year <= end; year++) {
			double refector = popData.get(year);
			// Refectoring the gdp data by 1000000
			popData.put(year, refector / 1000000);
		}

		// 1st analysis data
		String analysisOne = "Total Population (millions)";
		// analysis and yearPerValue is the hashmap.
		theData.put(analysisOne, popData);

		// 2st analysis data
		String analysisTwo = "Energy use (kg of oil equivalent per 1000 capita)";
		// analysis and yearPerValue is the hashmap.
		theData.put(analysisTwo, energyData);

		this.analysisResult.setTheData(theData);

		return this.analysisResult;
	}
}