package puf.m2.hms.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import puf.m2.hms.model.HmsException;
import puf.m2.hms.model.Patient;
import puf.m2.hms.model.Physician;
import puf.m2.hms.model.PhysicianAssignment;

public class AssignNurse extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Variables declaration - do not modify
	private javax.swing.JButton btnAssign;
	private javax.swing.JComboBox cboPatientID;
	private javax.swing.JComboBox cboNurseID;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;

	// End of variables declaration

	public AssignNurse() {
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

		if ("Make assign".equals(e.getActionCommand())) {
			Patient patient;
			try {
				patient = Patient.getPatientById(Integer.parseInt(cboPatientID
						.getSelectedItem().toString()));
				Physician nurse = Physician.getPhysicianById(Integer
						.parseInt(cboNurseID.getSelectedItem().toString()));

				PhysicianAssignment physicianAssignment = new PhysicianAssignment(
						patient, nurse);
				physicianAssignment.save();
				JOptionPane.showMessageDialog(null,
						"Insert new assign successful");
			} catch (Exception ex) {
				JOptionPane
						.showMessageDialog(null, "Fail to insert new assign");

			}
		}
	}

	private void addActionListener() {
		btnAssign.setActionCommand("Assign");
		btnAssign.addActionListener(this);
	}

	private void fillComboBox() throws HmsException {
		// Fill patientID

		for (Patient patient : Patient.getPatients()) {
			cboPatientID.addItem(patient.getId());
		}

		// Fill nurseID
		for (Physician nurse : Physician.getNurses()) {
			if (nurse.isAvailable()) {
				cboNurseID.addItem(nurse.getId());
			}
		}

	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		btnAssign = new javax.swing.JButton();
		cboPatientID = new javax.swing.JComboBox();
		cboNurseID = new javax.swing.JComboBox();

		jLabel1.setText("Patient ID");

		jLabel2.setText("Available nurse");

		btnAssign.setText("Assign");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(82, 82, 82)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(btnAssign)
												.addGroup(
														layout.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
																.addGroup(
																		layout.createSequentialGroup()
																				.addComponent(
																						jLabel1)
																				.addPreferredGap(
																						javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						cboPatientID,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						137,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGroup(
																		layout.createSequentialGroup()
																				.addComponent(
																						jLabel2)
																				.addGap(32,
																						32,
																						32)
																				.addComponent(
																						cboNurseID,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						137,
																						javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addContainerGap(90, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(22, 22, 22)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1)
												.addComponent(
														cboPatientID,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(22, 22, 22)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel2)
												.addComponent(
														cboNurseID,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(27, 27, 27).addComponent(btnAssign)
								.addContainerGap(127, Short.MAX_VALUE)));
	}
}
