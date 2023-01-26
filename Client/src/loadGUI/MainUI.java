/*************************************************
 * FALL 2022
 * EECS 3311 GUI SAMPLE CODE
 * ONLT AS A REFERENCE TO SEE THE USE OF THE jFree FRAMEWORK
 * THE CODE BELOW DOES NOT DEPICT THE DESIGN TO BE FOLLOWED 
 */

/*
 * Proxy design pattern - Main UI is not shown to the user until the login is sucessful
 * 
 * Singleton Design Pattern - Only one instance of  the Main UI is made 
 * 
 */
package loadGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import DataFetch.AnalysisResult;
import DataFetch.DataValidation;
import DataFetch.GetData;
import Graphs.Graphs;
import Graphs.GraphFactory;

public class MainUI extends JFrame implements GUI {

	private static final long serialVersionUID = 1L;
	JButton recalculate;

	private static MainUI instance;
	boolean dataValidated = false;
	ArrayList<String> graphs = new ArrayList<String>();
	Vector<String> viewsNames;

	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}

	public MainUI() {
		// Set window title
		super("Country Statistics");

		// Set top bar
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		Vector<String> countriesNames = new Vector<String>();
		countriesNames.add("USA");
		countriesNames.add("Canada");
		countriesNames.add("France");
		countriesNames.add("China");
		countriesNames.add("Brazil");
		countriesNames.sort(null);
		JComboBox<String> countriesList = new JComboBox<String>(countriesNames);

		JLabel chooseBank = new JLabel("Choose a Bank: ");
		Vector<String> bankNames = new Vector<String>();
		bankNames.add("WorldBank");
		bankNames.add("OpenCovid");
		bankNames.sort(null);
		JComboBox<String> bankList = new JComboBox<String>(bankNames);

		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		Vector<String> years = new Vector<String>();
		for (int i = 2022; i >= 2000; i--) {
			years.add("" + i);
		}
		JComboBox<String> fromList = new JComboBox<String>(years);
		JComboBox<String> toList = new JComboBox<String>(years);

		JPanel north = new JPanel();

		north.add(chooseCountryLabel);
		north.add(countriesList);
		north.add(from);
		north.add(fromList);
		north.add(to);
		north.add(toList);
		north.add(chooseBank);
		north.add(bankList);

		// Set bottom bar
		recalculate = new JButton("Recalculate");

		JLabel viewsLabel = new JLabel("Available Views: ");

		viewsNames = new Vector<String>();
		viewsNames.add("Pie Chart");
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Scatter Chart");
		viewsNames.add("Report");
		JComboBox<String> viewsList = new JComboBox<String>(viewsNames);
		JButton addView = new JButton("+");
		JButton removeView = new JButton("-");
		JLabel methodLabel = new JLabel("        Choose analysis method: ");

		Vector<String> methodsNames = new Vector<String>();
		methodsNames.add("Forest Area (% of land area)");
		methodsNames.add("Air Pollution & Forest area");
		methodsNames.add("GDP Per Capita vs CO2 Emissions");
		methodsNames.add("Government expenditure on education, total (% of GDP)");
		methodsNames.add("Health expenditure (per 1000 people) vs Hospital beds (per 1,000 people)");
		methodsNames.add("Total Population & Energy use (kg of oil equivalent per 1000 capita)");
		methodsNames.add("Total Population & CO2 emissions (metric tons per capita)");
		methodsNames.add("GDP per 1000 capita & Education expenditure & Health expenditure");
		methodsNames.add("Covid Deaths Per Year");

		JComboBox<String> methodsList = new JComboBox<String>(methodsNames);

		JPanel south = new JPanel();

		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);
		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);

		JPanel east = new JPanel();

		// Set charts region
		JPanel west = new JPanel();
		west.setLayout(new GridLayout(2, 0));
		west.setBackground(new Color(248, 249, 250));
		north.setBackground(new Color(233, 236, 239));
		east.setBackground(new Color(233, 236, 239));
		south.setBackground(new Color(233, 236, 239));

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.CENTER);

		recalculate.addActionListener(new ActionListener() {

			/*
			 * Fetches data selected by the User in the UI Performs Data validation If the
			 * Data is valid a graph with analysis is called
			 * 
			 * @return void
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				west.removeAll();
				String analysis = String.valueOf(methodsList.getSelectedItem());
				String fromDate = String.valueOf(fromList.getSelectedItem());
				String toDate = String.valueOf(toList.getSelectedItem());
				String country = String.valueOf(countriesList.getSelectedItem());
				String bank = String.valueOf(bankList.getSelectedItem());
				DataValidation dv = new DataValidation(analysis, country, fromDate, toDate, bank);

				try {
					dataValidated = dv.validate();
				} catch (IOException | ParseException e1) {
					e1.printStackTrace();
				}

				if (dataValidated) {
					// Data return from the server in the form on gson string.
					String analysisGSON = GetData.analysisData(analysis, country, fromDate, toDate, bank);
					AnalysisResult analysisResult = null;
					if (analysisGSON.length() > 0)
						analysisResult = new Gson().fromJson(analysisGSON, AnalysisResult.class);
					if (analysisResult != null)
						System.out.println(analysisResult.getTheData().toString());

					for (int i = 0; i < graphs.size(); i++) {
						Graphs graph = GraphFactory.determineGraph(graphs.get(i));
						try {
							graph.createGraph(analysis, country, fromDate, toDate, analysisResult, west);

						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
				}
				west.setVisible(true);
				west.repaint();
				MainUI.getInstance().pack();
				MainUI.getInstance().setVisible(true);
			}
		});

		addView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!graphs.contains(String.valueOf(viewsList.getSelectedItem()))) {

					graphs.add(String.valueOf(viewsList.getSelectedItem()));
				}
			}
		});

		removeView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				graphs.remove(String.valueOf(viewsList.getSelectedItem()));
			}
		});

		getContentPane().setVisible(true);
	}

	@Override
	public void display() {
		JFrame frame = MainUI.getInstance();
		frame.setSize(2000, 3000);
		frame.setBackground(new Color(34, 34, 59));
		frame.pack();
		frame.setLocation(70, 20);
		frame.setVisible(true);
	}
}