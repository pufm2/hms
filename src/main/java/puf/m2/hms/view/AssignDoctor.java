package puf.m2.hms.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import puf.m2.hms.model.HmsException;
import puf.m2.hms.model.Patient;
import puf.m2.hms.model.Physician;
import puf.m2.hms.model.PhysicianAssignment;
import puf.m2.hms.view.datechooser.JDateChooser;

public class AssignDoctor extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	protected GridBagConstraints gbc = new GridBagConstraints();

	private JLabel lblPatientID, lblDoctor, lblStartDate, lblEndDate;
	private JComboBox cboPatientID, cboDoctorID;
	private JDateChooser txtStartDate, txtEndDate;
	private JButton btnMakeAssign;

	public AssignDoctor() {
		initGUIComponent();
	}

	private void initGUIComponent() {
		lblPatientID = new JLabel("Patient ID");
		lblDoctor = new JLabel("Doctor ID");
		lblStartDate = new JLabel("Start date");
		lblEndDate = new JLabel("End date");

		cboPatientID = new JComboBox();
		cboDoctorID = new JComboBox();

		txtStartDate = new JDateChooser();
		txtEndDate = new JDateChooser();

		btnMakeAssign = new JButton("Assign");

		drawGUI();
		addActionListener();

		try {
			fillInCombobox();
		} catch (HmsException e) {
			e.printStackTrace();
		}

	}

	private void fillInCombobox() throws HmsException {
		// Fill patientID

		for (Patient patient : Patient.getPatients()) {
			cboPatientID.addItem(patient.getId());
		}
		// Fill doctor ID

		for (Physician doctor : Physician.getDoctors()) {
			cboDoctorID.addItem(doctor.getId());
		}
	}

	private void addActionListener() {
		btnMakeAssign.addActionListener(this);
	}

	private void drawGUI() {
		setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(2, 2, 2, 2);

		addComponent(lblPatientID, 0, 0);
		addComponent(cboPatientID, 1, 0);
		addComponent(lblDoctor, 0, 1);
		addComponent(cboDoctorID, 1, 1);
		addComponent(lblStartDate, 0, 2);
		addComponent(txtStartDate, 1, 2, 2, 0);
		addComponent(lblEndDate, 0, 3);
		addComponent(txtEndDate, 1, 3, 2, 0);
		addComponent(btnMakeAssign, 0, 5);
	}

	// Add component to specify row and column
	public void addComponent(JComponent comp, int gridx, int gridy,
			int gridwidth, int gridheight) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		add(comp, this.gbc);
	}

	// Add component to specify row and column
	public void addComponent(JComponent comp, int gridx, int gridy) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(comp, this.gbc);
	}

	public void assign(Physician physician, Patient patient, Date startDate,
			Date endDate) throws HmsException {
		if (!checkExistsPatient(patient.getId())) {
			JOptionPane.showMessageDialog(null, "This patientID is not exists");

		} else if (!"doctor".equals(physician.getRole())) {
			JOptionPane.showMessageDialog(null,
					"This physician is not a docotr");

		} else if (!physician.isAvailable()) {
			JOptionPane.showMessageDialog(null, "This doctor is not available");

		} else {
			PhysicianAssignment pa = new PhysicianAssignment(patient, physician);
			pa.save();
		}
	}

	public boolean checkExistsPatient(int patientID) throws HmsException {

		return Patient.checkExistPatient(patientID);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Assign".equals(e.getActionCommand())) {
			int physicianID = Integer.parseInt(cboDoctorID.getSelectedItem()
					.toString());
			int patientID = Integer.parseInt(cboPatientID.getSelectedItem()
					.toString());

			try {
				Physician physician;
				physician = Physician.getPhysicianById(physicianID);
				Patient patient;
				patient = Patient.getPatientById(patientID);
				assign(physician, patient, txtStartDate.getDate(), txtEndDate.getDate());
			} catch (HmsException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Assign failed");
				return;
			} 
			JOptionPane.showMessageDialog(null, "Assign successful");

		}

	}
}
