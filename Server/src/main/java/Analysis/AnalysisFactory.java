package Analysis;

import DataHandler.AnalysisBDO;

/*
 * Factory design pattern
 * 
 * Creates a factory for the different types of analysis
 */
public class AnalysisFactory {

	/*
	 * @param analysis type String - analysis name
	 * 
	 * @param country type String - Country value on which the analysis will be
	 * performed.
	 * 
	 * @param fromDate type String - Start date of the analysis
	 * 
	 * @param toDate type String - end date of the analysis
	 * 
	 * @param bank type String - Bank from which data will be indicated
	 */
	public static Analysis determineAnalysis(AnalysisBDO analysisBDO) {
		String analysis = analysisBDO.getAnalysis();

		if (analysis.equals("ForestArea")) {
			return new ForestAnalysis(analysisBDO);
		}

		else if (analysis.equals("GDPPerCapitavsCO2Emissions")) {
			return new GDPCO2Analysis(analysisBDO);
		}

		else if (analysis.equals("GovernmentEducationExpenditure")) {
			return new EducationExpenditureAnalysis(analysisBDO);
		}

		else if (analysis.equals("HealthExpenditurevsHospitalBeds")) {
			return new HealthExpenditurevsHospitalBeds(analysisBDO);
		}

		else if (analysis.equals("TotalPopulationEnergyUse")) {
			return new PopulationvsEnergyUse(analysisBDO);
		}

		else if (analysis.equals("GDPPerCapitaEducationExpenditureHealthExpenditure")) {
			return new GDPvsEducationExpenditurevsHealthExpenditure(analysisBDO);
		}

		else if (analysis.equals("TotalPopulationCO2Emissions")) {
			return new PopulationvsCO2Analysis(analysisBDO);
		}

		else if (analysis.equals("AirPollutionForestarea")) {
			System.out.println("callling correct air polution object");
			return new AirPollutionvsForestArea(analysisBDO);

		} else if (analysis.equals("CovidDeaths")) {

			return new CovidDeaths(analysisBDO);
		}

		else {
			return null;
		}

	}

}
