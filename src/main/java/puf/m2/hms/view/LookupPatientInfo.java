package puf.m2.hms.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import puf.m2.hms.model.HmsException;
import puf.m2.hms.model.Patient;

public class LookupPatientInfo extends JPanel implements ActionListener {

	protected GridBagConstraints gbc = new GridBagConstraints();

	private static final long serialVersionUID = 1L;
	private JRadioButton rbPatientID, rbFullname;
	private JTextField txtPatientID, txtFullname;
	private JButton btnLookup;

	public LookupPatientInfo() {

		this.rbPatientID = new JRadioButton("ID Patient:", true);
		this.rbFullname = new JRadioButton("Full name of Patient:");
		this.txtPatientID = new JTextField(30);
		this.txtFullname = new JTextField(30);
		this.btnLookup = new JButton("Lookup");
		this.btnLookup.setActionCommand("btnLookup");
		this.btnLookup.addActionListener(this);

		// -------------------Group radio button------------------------------//
		ButtonGroup chooseGroup = new ButtonGroup();
		chooseGroup.add(rbPatientID);
		chooseGroup.add(rbFullname);

		drawGUI();
	}

	private void drawGUI() {

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2); // insets for all components

		addComponent(rbPatientID, 0, 0);
		addComponent(txtPatientID, 1, 0);
		addComponent(rbFullname, 0, 1);
		addComponent(txtFullname, 1, 1);
		addComponent(btnLookup, 0, 2);

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ("btnLookup".equals(e.getActionCommand())) {
			// Return patient ResulSet
			int patientID = 0;
			String patient_ID = "";
			patient_ID = txtPatientID.getText();
			if (!patient_ID.equals("")) {
				patientID = Integer.parseInt(txtPatientID.getText());
			}

			try {
				Patient patient = Patient.getPatientById(patientID);

				if (patient != null) {
					String patientInfomation = "Patient infomation \n";
					patientInfomation += "\n Patient ID: " + patient.getId()
							+ "\n Patient name: " + patient.getName()
							+ "\n Birthdate: " + patient.getDateOfBirth()
							+ "\n Address: " + patient.getAddress()
							+ "\n Sex: " + patient.getSex() + "\n Phone: "
							+ patient.getPhone() + "\n Biographic health: "
							+ patient.getBiographicHealth();

					JOptionPane.showMessageDialog(null, patientInfomation);
				}
			} catch (HmsException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
	}
}