package puf.m2.hms.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import puf.m2.hms.exception.PatientException;
import puf.m2.hms.exception.PhysicianAssignmentException;
import puf.m2.hms.exception.PhysicianException;
import puf.m2.hms.model.Patient;
import puf.m2.hms.model.Physician;
import puf.m2.hms.model.PhysicianAssignment;

public class AssignDoctor extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Variables declaration - do not modify
	private javax.swing.JButton btnAssign;
	private javax.swing.JComboBox cboPatientID;
	private javax.swing.JComboBox cboDoctorID;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;

	// End of variables declaration

	public AssignDoctor() {
		initComponents();
		fillComboBox();
		addActionListener();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if ("Assign".equals(e.getActionCommand())) {

			try {
				int patientID = Integer.parseInt(cboPatientID.getSelectedItem()
						.toString());
				Patient patient = Patient.getPatientById(patientID);
				if (patient == null)
					throw new PatientException(null);

				int doctorID = Integer.parseInt(cboDoctorID.getSelectedItem()
						.toString());
				Physician doctor = Physician.getPhysicianById(doctorID);
				if (doctor == null)
					throw new PhysicianException(null);

				PhysicianAssignment physicianAssignment = new PhysicianAssignment(
						patient, doctor);

				// check if duplicate data
				if (isDuplicateAssign(patient, doctor)) {
					JOptionPane.showMessageDialog(this,
							"Duplicate value, can not assign these value",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					physicianAssignment.save();
					JOptionPane.showMessageDialog(this,
							"Insert new assign successful", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this,
						"Fail to insert new assign", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void addActionListener() {
		btnAssign.setActionCommand("Assign");
		btnAssign.addActionListener(this);
	}

	private void fillComboBox() {
		// Fill patientID
		try {
			for (Patient patient : Patient.getPatients()) {
				cboPatientID.addItem(patient.getId());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Can not get list of patient",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

		// Fill DoctorID
		try {
			for (Physician doctor : Physician.getDoctors()) {
				if (doctor.isAvailable()) {
					cboDoctorID.addItem(doctor.getId());
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
					"Can not get list of available doctor", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public boolean isDuplicateAssign(Patient patient, Physician doctor) {
		boolean result = false;

		try {
			for (PhysicianAssignment physicianAssignment : PhysicianAssignment
					.getPhysicianAssignments()) {
				if (physicianAssignment.getPatient().equals(patient)
						&& physicianAssignment.getPhysician().equals(doctor))
					// duplicate value
					return true;
			}
		} catch (PhysicianAssignmentException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		btnAssign = new javax.swing.JButton();
		cboPatientID = new javax.swing.JComboBox();
		cboDoctorID = new javax.swing.JComboBox();

		jLabel1.setText("Patient ID");

		jLabel2.setText("Available doctor");

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
																						cboDoctorID,
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
														cboDoctorID,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(27, 27, 27).addComponent(btnAssign)
								.addContainerGap(127, Short.MAX_VALUE)));
	}
}
