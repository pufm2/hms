package puf.m2.hms.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

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
import puf.m2.hms.view.datechooser.JDateChooser;

public class ManageMedicalRecord extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	protected GridBagConstraints gbc = new GridBagConstraints();

	// Define GUI component
	JLabel lblRecordID, lblPatientID, lblDateAffect, lblRecordDetail;
	JComboBox cboRecordID, cboPatientID;
	JDateChooser txtDateAffect;
	JTextField txtRecordDetail;
	JButton btnInsert;

	public ManageMedicalRecord() {

		initGUIComponent();
	}

	public void initGUIComponent() {
		lblRecordID = new JLabel("Record ID");
		lblPatientID = new JLabel("Patient ID");
		lblDateAffect = new JLabel("Date affect");
		lblRecordDetail = new JLabel("Record detail");

		cboRecordID = new JComboBox();
		cboPatientID = new JComboBox();

		txtDateAffect = new JDateChooser();
		txtRecordDetail = new JTextField(50);

		btnInsert = new JButton("Insert");

		drawGUI();
		addActionListener();

		try {
			fillPatient();
		} catch (HmsException e) {
			e.printStackTrace();
		}
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

	private void drawGUI() {

		setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(2, 2, 2, 2);

		addComponent(lblRecordID, 0, 0);
		addComponent(cboRecordID, 1, 0);
		addComponent(lblPatientID, 0, 1);
		addComponent(cboPatientID, 1, 1);
		addComponent(lblDateAffect, 0, 2);
		addComponent(txtDateAffect, 1, 2);
		addComponent(lblRecordDetail, 0, 3);
		addComponent(txtRecordDetail, 1, 3);

		addComponent(btnInsert, 0, 5);
	}

	public void addActionListener() {
		btnInsert.addActionListener(this);
	}

	public void fillPatient() throws HmsException {

		for (Patient patient : Patient.getPatients()) {
			cboPatientID.addItem(patient.getId());
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Insert".equals(e.getActionCommand())) {
			
			int patientId = Integer.parseInt(cboPatientID.getSelectedItem().toString());
			Date dateAffect = txtDateAffect.getDate();
			String recordDetail = txtRecordDetail.getText();

			switch (validateInputForm(patientId, DateUtils.dateToString(dateAffect), recordDetail)) {
			case -1:
				JOptionPane.showMessageDialog(null, "You must input valid patientID");
				break;
			case -2:
				JOptionPane.showMessageDialog(null, "You must input valid date");
				break;
			case -3:
				JOptionPane.showMessageDialog(null, "You must input valid record detail");
				break;
			case 1:
				try {
					MedicalRecord medicalrecord = new MedicalRecord(patientId, dateAffect, recordDetail);
					medicalrecord.save();
				} catch (HmsException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private final int validateInputForm(int patientId, String dateAffect,
			String recordDetail) {

		// Check if any field is null
		if (patientId < 0)
			return -1;
		// Date is not valid
		else if (dateAffect.equals("")) // Change later
			return -2;
		else if (recordDetail.equals(""))
			return -3;
		else
			return 1;

	}

}
