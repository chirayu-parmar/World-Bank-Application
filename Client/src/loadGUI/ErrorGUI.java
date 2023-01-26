package loadGUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

// Code reference : https://beginnersbook.com/2015/07/java-swing-tutorial/
/*
 * Creates a GUI for Login
 */
//public class Login extends JFrame implements ActionListener  {
public class ErrorGUI implements ActionListener {

	private static JFrame errorFrame;
	private static JPanel errorPanel;
	private static JButton okButton;
	private static JLabel errorMsg;
	private static JLabel suggestionMsg;

	/*
	 * Displays the Error GUI
	 */
	public void display(String message, String suggestionMessage) {
		// Creating instance of JFrame
		errorFrame = new JFrame("Erorr Message");
		// Set the width and height of frame
		errorFrame.setSize(500, 150);
		// Set the Jframe(GUI) in the center of the screen
		errorFrame.setLocationRelativeTo(null);
		errorFrame.setBackground(new Color(34, 34, 59));

		// Creating instance of JPanel
		errorPanel = new JPanel();
		errorPanel.setBackground(new Color(248, 249, 250));
		// Adding panel to frame
		errorFrame.add(errorPanel);

		// Helper method to add all the components in JFrame
		this.components(errorPanel, message, suggestionMessage);

		// Frame visibility to true.
		errorFrame.setVisible(true);
		errorFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	@SuppressWarnings("static-access")
	private void components(JPanel panel, String errorMessage, String suggestionMessage) {

		panel.setLayout(null);

		// Create submit button.
		okButton = new JButton("OK");
		// Set x, y, width and height of Text Field.
		okButton.setBounds(210, 80, 80, 25);
		// Add password text field into the panel.
		panel.add(okButton);

		// Perform action on button click
		okButton.addActionListener(this); // add action listener to button

		// Create password authentication label.
		errorMsg = new JLabel(errorMessage, errorMsg.CENTER);
		// Set x, y, width and height of text label.
		errorMsg.setBounds(20, 10, 450, 25);
		// Set color to dark red
		errorMsg.setForeground(new Color(255, 0, 0));

		// Create password authentication label.
		suggestionMsg = new JLabel(suggestionMessage, suggestionMsg.CENTER);
		// Set x, y, width and height of text label.
		suggestionMsg.setBounds(20, 30, 450, 25);
		// Set color to dark red
		suggestionMsg.setForeground(new Color(255, 0, 0));

		errorPanel.add(errorMsg);
		errorPanel.add(suggestionMsg);
	}

	/*
	 * Action Performed is called when the Login button is pressed. It compares the
	 * username and password if correct match then the UI is Displayed. If match is
	 * incorrect error message is diplayed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		errorFrame.dispose();
	}

}
