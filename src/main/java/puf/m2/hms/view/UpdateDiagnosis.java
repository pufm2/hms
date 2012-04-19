package puf.m2.hms.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import puf.m2.hms.model.HmsException;
import puf.m2.hms.model.MedicalRecord;
import puf.m2.hms.model.Patient;
import puf.m2.hms.utils.DateUtils;

public class UpdateDiagnosis extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UpdateDiagnosis() {
		super();
		initGUIComponent();
	}

	protected GridBagConstraints gbc = new GridBagConstraints();

	private JLabel lblPatientID, lblMedicalRecord, lblDetails;
	private JComboBox cboPatientID, cboMedicalRecordID;

	private JLabel lblDate;
	private JTextField txtDetails;
	private JButton btnSave;
	private JButton btnClear;

	private void addActionListener() {
		btnSave.setActionCommand("Save");
		btnSave.addActionListener(this);

		btnClear.setActionCommand("Clear");
		btnClear.addActionListener(this);

		cboPatientID.setActionCommand("PatientID");
		cboPatientID.addActionListener(this);

		cboMedicalRecordID.setActionCommand("MedicalRecordID");
		cboMedicalRecordID.addActionListener(this);
	}

	// Add component to specify row and column
	public void addComponent(JComponent comp, int gridx, int gridy) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(comp, this.gbc);
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

	private void drawGUI() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2); // insets for all components

		addComponent(lblPatientID, 0, 0);
		addComponent(cboPatientID, 1, 0);
		addComponent(lblMedicalRecord, 0, 1);
		addComponent(cboMedicalRecordID, 1, 1);
		addComponent(lblDate, 0, 2);
		addComponent(lblDetails, 0, 3);
		addComponent(txtDetails, 1, 3);

		addComponent(btnSave, 0, 4);
		addComponent(btnClear, 1, 4);
	}

	private void initGUIComponent() {
		lblPatientID = new JLabel("Patient");
		cboPatientID = new JComboBox();
		lblMedicalRecord = new JLabel("Medical record");
		cboMedicalRecordID = new JComboBox();
		lblDetails = new JLabel("Details");
		lblDate = new JLabel("    ");
		txtDetails = new JTextField(50);
		btnSave = new JButton("Update");
		btnClear = new JButton("Cancel");

		drawGUI();

		try {
			fillComboBox();
		} catch (HmsException e) {
			e.printStackTrace();
		}

		addActionListener();
	}

	private void fillComboBox() throws HmsException {
		// Fill patientID
		for (Patient patient : Patient.getPatients()) {
			cboPatientID.addItem(patient.getId());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Update".equals(e.getActionCommand())) {
			Patient patient = new Patient(Integer.parseInt(cboPatientID
					.getSelectedItem().toString()));
			String detail = txtDetails.getText();

			MedicalRecord medicalRecord = null;
			try {
				medicalRecord = new MedicalRecord(patient,
						DateUtils.parseDate(lblDate.getText()), detail);
				try {
					medicalRecord.update();
				} catch (HmsException e1) {
					e1.printStackTrace();
				}
			} catch (ParseException e2) {

				e2.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Update diagnosis successful");
		} else if ("PatientID".equals(e.getActionCommand())) {
			Patient patient = new Patient(Integer.parseInt(cboPatientID
					.getSelectedItem().toString()));
			try {
				List<MedicalRecord> ls = MedicalRecord
						.loadMedicalRecord(patient);
				int index = 0;
				while (ls.size() >= 1) {
					cboMedicalRecordID.addItem(ls.get(index).getId());
					index++;
				}
			} catch (HmsException e1) {
				e1.printStackTrace();
			}
		} else if ("MedicalRecordID".equals(e.getActionCommand())) {
			int id = Integer.parseInt(cboMedicalRecordID.getSelectedItem()
					.toString());
			MedicalRecord medicalRecord = null;
			try {
				medicalRecord = MedicalRecord.loadMedicalRecordById(id);
			} catch (HmsException e1) {
				e1.printStackTrace();
			}
			lblDate.setText(DateUtils.dateToString(medicalRecord
					.getDateAffect()));
			txtDetails.setText(medicalRecord.getDetail());
		}
	}
}
