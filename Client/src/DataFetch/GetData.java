package DataFetch;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class GetData {
	
	/*
	 * Creates a link to the local host server with the required parameters
	 * receives the response data in the form of a string
	 *
	 *
	 * @param Analysis type String - Type of analysis to be conducted
	 * 
	 * @param Country type String - Country value on which the analysis will be
	 * performed .
	 * 
	 * @param fromDate type String - Start date of the analysis
	 * 
	 * @param toDate type String - end date of the analysis
	 * 
	 * @param bank type String - Bank from which data will be indicated
	 */
		public static String analysisData(String analysis, String country, String fromDate, String toDate, String bank) {
		// Server link.
		String urlString = analysisLink(analysis, country, fromDate, toDate, bank);
		System.out.println(urlString);

		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.connect();
			int responsecode = conn.getResponseCode();
			System.out.println("Response Code " + responsecode);
			// IF THE RESPONSE IS 200 OK GET THE LINE WITH THE RESULTS
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}

				System.out.println("-------- Here is the response to the client: " + inline);
				sc.close();
				return inline;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}
		return "";
	}

	//Creates the server link
	/*
	 * @param Analysis type String - Type of analysis to be conducted
	 * 
	 * @param Country type String - Country value on which the analysis will be
	 * performed .
	 * 
	 * @param fromDate type String - Start date of the analysis
	 * 
	 * @param toDate type String - end date of the analysis
	 * 
	 * @param bank type String - Bank from which data will be indicated
	 */
	private static String analysisLink(String analysis, String country, String fromDate, String toDate, String bank) {
		System.out.println(AnalysisCode.getAnalysisCode(analysis));
		System.out.println(CountryCode.getCountryCode(country));
		return String.format("http://localhost:8000/%s/?p1=%s&p2=%s&p3=%s&p4=%s", bank,
				AnalysisCode.getAnalysisCode(analysis), CountryCode.getCountryCode(country), fromDate, toDate);
	}
}
