package DataFetch;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import loadGUI.ErrorGUI;

/*
 * This class validates the bank, year and country.
 */
public class DataValidation {

	private String country;
	private String analysis;
	private String fromDate;
	private String toDate;
	private String bank;

	private JSONParser jsonParser;
	private JSONArray excludedCountry;
	private JSONArray excludedYear;
	private JSONObject countryObj;
	private ErrorGUI errorGUI;

	public DataValidation(String analysis, String country, String fromDate, String toDate, String bank) {
		this.country = country;
		this.analysis = analysis;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.bank = bank;

		this.jsonParser = new JSONParser();
		this.excludedCountry = new JSONArray();
		this.excludedYear = new JSONArray();
		this.countryObj = new JSONObject();
		this.errorGUI = new ErrorGUI();
	}

	/*
	 * This method validate the year and country.
	 * 
	 * @return boolean type.
	 */
	public boolean validate() throws FileNotFoundException, IOException, ParseException {
		if (bank.equals("OpenCovid")) {
			if (!analysis.equals("Covid Deaths Per Year")) {
				errorGUI.display("Open Covid API not availble for ", analysis + " ");
				return false;
			}

		}
		if (!bank.equals("OpenCovid")) {
			if (analysis.equals("Covid Deaths Per Year")) {
				errorGUI.display("World Bank API not availble for ", analysis + " ");
				return false;
			}
		}
		return getCountryAuth() && getYearAuth();
	}

	/*
	 * This is the helper method to validate the Country.
	 * 
	 * @return boolean type.
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public boolean getCountryAuth() throws FileNotFoundException {

		if (bank.equals("OpenCovid")) {
			if (analysis.equals("Covid Deaths Per Year")) {
				if (!country.equals("Canada")) {
					errorGUI.display("Data for " + this.country + " is not avaiable !!!", "Please select Canada.");
					return false;
				}
			}
		}

		boolean isExcludedCountry = true;

		try (FileReader reader = new FileReader("ExcludeCountry.json")) {
			Object object = jsonParser.parse(reader);
			excludedCountry = (JSONArray) object;
			countryObj.put("country", this.country);

			for (Object currentObject : excludedCountry) {
				if (countryObj.equals(currentObject)) {
					isExcludedCountry = false;
				}
			}

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		if (!isExcludedCountry) {
			System.out.println("Cannot fetch data for " + this.country);
			errorGUI.display("Data for " + this.country + " is not avaiable !!!",
					"Please select country other than France and China.");
		}
		return isExcludedCountry;
	}

	/*
	 * This is the helper method to validate the Year.
	 * 
	 * @return boolean type.
	 * 
	 * @throws IOException
	 */
	public boolean getYearAuth() throws IOException {

		try (FileReader reader = new FileReader("IncludedDates.json")) {
			Object object = jsonParser.parse(reader);
			excludedYear = (JSONArray) object;

			for (Object currentObject : excludedYear) {
				JSONObject excludedYearObj = (JSONObject) currentObject;
				if (this.analysis.equals(excludedYearObj.get("analysis"))) {
					String fromDate = (String) excludedYearObj.get("start");
					String toDate = (String) excludedYearObj.get("end");

					if (Integer.parseInt(this.fromDate) > Integer.parseInt(this.toDate)) {
						System.out.println("start dates:" + "start has to start from " + fromDate
								+ " end has to end at " + toDate);
						errorGUI.display(
								"The year range of " + this.analysis + " for " + this.country + " is invalid !!!",
								"Please select the year range between " + fromDate + " to " + toDate + ".");
						return false;
					}

					if (Integer.parseInt(fromDate) <= Integer.parseInt(this.fromDate)
							&& Integer.parseInt(toDate) >= Integer.parseInt(this.toDate)) {
						System.out.println("Valid date");
						return true;
					}

					else {

						System.out.println("start dates:" + "start has to start from " + fromDate
								+ " end has to end at " + toDate);
						errorGUI.display(
								"The year range of " + this.analysis + " for " + this.country + " is invalid !!!",
								"Please select the year range between " + fromDate + " to " + toDate + ".");
					}
				}
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return false;
	}
}
