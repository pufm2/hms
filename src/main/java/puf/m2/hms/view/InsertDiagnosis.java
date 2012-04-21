package puf.m2.hms.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import puf.m2.hms.model.HmsException;
import puf.m2.hms.model.MedicalRecord;
import puf.m2.hms.model.Patient;
import puf.m2.hms.utils.DateUtils;
import puf.m2.hms.view.datechooser.JDateChooser;

public class InsertDiagnosis extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Variables declaration - do not modify
	private javax.swing.JButton btnSave;
	private javax.swing.JComboBox cboPatientID;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JScrollPane jScrollPane1;
	private JDateChooser txtDateAffect;
	private javax.swing.JTextArea txtDetails;

	// End of variables declaration

	public InsertDiagnosis() {
		initComponents();
		try {
			fillComboBox();
		} catch (HmsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addActionListener();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Save".equals(e.getActionCommand())) {
			Patient patient = new Patient(Integer.parseInt(cboPatientID
					.getSelectedItem().toString()));
			Date dateAffect = txtDateAffect.getDate();
			String detail = txtDetails.getText();

			MedicalRecord medicalRecord = new MedicalRecord(patient,
					dateAffect, detail);
			try {
				medicalRecord.save();
			} catch (HmsException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Insert diagnosis successful");
		} else if ("Clear".equals(e.getActionCommand())) {
			cboPatientID.setSelectedIndex(0);
			try {
				txtDateAffect.setDate(DateUtils.parseDate(DateUtils
						.getCurrentDate()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			txtDetails.setText("");
			JOptionPane.showMessageDialog(null, "Clear all!");
		}
	}

	private void addActionListener() {
		this.btnSave.setActionCommand("Save");
		this.btnSave.addActionListener(this);
	}

	private void fillComboBox() throws HmsException {
		// Fill patientID
		for (Patient patient : Patient.getPatients()) {
			cboPatientID.addItem(patient.getId());
		}
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		cboPatientID = new javax.swing.JComboBox();
		jLabel4 = new javax.swing.JLabel();
		txtDateAffect = new JDateChooser();
		jScrollPane1 = new javax.swing.JScrollPane();
		txtDetails = new javax.swing.JTextArea();
		btnSave = new javax.swing.JButton();

		jLabel1.setText("Patient");

		jLabel2.setText("Date affect");

		jLabel4.setText("Details");

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
																						jLabel2)
																				.addComponent(
																						jLabel1)
																				.addComponent(
																						jLabel4))
																.addGap(18, 18,
																		18)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jScrollPane1,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addGroup(
																						layout.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																								.addComponent(
																										cboPatientID,
																										0,
																										72,
																										Short.MAX_VALUE)
																								.addComponent(
																										txtDateAffect)))))
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
														cboPatientID,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel2)
												.addComponent(
														txtDateAffect,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel4)
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
}
