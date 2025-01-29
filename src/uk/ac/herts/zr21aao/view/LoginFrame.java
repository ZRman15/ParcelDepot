package uk.ac.herts.zr21aao.view;

import uk.ac.herts.zr21aao.Model.Staff;
import uk.ac.herts.zr21aao.controller.ParcelList;
import uk.ac.herts.zr21aao.controller.SystemLog;
import uk.ac.herts.zr21aao.controller.CustomerList;
import uk.ac.herts.zr21aao.Model.Parcel;
import uk.ac.herts.zr21aao.Model.Customer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@SuppressWarnings("unused")
public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField staffIDField;
	private JPasswordField passwordField;

	public LoginFrame() {
		super("Depot Managment");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3, 2, 5, 5)); // 3 rows 2 columns

		// staff ID row
		add(new JLabel("Staff ID:"));
		staffIDField = new JTextField();
		add(staffIDField);

		// password row
		add(new JLabel("Password:"));
		passwordField = new JPasswordField();
		add(passwordField);

		// login button
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(e -> doLogin());
		add(loginButton);
		pack();
		setLocationRelativeTo(null); // center on screen
		setVisible(true);
	}

	private void doLogin() {
		String staffID = staffIDField.getText().trim();
		String password = new String(passwordField.getPassword());
		Staff validStaff = Staff.initStaff();
		// validate Staff
		if (staffID.equals(validStaff.getStaffID()) && password.hashCode() == validStaff.getPassword()) {
			JOptionPane.showMessageDialog(this, "Login successful. Welcome, " + validStaff.getFirstName() + "!");
			SystemLog.getInstance().log("Login Successful by: " + validStaff.toString());
			new MainSystemFrame();
			dispose(); // close window
		} else {
			JOptionPane.showMessageDialog(this, "Invalid Staff ID or Password!", "Login Error",
					JOptionPane.ERROR_MESSAGE);
			SystemLog.getInstance().log("Invalid login detected");
		}
	}

}