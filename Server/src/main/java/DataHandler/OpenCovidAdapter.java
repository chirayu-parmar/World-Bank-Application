package DataHandler;

import java.util.HashMap;
import org.json.simple.parser.ParseException;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/*
 * Adapter for the OpenCovidAPI which fetches data from the OpenCovid API
 */
public class OpenCovidAdapter extends LinkAdapter {
	GetData getData = new GetData();

	/*
	 * Open Covid Adapter to fetch data from the Open Covid API
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
	@SuppressWarnings("deprecation")
	public HashMap<Integer, Double> analysisParameter(String analysis, String country, String fromDate, String toDate)
			throws ParseException {
		String urlString = String.format(
				"https://api.opencovid.ca/timeseries?stat=deaths&geo=can&after=%s-12-31&before=%s-12-31&fill=false&version=false&pt_names=short&hr_names=hruid&legacy=false&fmt=json",
				fromDate, toDate);
		System.out.println(urlString);

		HashMap<Integer, Double> analysisData = new HashMap<Integer, Double>();

		String inline = getData.dataInitiator(urlString);

		int startIndex = inline.indexOf('[');
		int endIndex = inline.indexOf(']') + 1;
		String data = inline.substring(startIndex, endIndex);

		JsonArray jsonArray = new JsonParser().parse(data).getAsJsonArray();
		int sizeOfResults = jsonArray.size();

		String date;
		Double valueForYear = 0.0;

		int startYear = Integer.parseInt(fromDate);
		int endYear = Integer.parseInt(toDate);

		for (int i = 0; i < sizeOfResults; i++) {

			date = jsonArray.get(i).getAsJsonObject().get("date").toString().toString();
			date = date.substring(1, date.length() - 1);

			String requiredYear = startYear + "-12-31";
			// System.out.println(date);

			if (startYear <= endYear && requiredYear.equals(date)) {

				if (jsonArray.get(i).getAsJsonObject().get("value").isJsonNull())
					valueForYear = 0.0;
				else
					valueForYear = jsonArray.get(i).getAsJsonObject().get("value").getAsDouble();

				analysisData.put(startYear, valueForYear);
				startYear++;
			}

		}

		return analysisData;

	}
}
