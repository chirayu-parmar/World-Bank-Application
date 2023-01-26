package DataHandler;

import java.util.HashMap;
import org.json.simple.parser.ParseException;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

@SuppressWarnings("deprecation")

/*
 * Adapter for the WorldBank API which fetches data from the worldBank API
 */
public class WBAdapter extends LinkAdapter {
	GetData getData = new GetData();

	/*
	 * World Bank Adapter to fetch data from the World Bank API
	 * 
	 * @param country type String - Country value on which the analysis will be
	 * performed.
	 * 
	 * @param analysisName type String - analysis name.
	 * 
	 * @param fromDate type String - Start date of the analysis
	 * 
	 * @param toDate type String - end date of the analysis
	 * 
	 * @throws ParseException
	 * 
	 */
	public HashMap<Integer, Double> analysisParameter(String country, String analysisName, String fromDate,
			String toDate) throws ParseException {

		String urlString = String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json",
				country, analysisName, fromDate, toDate);
		System.out.println(urlString);
		String data = getData.dataInitiator(urlString);

		JsonArray jsonArray = new JsonParser().parse(data).getAsJsonArray();

		double valueForYear = 0.0;
		int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();
		int year;

		HashMap<Integer, Double> analysisData = new HashMap<Integer, Double>();
		for (int i = 0; i < sizeOfResults; i++) {

			year = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();

			if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull())
				valueForYear = 0;
			else
				valueForYear = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").getAsDouble();

			analysisData.put(year, valueForYear);

		}

		return analysisData;
	}

}
