package puf.m2.hms.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import puf.m2.hms.model.MedicalRecord;
import puf.m2.hms.model.Patient;
import puf.m2.hms.utils.DateUtils;

public class UpdateDiagnosis extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	// Variables declaration - do not modify
	private javax.swing.JButton btnUpdate;
	private javax.swing.JComboBox cboMedicalRecordID;
	private javax.swing.JComboBox cboPatientID;
	private javax.swing.JLabel lblPatientID;
	private javax.swing.JLabel lblDateAffect;
	private javax.swing.JLabel lblDetail;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea txtDetails;

	// End of variables declaration

	public UpdateDiagnosis() {
		initComponents();
		fillComboBox();
		addActionListener();
	}

	public void actionPerformed(ActionEvent e) {
		if ("Update".equals(e.getActionCommand())) {
			Patient patient = new Patient(Integer.parseInt(cboPatientID
					.getSelectedItem().toString()));
			String detail = txtDetails.getText();

			MedicalRecord medicalRecord = null;
			medicalRecord = new MedicalRecord(patient,
					DateUtils.parseDate(lblDateAffect.getText()), detail);
			try {
				medicalRecord.update();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Update diagnosis successful");
		} else if ("PatientID".equals(e.getActionCommand())) {
			Patient patient = new Patient(Integer.parseInt(cboPatientID
					.getSelectedItem().toString()));

			List<MedicalRecord> ls = null;
			try {
				ls = MedicalRecord.loadMedicalRecord(patient);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int index = 0;
			while (ls.size() >= 1) {
				cboMedicalRecordID.addItem(ls.get(index).getId());
				index++;
			}

		} else if ("MedicalRecordID".equals(e.getActionCommand())) {
			int id = Integer.parseInt(cboMedicalRecordID.getSelectedItem()
					.toString());
			MedicalRecord medicalRecord = null;

			try {
				medicalRecord = MedicalRecord.loadMedicalRecordById(id);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			txtDetails.setText(medicalRecord.getDetail());
		}
	}

	private void addActionListener() {
		btnUpdate.setActionCommand("Save");
		btnUpdate.addActionListener(this);

		cboPatientID.setActionCommand("PatientID");
		cboPatientID.addActionListener(this);

		cboMedicalRecordID.setActionCommand("MedicalRecordID");
		cboMedicalRecordID.addActionListener(this);
	}

	private void fillComboBox() {
		// Fill patientID
		try {
			for (Patient patient : Patient.getPatients()) {
				cboPatientID.addItem(patient.getId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initComponents() {

		lblPatientID = new javax.swing.JLabel();
		cboPatientID = new javax.swing.JComboBox();
		lblDateAffect = new javax.swing.JLabel();
		cboMedicalRecordID = new javax.swing.JComboBox();
		lblDetail = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		txtDetails = new javax.swing.JTextArea();
		btnUpdate = new javax.swing.JButton();

		lblPatientID.setText("Patient ID");

		lblDateAffect.setText("Medical record ID");

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
																						lblDateAffect)
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
												.addComponent(lblDateAffect)
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
}
