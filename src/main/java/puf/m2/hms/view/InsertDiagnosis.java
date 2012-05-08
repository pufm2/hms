package puf.m2.hms.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import puf.m2.hms.exception.MedicalRecordException;
import puf.m2.hms.model.MedicalRecord;
import puf.m2.hms.model.Patient;

public class InsertDiagnosis extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Variables declaration - do not modify
	private javax.swing.JButton btnSave;
	private javax.swing.JComboBox cboPatientID;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lblDetail;
	private javax.swing.JLabel lblPatient;
	private javax.swing.JTextArea txtDetails;

	// End of variables declaration

	public InsertDiagnosis() {
		initComponents();
		fillComboBox();
		addActionListener();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Save".equals(e.getActionCommand())) {

			if (!isValidFields()) {
				return;
			}

			Patient patient = new Patient(Integer.parseInt(cboPatientID
					.getSelectedItem().toString()));
			Date dateAffect = new Date();
			String detail = txtDetails.getText();

			if (isDuplicateMedicalRecord(patient, dateAffect, detail)) {
				// does not allow to save duplicate values: patient,
				// dateAffect, details
				JOptionPane
						.showMessageDialog(
								this,
								"Duplicate value, can not insert these value of diagnosis",
								"Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				MedicalRecord medicalRecord = new MedicalRecord(patient,
						dateAffect, detail);
				try {
					medicalRecord.save();
					JOptionPane.showMessageDialog(this,
							"Insert diagnosis successful", "Sucess",
							JOptionPane.INFORMATION_MESSAGE);
					cboPatientID.setSelectedIndex(0);
					txtDetails.setText("");
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}

		} else if ("Clear".equals(e.getActionCommand())) {
			cboPatientID.setSelectedIndex(0);
			txtDetails.setText("");
			JOptionPane.showMessageDialog(null, "Clear all", "Clear",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void addActionListener() {
		this.btnSave.setActionCommand("Save");
		this.btnSave.addActionListener(this);
	}

	private void fillComboBox() {
		// Fill patientID
		try {
			for (Patient patient : Patient.getPatients()) {
				cboPatientID.addItem(patient.getId());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		lblPatient = new javax.swing.JLabel();
		cboPatientID = new javax.swing.JComboBox();
		lblDetail = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		txtDetails = new javax.swing.JTextArea();
		btnSave = new javax.swing.JButton();

		lblPatient.setText("Patient");

		lblDetail.setText("Details");

		txtDetails.setColumns(20);
		txtDetails.setRows(5);
		jScrollPane1.setViewportView(txtDetails);

		btnSave.setText("Save");

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
												.addComponent(btnSave)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						lblPatient)
																				.addComponent(
																						lblDetail))
																.addGap(39, 39,
																		39)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jScrollPane1,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						cboPatientID,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						72,
																						javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addContainerGap(25, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(lblPatient)
												.addComponent(
														cboPatientID,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(lblDetail)
												.addComponent(
														jScrollPane1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addComponent(btnSave)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
	}// </editor-fold>

	public boolean isDuplicateMedicalRecord(Patient patient, Date dateAffect,
			String detail) {
		boolean result = false;

		try {
			for (MedicalRecord medicalRecord : MedicalRecord
					.loadMedicalRecord(patient)) {
				if (medicalRecord.getDateAffect().equals(dateAffect)
						&& medicalRecord.getDetail().equals(detail))
					// duplicate value
					return true;
			}
		} catch (MedicalRecordException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public boolean isValidFields() {
		boolean result = true;

		/*
		 * // check if date affect is earlier current date Date current = new
		 * Date(); Date dateAffect = txtDateAffect.getDate(); if (dateAffect ==
		 * null) { JOptionPane.showMessageDialog(this,
		 * "You must put a valid date affect for medical record", "Error",
		 * JOptionPane.ERROR_MESSAGE); return false; } else if
		 * (dateAffect.compareTo(current) > 0) {
		 * JOptionPane.showMessageDialog(this,
		 * "Date affect must be ealier than current date", "Error",
		 * JOptionPane.ERROR_MESSAGE); return false; }
		 */
		// check details does not blank
		if (txtDetails.getText().equals("")) {
			JOptionPane.showMessageDialog(this,
					"You must put details of record", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return result;
	}
}
