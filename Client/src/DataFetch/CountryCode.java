package DataFetch;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * This class is used to fetch the appropriate country code from our JSON File
 */
public class CountryCode {

	/*
	 * This is static method retrieve the country code of given country.
	 * 
	 * @return country name.
	 */
	public static String getCountryCode(String countryName) {
		String countryCode = "";
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("CountryCode.json")) {
			Object object = jsonParser.parse(reader);
			JSONObject countryCodeObj = (JSONObject) object;
			countryCode = (String) countryCodeObj.get(countryName);

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return countryCode;
	}

}
