package loadGUI;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * This class fetches the login credentials from the JSON Database
 */
public class LoginCredentialFetcher {
	boolean isAuth = false;

	/*
	 * Reads the JSON file to fetch the username and password from the data base
	 * 
	 * @return JSONArray
	 */
	public static JSONArray getValidCredential() throws FileNotFoundException {

		JSONArray jarrCreds = new JSONArray();
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("u.json")) {
			Object object = jsonParser.parse(reader);
			jarrCreds = (JSONArray) object;

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return jarrCreds;

	}

}
