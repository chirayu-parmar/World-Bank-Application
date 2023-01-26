package DataHandler;

import java.util.HashMap;

import org.json.simple.parser.ParseException;

/*
 * Adapter design pattern
 * Adapter is used here to adapt to the given Api's for data fetching in the future
 */
public abstract class LinkAdapter {
	/*
	 * @param Country type String - Country value on which the analysis will be
	 * performed .
	 * 
	 * @param fromDate type String - Start date of the analysis
	 * 
	 * @param toDate type String - end date of the analysis
	 * 
	 * @return JSONArray
	 * 
	 * @throws ParseException
	 */
	public abstract HashMap<Integer, Double> analysisParameter(String analysis, String country, String fromDate,
			String toDate) throws ParseException;

}
