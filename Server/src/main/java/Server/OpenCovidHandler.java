package Server;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.parser.ParseException;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import Analysis.Analysis;
import Analysis.AnalysisFactory;
import DataHandler.AnalysisBDO;
import DataHandler.AnalysisResult;
/*
 * Creates the AnalysisBDO with the parameters received in the HTTPrequest
 * Creates an analysis Factory to decide which type of analysis
 * Conducts the analysis
 * Sends response of the data as Json string
 */
public class OpenCovidHandler implements HttpHandler {
	public void handle(HttpExchange t) throws IOException {

		AnalysisBDO analysisBDO = new AnalysisBDO(t.getRequestURI().getQuery());

		String replyLink = "p1:" + analysisBDO.getAnalysis() + " p2:" + analysisBDO.getCountry() + " p3:"
				+ analysisBDO.getFromDate() + " p4:" + analysisBDO.getToDate();
		System.out.println(replyLink);

		Analysis aF = AnalysisFactory.determineAnalysis(analysisBDO);
		AnalysisResult result = null;
		try {
			result = aF.analysisData();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (result != null)
			System.out.println(result.getTheData().toString());

		Gson gson = new Gson();
		String response = gson.toJson(result);
		t.sendResponseHeaders(200, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
}
