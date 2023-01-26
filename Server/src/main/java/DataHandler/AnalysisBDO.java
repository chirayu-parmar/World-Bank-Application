package DataHandler;

import java.util.HashMap;
import java.util.Map;
/*
 * This BDO hold the parameters for the required analysis Selected by the user
 * 
 */

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
*/
public class AnalysisBDO {

	private String analysis;
	private String country;
	private String fromDate;
	private String toDate;

	
	public AnalysisBDO(String analysisParam) {
		Map<String, String> params = queryToMap(analysisParam);
		this.analysis = params.get("p1");
		this.country = params.get("p2");
		this.fromDate = params.get("p3");
		this.toDate = params.get("p4");
	}
	
	//Convert the HTTPquery request to a MAP for the BDO
	public Map<String, String> queryToMap(String analysisParam) {
		if (analysisParam == null) {
			return null;
		}
		Map<String, String> result = new HashMap<>();
		for (String param : analysisParam.split("&")) {
			String[] entry = param.split("=");
			if (entry.length > 1) {
				result.put(entry[0], entry[1]);
			} else {
				result.put(entry[0], "");
			}
		}
		return result;
	}

	//getters and setters
	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysisName) {
		this.analysis = analysisName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "AnalysisBDO [analysis=" + analysis + ", country=" + country + ", fromDate=" + fromDate + ", toDate="
				+ toDate + "]";
	}

}
