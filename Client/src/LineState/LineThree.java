package LineState;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

//State Design Pattern
/*
* This class is the Third state on the Line Graph where 3 Line graphs are displayed
*/
public class LineThree implements LineState {

	/*
	 * @param analysisResults type HashMap<String, HashMap<Integer, Double>> -
	 * Stores the name of the analysis as key then another nested hashmap with dates
	 * and data values
	 * 
	 * @param fromDate type String - Start date of the analysis
	 * 
	 * @param toDate type String - end date of the analysis
	 * 
	 * @param analysis type String - name of the analysis
	 * 
	 * @return ChartPanel
	 */
	@Override
	public ChartPanel getGraph(HashMap<String, HashMap<Integer, Double>> analysisResults, String fromDate,
			String toDate, String analysis) {
		int series = 1;

		XYSeries series1 = new XYSeries("");
		XYSeries series2 = new XYSeries("");
		XYSeries series3 = new XYSeries("");

		// add values to dataset

		for (Map.Entry<String, HashMap<Integer, Double>> entry : analysisResults.entrySet()) {

			String analysisVariable = entry.getKey();
			if (series == 1) {
				series1 = new XYSeries(analysisVariable);
			}
			if (series == 2) {
				series2 = new XYSeries(analysisVariable);
			}
			if (series == 3) {
				series3 = new XYSeries(analysisVariable);
			}
			System.out.println(analysisVariable);
			HashMap<Integer, Double> analysisData = entry.getValue();

			int start = Integer.parseInt(fromDate);
			int end = Integer.parseInt(toDate);

			for (int i = start; i <= end; i++) {

				System.out.println(i + "  " + analysisData.get(i));
				if (analysisData.get(i) != 0) {
					if (series == 1) {
						series1.add(i, analysisData.get(i));
					}
					if (series == 2) {
						series2.add(i, analysisData.get(i));

					}
					if (series == 3) {
						series3.add(i, analysisData.get(i));

					}
				}
			}
			series++;

		}

		// add values to dataset and return to graph

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);

		JFreeChart chart = ChartFactory.createXYLineChart(analysis, "Year", "", dataset, PlotOrientation.VERTICAL, true,
				true, false);

		XYPlot plot = chart.getXYPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));

		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(new TextTitle(analysis, new Font("Serif", java.awt.Font.BOLD, 18)));

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		return chartPanel;
	}

}
