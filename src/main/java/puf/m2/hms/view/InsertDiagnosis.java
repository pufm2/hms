package puf.m2.hms.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
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

public class InsertDiagnosis extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected GridBagConstraints gbc = new GridBagConstraints();

	private JLabel lblPatientID, lblDateAffect, lblDetails;
	private JComboBox cboPatientID;
	private JDateChooser txtDateAffect;
	private JTextField txtDetails;
	private JButton btnSave;
	private JButton btnClear;

	public InsertDiagnosis() {
		super();
		initGUIComponent();
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

		this.btnClear.setActionCommand("Clear");
		this.btnClear.addActionListener(this);
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
		addComponent(lblDateAffect, 0, 1);
		addComponent(txtDateAffect, 1, 1);
		addComponent(lblDetails, 0, 2);
		addComponent(txtDetails, 1, 2);

		addComponent(btnSave, 0, 3);
		addComponent(btnClear, 1, 3);
	}

	private void initGUIComponent() {
		lblPatientID = new JLabel("Patient");
		lblDateAffect = new JLabel("Date affect");
		lblDetails = new JLabel("Details");
		cboPatientID = new JComboBox();
		txtDateAffect = new JDateChooser();
		txtDetails = new JTextField(50);
		btnSave = new JButton("Save");
		btnClear = new JButton("Clear ");

		drawGUI();
		addActionListener();

		try {
			fillComboBox();
		} catch (HmsException e) {
			e.printStackTrace();
		}
	}

	private void fillComboBox() throws HmsException {
		// Fill patientID
		for (Patient patient : Patient.getPatients()) {
			cboPatientID.addItem(patient.getId());
		}
	}
}
