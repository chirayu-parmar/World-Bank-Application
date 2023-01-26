package loadGUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.*;

// Code reference : https://beginnersbook.com/2015/07/java-swing-tutorial/
/*
 * Creates a GUI for Login
 */
//public class Login extends JFrame implements ActionListener  {
public class LoginGUI implements GUI, ActionListener {

	private static JFrame loginFrame;
	private static JPanel loginPanel;
	private static JLabel usernameLabel;
	private static JLabel passwordLabel;
	private static JTextField usernameField;
	private static JPasswordField passwordField;
	private static JButton logInButton;
	private static JLabel passwordAuthenticated;

	@Override
	/*
	 * Displays the Login GUI
	 */
	public void display() {

		// Creating instance of JFrame
		loginFrame = new JFrame("Login");
		// Set the width and height of frame
		loginFrame.setSize(310, 190);
		// Set the Jframe(GUI) in the center of the screen
		loginFrame.setLocationRelativeTo(null);

		loginFrame.setBackground(new Color(34, 34, 59));

		// Creating instance of JPanel
		loginPanel = new JPanel();

		loginPanel.setBackground(new Color(248, 249, 250));
		// Adding panel to frame
		loginFrame.add(loginPanel);

		// Helper method to add all the components in JFrame
		this.loginComponents(loginPanel);

		// Frame visibility to true.
		loginFrame.setVisible(true);

		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/*
	 * Sets the Login Components
	 */
	@SuppressWarnings("static-access")
	private void loginComponents(JPanel panel) {

		panel.setLayout(null);

		// Create user name label.
		usernameLabel = new JLabel("Username:");
		// Set x, y, width and height of text label.
		usernameLabel.setBounds(20, 5, 80, 25);
		// Add user name label into the panel.
		panel.add(usernameLabel);

		// Create user name text field.
		usernameField = new JTextField(25);
		// Set x, y, width and height of Text Field.
		usernameField.setBounds(95, 5, 190, 25);
		// Add user name text field into the panel.
		panel.add(usernameField);

		// Create password label.
		passwordLabel = new JLabel("Password:");
		// Set x, y, width and height of text label.
		passwordLabel.setBounds(20, 40, 80, 25);
		// Add password label into the panel.
		panel.add(passwordLabel);

		// Create password text field.
		passwordField = new JPasswordField(25);
		// Set x, y, width and height of Text Field.
		passwordField.setBounds(95, 40, 190, 25);
		// Add password text field into the panel.
		panel.add(passwordField);

		// Create submit button.
		logInButton = new JButton("Log In");
		// Set x, y, width and height of Text Field.
		logInButton.setBounds(125, 75, 80, 25);
		// Add password text field into the panel.
		panel.add(logInButton);

		// Perform action on button click
		logInButton.addActionListener(this); // add action listener to button

		// Create password authentication label.
		passwordAuthenticated = new JLabel("", passwordAuthenticated.CENTER);
		// Set x, y, width and height of text label.
		passwordAuthenticated.setBounds(0, 110, 290, 25);
		// Add password authentication label into the panel.
		loginPanel.add(passwordAuthenticated);
	}

	/*
	 * Fetches Username from the username textField
	 * 
	 * @return String
	 */
	private String getUsername() {
		return usernameField.getText();
	}

	/*
	 * Fetches password from the password textField
	 * 
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	private String getPassword() {
		return passwordField.getText();
	}

	/*
	 * Action Performed is called when the Login button is pressed. It compares the
	 * username and password if correct match then the UI is Displayed. If match is
	 * incorrect error message is diplayed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String username = getUsername(); // get user entered username from the textField1
		String password = getPassword();
		boolean passwordMatch = false;

		try {
			passwordMatch = LoginAuth.fetchAuth(username, password);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (passwordMatch) {

			loginFrame.dispose();

			MainUI success = new MainUI();
			success.display();
		}

		else {
			// Set color to dark red
			passwordAuthenticated.setForeground(new Color(255, 0, 0));
			// Set JLabel text
			passwordAuthenticated.setText("Username or Password is wrong !!!");
		}
	}

}
