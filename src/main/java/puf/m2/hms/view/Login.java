package puf.m2.hms.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import puf.m2.hms.model.Role;
import puf.m2.hms.model.User;

public class Login extends javax.swing.JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Variables declaration - do not modify
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPasswordField txtPassword;
	private javax.swing.JTextField txtUsername;

	// End of variables declaration

	private JFrame parent;

	public Login(JFrame parent) {
		this.parent = parent;
		initComponents();

	}

	public void actionPerformed(ActionEvent e) {

		if ("Login".equals(e.getActionCommand())) {
			String username = txtUsername.getText();
			@SuppressWarnings("deprecation")
			String password = txtPassword.getText();
			login(username, password);
		}
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
		txtPassword.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login(txtUsername.getText(), txtPassword.getText());
				}
			}
		});

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

	public void login(String username, String password) {
		User user = User.login(username, password);

		if (user != null) {
			parent.setVisible(false);
			String role = user.getRole();
			if (Role.Receptionist.name().equals(role)) {
				Utils.createAndShowGUI(new JFrame("Receptionist role"),
						new ReceptionistView());
			} else if (Role.Nurse.name().equals(role)) {
				Utils.createAndShowGUI(new JFrame("Nurse role"),
						new NurseView());
			} else if (Role.Doctor.name().equals(role)) {
				Utils.createAndShowGUI(new JFrame("Doctor role"),
						new DoctorView());
			} else if (Role.Admin.name().equals(role)) {
				Utils.createAndShowGUI(new JFrame("Admin role"),
						new AdminView());
			}
		} else {
			JOptionPane.showMessageDialog(parent, "Invalid Credential",
					"Alert", JOptionPane.ERROR_MESSAGE);
		}
	}
}
