package puf.m2.hms.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import puf.m2.hms.exception.UserException;
import puf.m2.hms.model.User;

public class Login extends javax.swing.JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	public boolean loginSuccesful = false;

	// Variables declaration - do not modify
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPasswordField txtPassword;
	private javax.swing.JTextField txtUsername;

	// End of variables declaration

	public Login() {
		initComponents();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {

		if ("Login".equals(e.getActionCommand())) {
			login(txtUsername.getText(), txtPassword.getText());
		}
	}

	private void login(String username, String password) {

		String role;

		User user = null;
		try {
			user = User.login(username, password);
		} catch (UserException userException) {
			System.out.println("Can not login with "
					+ userException.getUsername() + "@"
					+ userException.getPassword());
		}

		if (user != null) {
			loginSuccesful = true;
			role = user.getRole();
			if (role.equals("receptionist")) {
				// login successful, show "Main view" form of receptionist
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						UIManager.put("swing.boldMetal", Boolean.FALSE);
						Utils.createAndShowGUI("Receptionist role",
								new ReceptionistView());
					}
				});
			} else if (role.equals("nurse")) {
				// login successful, show "Main view" form of nurse
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						UIManager.put("swing.boldMetal", Boolean.FALSE);
						Utils.createAndShowGUI("Nurse role", new NurseView());
					}
				});
			} else if (role.equals("doctor")) {
				// login successful, show "Main view" form of doctor
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						UIManager.put("swing.boldMetal", Boolean.FALSE);
						Utils.createAndShowGUI("Doctor role", new DoctorView());
					}
				});
			}
		} else {
			// login unsuccessful
			JOptionPane.showMessageDialog(null,
					"Login fail, please retype username and password");
		}

	}

	public boolean checkValidateUser(String username, String password) {
		User user = null;
		try {
			user = User.login(username, password);
		} catch (UserException e) {
			JOptionPane.showMessageDialog(null,
					"Invalid username and/or password");
		}
		return user != null;
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		txtUsername = new javax.swing.JTextField();
		txtPassword = new javax.swing.JPasswordField();
		javax.swing.JButton btnLogin = new javax.swing.JButton();

		jLabel1.setText("Username");

		jLabel2.setText("Password");

		txtPassword.setText("");

		btnLogin.setText("Login");
		btnLogin.addActionListener(this);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(btnLogin)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				false)
																				.addComponent(
																						jLabel2,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						jLabel1,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						txtUsername)
																				.addComponent(
																						txtPassword,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						128,
																						Short.MAX_VALUE))))
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1)
												.addComponent(
														txtUsername,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel2)
												.addComponent(
														txtPassword,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addComponent(btnLogin)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
	}
}
