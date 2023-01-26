package Graphs;

/*
 * Factory design Pattern
 * Graph Factory is used to decide which graph will be used
 */
public class GraphFactory {
	/*
	 * @param graph type String - graph name.
	 * 
	 * @return Graphs
	 */

	public static Graphs determineGraph(String graph) {

		if (graph.equals("Pie Chart")) {
			return new PieGraph();
		} else if (graph.equals("Line Chart")) {
			return new LineGraph();
		} else if (graph.equals("Bar Chart")) {
			return new BarGraph();

		} else if (graph.equals("Scatter Chart")) {
			return new ScatterChart();

		} else if (graph.equals("Report")) {
			return new Report();
		} else {
			return null;
		}
	}
}
