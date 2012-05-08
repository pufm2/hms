package puf.m2.hms.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import puf.m2.hms.model.Patient;
import puf.m2.hms.view.datechooser.JDateChooser;

public class RegisterPatient extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Variables declaration - do not modify
	private javax.swing.JButton btnRegister;
	private javax.swing.ButtonGroup btnGroupSex;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lblPatientName;
	private javax.swing.JLabel lblPatientName1;
	private javax.swing.JLabel lblPatientName2;
	private javax.swing.JLabel lblPatientName3;
	private javax.swing.JLabel lblPatientName4;
	private javax.swing.JRadioButton rdFemale;
	private javax.swing.JRadioButton rdMale;
	private javax.swing.JTextField txtAddress;
	private javax.swing.JTextArea txtBiographicHealth;
	private JDateChooser txtBirthdate;
	private javax.swing.JTextField txtPatientName;
	private javax.swing.JTextField txtPhoneNumber;

	// End of variables declaration

	public RegisterPatient() {
		initComponents();
		fillComboBox();
		addActionListener();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Register".equals(e.getActionCommand())) {
			int patientSex = "Male".equals(e.getActionCommand()) ? 1 : 0;

			// Check valid fields
			if (!isValidFields())
				return;

			// Create Patient object
			Patient patient = new Patient(txtPatientName.getText(),
					txtBirthdate.getDate().toString(), txtAddress.getText(),
					patientSex, txtPhoneNumber.getText(),
					txtBiographicHealth.getText());

			// Save patient information to database
			try {
				patient.save();
				JOptionPane.showMessageDialog(this,
						"Saved patient's information with new patient ID is "
								+ patient.getId(), "Sucess",
						JOptionPane.INFORMATION_MESSAGE);
				clearField();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,
						"Can not save patient's information", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void addActionListener() {
		btnRegister.addActionListener(this);
	}

	private void clearField() {
		txtPatientName.setText("");
		txtBiographicHealth.setText("");
		txtPhoneNumber.setText("");
		txtAddress.setText("");
	}

	private void fillComboBox() {
	}

	private void initComponents() {

		btnGroupSex = new javax.swing.ButtonGroup();
		lblPatientName = new javax.swing.JLabel();
		txtPatientName = new javax.swing.JTextField();
		lblPatientName1 = new javax.swing.JLabel();
		lblPatientName2 = new javax.swing.JLabel();
		lblPatientName3 = new javax.swing.JLabel();
		lblPatientName4 = new javax.swing.JLabel();
		txtBirthdate = new JDateChooser();
		txtPhoneNumber = new javax.swing.JTextField();
		txtAddress = new javax.swing.JTextField();
		rdMale = new javax.swing.JRadioButton();
		rdFemale = new javax.swing.JRadioButton();
		btnRegister = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		txtBiographicHealth = new javax.swing.JTextArea();
		jLabel1 = new javax.swing.JLabel();

		lblPatientName.setText("Patient name");

		lblPatientName1.setText("Birthdate");

		lblPatientName2.setText("Address");

		lblPatientName3.setText("Phone number");

		lblPatientName4.setText("Biographic health");

		btnGroupSex.add(rdMale);
		btnGroupSex.add(rdFemale);
		rdMale.setText("Male");
		rdFemale.setText("Female");

		btnRegister.setText("Register");

		txtBiographicHealth.setColumns(20);
		txtBiographicHealth.setRows(5);
		jScrollPane1.setViewportView(txtBiographicHealth);

		jLabel1.setText("Sex");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(lblPatientName)
												.addComponent(lblPatientName1)
												.addComponent(lblPatientName2)
												.addComponent(lblPatientName3)
												.addComponent(lblPatientName4)
												.addComponent(jLabel1))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						txtPatientName,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						290,
																						Short.MAX_VALUE)
																				.addComponent(
																						txtBirthdate)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														jScrollPane1,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addComponent(
																														txtPhoneNumber,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														114,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addComponent(
																														txtAddress,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														114,
																														javax.swing.GroupLayout.PREFERRED_SIZE))
																								.addGap(9,
																										9,
																										9)))
																.addContainerGap())
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		rdMale)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		rdFemale)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		102,
																		Short.MAX_VALUE)
																.addComponent(
																		btnRegister)
																.addGap(23, 23,
																		23)))));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(lblPatientName)
												.addComponent(
														txtPatientName,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														txtBirthdate,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(lblPatientName1))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(40, 40,
																		40)
																.addComponent(
																		lblPatientName4))
												.addComponent(
														jScrollPane1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														txtPhoneNumber,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(lblPatientName3))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														txtAddress,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(lblPatientName2))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel1)
																.addContainerGap())
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						rdMale)
																				.addComponent(
																						rdFemale)
																				.addComponent(
																						btnRegister))
																.addContainerGap()))));
	}// </editor-fold>

	private boolean isValidFields() {
		boolean result = true;

		// check patient name can not blank
		if (txtPatientName.getText().equals("")) {
			;
			JOptionPane.showMessageDialog(this, "You must put name of patient",
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// check if birthdate is earlier current date
		Date current = new Date();
		Date birthDate = txtBirthdate.getDate();
		if (birthDate == null) {
			JOptionPane.showMessageDialog(this,
					"You must put a valid birthdate of patient", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (birthDate.compareTo(current) > 0) {
			JOptionPane.showMessageDialog(this,
					"Birthdate must be ealier than current date", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// check patient address can not blank
		if (txtAddress.getText().equals("")) {
			JOptionPane.showMessageDialog(this,
					"You must put address of patient", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// check patient phone can not blank
		if (txtPhoneNumber.getText().equals("")) {
			JOptionPane.showMessageDialog(this,
					"You must put phone number of patient", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// check if phone number is number or string
		try {
			int i = Integer.parseInt(txtPhoneNumber.getText());
			result = true;
		} catch (Exception e) {
			JOptionPane
					.showMessageDialog(
							this,
							"Phone number does not accept character, only 0-9 is acceptable",
							"Error", JOptionPane.ERROR_MESSAGE);
			result = false;
		}

		// check biographicHealth is not null
		if (txtBiographicHealth.getText().equals("")) {
			JOptionPane.showMessageDialog(this,
					"You must put a biographic health of patient", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// check if sex is selected
		if (!rdFemale.isSelected() && !rdMale.isSelected()) {
			JOptionPane.showMessageDialog(this,
					"You must choose patient's gender", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return result;
	}
}