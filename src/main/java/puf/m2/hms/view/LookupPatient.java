package puf.m2.hms.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import puf.m2.hms.exception.PatientException;
import puf.m2.hms.model.Patient;
import puf.m2.hms.utils.DateUtils;

public class LookupPatient extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Variables declaration - do not modify
	private javax.swing.JButton btnLookup;
	private javax.swing.ButtonGroup btnGroupChoice;
	private javax.swing.JRadioButton rbPatientID;
	private javax.swing.JRadioButton rbPatientName;
	private javax.swing.JTextField txtPatientID;
	private javax.swing.JTextField txtPatientName;

	// End of variables declaration

	public LookupPatient() {
		initComponents();
		addActionListener();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Lookup".equals(e.getActionCommand())) {

			if (!isValidCondition())
				return;

			int patientID = Integer.parseInt(txtPatientID.getText());

			Patient patient = null;
			try {
				patient = Patient.getPatientById(patientID);
			} catch (PatientException e1) {
				System.out.println(e1.getMessage());
			}

			if (patient != null) {
				String gender = patient.getSex() == 1 ? "Male" : "Female";
				String patientInfomation = "\n Patient ID: " + patient.getId()
						+ "\n Patient name: " + patient.getName()
						+ "\n Birthdate: "
						+ DateUtils.parseDate(patient.getDateOfBirth())
						+ "\n Address: " + patient.getAddress() + "\n Sex: "
						+ gender + "\n Phone: " + patient.getPhone()
						+ "\n Biographic health: "
						+ patient.getBiographicHealth();

				JOptionPane.showMessageDialog(this, patientInfomation,
						"Patient information", JOptionPane.INFORMATION_MESSAGE);
			} else
				JOptionPane.showMessageDialog(this,
						"Can not find patient as you type", "Alert",
						JOptionPane.ERROR_MESSAGE);
		}
	}

	private void addActionListener() {
		btnLookup.setActionCommand("Lookup");
		btnLookup.addActionListener(this);
	}

	private void initComponents() {

		btnGroupChoice = new javax.swing.ButtonGroup();
		rbPatientID = new javax.swing.JRadioButton();
		rbPatientName = new javax.swing.JRadioButton();
		txtPatientID = new javax.swing.JTextField();
		txtPatientName = new javax.swing.JTextField();
		btnLookup = new javax.swing.JButton();

		btnGroupChoice.add(rbPatientID);
		btnGroupChoice.add(rbPatientName);
		rbPatientID.setText("Patient ID");
		rbPatientName.setText("Patient name");

		btnLookup.setText("Lookup");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(19, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(btnLookup)
												.addGroup(
														layout.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
																.addGroup(
																		layout.createSequentialGroup()
																				.addComponent(
																						rbPatientID)
																				.addGap(18,
																						18,
																						18)
																				.addComponent(
																						txtPatientID))
																.addGroup(
																		layout.createSequentialGroup()
																				.addComponent(
																						rbPatientName)
																				.addPreferredGap(
																						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						txtPatientName,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						191,
																						javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addGap(15, 15, 15)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(rbPatientID)
												.addComponent(
														txtPatientID,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(rbPatientName)
												.addComponent(
														txtPatientName,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18).addComponent(btnLookup)
								.addContainerGap(19, Short.MAX_VALUE)));
	}

	public boolean isValidCondition() {
		boolean result = true;
		// check valid if patient ID is select
		if (rbPatientID.isSelected()) {
			// check null value
			if (txtPatientID.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "You must put patient ID",
						"Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			// check patientID is number
			try {
				int i = Integer.parseInt(txtPatientID.getText());
				result = true;
			} catch (Exception e) {
				JOptionPane
						.showMessageDialog(
								this,
								"Patient ID does not accept character, only 0-9 is acceptable",
								"Error", JOptionPane.ERROR_MESSAGE);
				result = false;
			}
		} else if (rbPatientName.isSelected()) {
			// check null value
			if (txtPatientName.getText().equals("")) {
				JOptionPane.showMessageDialog(this,
						"You must put patient name", "Error",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "You must choose one option",
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return result;
	}
}