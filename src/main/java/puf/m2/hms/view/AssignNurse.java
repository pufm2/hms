package puf.m2.hms.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import puf.m2.hms.model.HmsException;
import puf.m2.hms.model.Patient;
import puf.m2.hms.model.Physician;
import puf.m2.hms.model.PhysicianAssignment;

public class AssignNurse extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblPatientID, lblAvaiableNurse;
	private JComboBox cboPatientID, cboAvaiableNurse;
	private JButton btnMakeAssign;


	public AssignNurse() {

		this.lblPatientID = new JLabel("ID Patient:");
		this.lblAvaiableNurse = new JLabel("Available nurse:");
		this.btnMakeAssign = new JButton("Make assign");

		// ----------------------Combo
		// box----------------------------------------//
		this.cboPatientID = new JComboBox();
		this.cboAvaiableNurse = new JComboBox();

		// ----------------------ADD
		// CONTAINT------------------------------------//
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2); // insets for all components
		c.gridx = 0; // column 0
		c.gridy = 0; // row 0
		c.ipadx = 5;
		c.ipady = 5;
		c.anchor = GridBagConstraints.LINE_START;
		this.add(this.lblPatientID, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		// c.gridy = 0; // comment out this for reusing the obj
		c.ipadx = 0; // resets the pad to 0
		c.ipady = 0;
		this.add(this.cboPatientID, c);// ID is generate by system

		c.gridx = 0; // column 0
		c.gridy = 1; // row 1
		c.anchor = GridBagConstraints.LINE_START;
		this.add(this.lblAvaiableNurse, c);

		// Set data for comboboxs

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1; // column 0
		c.gridy = 1; // row 1
		this.add(this.cboAvaiableNurse, c);

		c.gridx = 1; // column 1
		c.gridy = 2; // row 2
		c.anchor = GridBagConstraints.LINE_END;
		this.add(this.btnMakeAssign, c);
		
		try {
			fillComboBox();
		} catch (HmsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Add action listener
		btnMakeAssign.addActionListener(this);
	}

	private void fillComboBox() throws HmsException {
		// Fill patientID

		for (Patient patient : Patient.getPatients()) {
			cboPatientID.addItem(patient.getId());
		}

		// Fill nurseID
		for (Physician nurse : Physician.getNurses()) {
			if (nurse.isAvailable()) {
				cboAvaiableNurse.addItem(nurse.getId());
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if ("Make assign".equals(e.getActionCommand())) {
			Patient patient;
			try {
				patient = Patient.getPatientById(Integer.parseInt(cboPatientID.getSelectedItem().toString()));
				Physician nurse = Physician.getPhysicianById(Integer.parseInt(cboAvaiableNurse.getSelectedItem().toString()));
				
				PhysicianAssignment physicianAssignment = new PhysicianAssignment(patient, nurse);
				physicianAssignment.save();
				JOptionPane.showMessageDialog(null, "Insert new assign successful");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Fail to insert new assign");
				
			}
		}
	}
}
