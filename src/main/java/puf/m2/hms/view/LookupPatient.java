package puf.m2.hms.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import puf.m2.hms.model.HmsException;
import puf.m2.hms.model.Patient;

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
		// TODO Auto-generated method stub
		if ("Lookup".equals(e.getActionCommand())) {
			// Return patient ResulSet
			int patientID = 0;
			String patient_ID = "";
			patient_ID = txtPatientID.getText();
			if (!patient_ID.equals("")) {
				patientID = Integer.parseInt(txtPatientID.getText());
			}

			try {
				Patient patient = Patient.getPatientById(patientID);

				if (patient != null) {
					String patientInfomation = "Patient infomation \n";
					patientInfomation += "\n Patient ID: " + patient.getId()
							+ "\n Patient name: " + patient.getName()
							+ "\n Birthdate: " + patient.getDateOfBirth()
							+ "\n Address: " + patient.getAddress()
							+ "\n Sex: " + patient.getSex() + "\n Phone: "
							+ patient.getPhone() + "\n Biographic health: "
							+ patient.getBiographicHealth();

					JOptionPane.showMessageDialog(null, patientInfomation);
				}
			} catch (HmsException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
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
}