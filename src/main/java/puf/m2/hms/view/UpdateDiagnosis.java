package puf.m2.hms.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import puf.m2.hms.exception.MedicalRecordException;
import puf.m2.hms.exception.PatientException;
import puf.m2.hms.model.MedicalRecord;
import puf.m2.hms.model.Patient;

public class UpdateDiagnosis extends JPanel implements ActionListener,
		ItemListener {

	private static final long serialVersionUID = 1L;
	// Variables declaration - do not modify
	private javax.swing.JLabel lblPatientID;
	private javax.swing.JComboBox cboPatientID;
	private javax.swing.JLabel lblMedicalRecordID;
	private javax.swing.JComboBox cboMedicalRecordID;
	private javax.swing.JLabel lblDetail;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea txtDetails;
	private javax.swing.JButton btnUpdate;

	// End of variables declaration

	public UpdateDiagnosis() {
		initComponents();
		fillComboBox();
		addActionListener();
		addItemListener();
	}

	public void actionPerformed(ActionEvent e) {
		if ("Update".equals(e.getActionCommand())) {
			Patient patient = new Patient(Integer.parseInt(cboPatientID
					.getSelectedItem().toString()));
			String detail = txtDetails.getText();

			MedicalRecord medicalRecord = null;
			medicalRecord = new MedicalRecord(patient, new Date(), detail);
			try {
				medicalRecord.update();
				JOptionPane.showMessageDialog(this, "Update sucessful",
						"Update diagnosis", JOptionPane.INFORMATION_MESSAGE);
			} catch (MedicalRecordException e1) {
				JOptionPane.showMessageDialog(this, "Can not update diagnosis",
						"Error", JOptionPane.ERROR_MESSAGE);
				System.out.println(e1.getMessage());
			}
		}
	}

	private void addActionListener() {
		btnUpdate.setActionCommand("Update");
		btnUpdate.addActionListener(this);

		cboPatientID.setActionCommand("PatientID");
		cboPatientID.addActionListener(this);

		cboMedicalRecordID.setActionCommand("MedicalRecordID");
		cboMedicalRecordID.addActionListener(this);
	}

	private void addItemListener() {
		cboPatientID.addItemListener(this);
	}

	private void fillComboBox() {
		// Fill patientID
		try {
			for (Patient patient : Patient.getPatients()) {
				cboPatientID.addItem(patient.getId());
			}
		} catch (PatientException e) {
			System.out.println(e.getMessage());
		}
	}

	private void initComponents() {

		lblPatientID = new javax.swing.JLabel();
		cboPatientID = new javax.swing.JComboBox();
		lblMedicalRecordID = new javax.swing.JLabel();
		cboMedicalRecordID = new javax.swing.JComboBox();
		lblDetail = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		txtDetails = new javax.swing.JTextArea();
		btnUpdate = new javax.swing.JButton();

		lblPatientID.setText("Patient ID");

		lblMedicalRecordID.setText("Medical record ID");

		lblDetail.setText("Details");

		txtDetails.setColumns(20);
		txtDetails.setRows(5);
		jScrollPane1.setViewportView(txtDetails);

		btnUpdate.setText("Update");

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
												.addComponent(btnUpdate)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						lblPatientID)
																				.addComponent(
																						lblMedicalRecordID)
																				.addComponent(
																						lblDetail))
																.addGap(18, 18,
																		18)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																								.addComponent(
																										cboPatientID,
																										0,
																										94,
																										Short.MAX_VALUE)
																								.addComponent(
																										cboMedicalRecordID,
																										0,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE))
																				.addComponent(
																						jScrollPane1,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addContainerGap(21, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(lblPatientID)
												.addComponent(
														cboPatientID,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														lblMedicalRecordID)
												.addComponent(
														cboMedicalRecordID,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(lblDetail)
												.addComponent(
														jScrollPane1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(btnUpdate)
								.addContainerGap(19, Short.MAX_VALUE)));
	}// </editor-fold>

	@Override
	public void itemStateChanged(ItemEvent e) {
		cboMedicalRecordID.removeAllItems();
		Patient patient = new Patient(Integer.parseInt(cboPatientID
				.getSelectedItem().toString()));
		try {
			for (MedicalRecord medicalRecord : MedicalRecord
					.loadMedicalRecord(patient)) {
				cboMedicalRecordID.addItem(medicalRecord.getId());
			}
		} catch (MedicalRecordException e1) {
			System.out.println(e1.getMessage());
		}
	}
}
