
package loadGUI;

import java.io.FileNotFoundException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
 * Class to authorize the Login Credentials
 */
public class LoginAuth {

	/*
	 * Compares the given login credentials with the credentials given in the
	 * database and return the respective boolean type
	 * 
	 * @param username type String - username which the user has Entered.
	 * 
	 * @param password type String - password which the user has Entered.
	 * 
	 * @return boolean
	 * 
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static boolean fetchAuth(String username, String password) throws FileNotFoundException {

		boolean isAuth = false;
		JSONArray jarrCreds = LoginCredentialFetcher.getValidCredential();

		JSONObject userInfo = new JSONObject();
		userInfo.put("username", username);
		userInfo.put("password", password);

		for (Object currentObject : jarrCreds) {
			if (userInfo.equals(currentObject)) {
				isAuth = true;
			}

		}
		return isAuth;
	}

}
