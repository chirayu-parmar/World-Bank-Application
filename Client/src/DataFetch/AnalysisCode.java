package DataFetch;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/*
 * reads JSON file to get the appropriate Analysis code
 */
public class AnalysisCode {

	public static String getAnalysisCode(String analysis) {
		String analysisCode = "";
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("AnalysisCode.json")) {
			Object object = jsonParser.parse(reader);
			JSONObject analysisCodeObj = (JSONObject) object;
			analysisCode = (String) analysisCodeObj.get(analysis);

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return analysisCode;
	}

}
