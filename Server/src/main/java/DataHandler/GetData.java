/*************************************************
 * FALL 2022
 * EECS 3311 http Client SAMPLE CODE
 * ONLT AS A REFERENCE TO SEE THE USE OF http INVOCATIONS THROUGH JAVA
 * THE CODE BELOW DOES NOT DEPICT THE DESIGN TO BE FOLLOWED 
 */
package DataHandler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/*
 * Facade design pattern 
 * This class is called from the main UI and the logic is done inside the classes hiding logic from the main UI
 * This class allows us the fetch data required for analysis
 */
public class GetData {
	/*
	 * This methods creates a RestAPI call to fetch Data from the API link
	 * 
	 * @param stringUrl type string - The url to fetch data from
	 * 
	 * @return String
	 */
	public String dataInitiator(String stringUrl) {

		String inline = "";

		try {
			URL url = new URL(stringUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {

				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();

			}
		} catch (IOException e) {

		}
		return inline;
	}
}
