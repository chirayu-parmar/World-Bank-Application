package Analysis;

import org.json.simple.parser.ParseException;

import DataHandler.AnalysisBDO;
import DataHandler.AnalysisResult;

/*
 * This abstract class is implemented by all the analysisType classes
 */

/* Stratergy design - the type of Analysis  used is decided by stratergy during runtime
 * 
 * Facade design pattern
 * 
 * The graphs logic is hidden from the main UI
 * The Graphs interface is implemented by all the graph classes
 */

/*
 * Analysis Data method is used to call the analysis result object which holds the analysis data
 * 
 * @param country type String - Country value on which the analysis will be
 * performed .
 * @param fromDate type String - Start date of the analysis
 * @param toDate type String - end date of the analysis
 * @param bank type String - Bank from which the data will be fetched
 */
public abstract class Analysis {

	protected String country;
	protected String fromDate;
	protected String toDate;
	protected String bank;
	protected AnalysisResult analysisResult;

	public Analysis(AnalysisBDO analysisBDO) {
		this.country = analysisBDO.getCountry();
		this.fromDate = analysisBDO.getFromDate();
		this.toDate = analysisBDO.getToDate();
		this.analysisResult = new AnalysisResult();
	}

	/*
	 * this method is used to call the analysis result object which holds the
	 * analysis data
	 * 
	 * @throws ParseException
	 * 
	 * @return AnalysisResult type
	 */
	public abstract AnalysisResult analysisData() throws ParseException;
}
