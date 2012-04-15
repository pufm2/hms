package puf.m2.hms.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import puf.m2.hms.model.HmsException;
import puf.m2.hms.model.Patient;
import puf.m2.hms.view.datechooser.JDateChooser;

public class RegisterPatient extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblPatientName, lblPatientBirthDate, lblPatientAddress,
			lblPatientSex, lblPatientPhone, lblBiographicHealth;
	private JTextField txtPatientName, txtPatientAddress, txtPatientPhone;
	private JTextArea txtBiographicHealth;
	private JButton btnRegister;
	private ButtonGroup sexChoose;
	private JRadioButton rbMale, rbFemale;
	private JDateChooser txtBirthDate = new JDateChooser();

	protected GridBagConstraints gbc = new GridBagConstraints();

	public RegisterPatient() {

		this.lblPatientName = new JLabel("Full name:");
		this.lblPatientBirthDate = new JLabel("Birthdate:");
		this.lblPatientSex = new JLabel("Sex:");
		this.lblPatientAddress = new JLabel("Address:");
		this.lblPatientPhone = new JLabel("Phone number:");
		this.lblBiographicHealth = new JLabel("Biographic Health:");
		this.btnRegister = new JButton("Register");

		this.rbMale = new JRadioButton("Male");
		this.rbFemale = new JRadioButton("Female");
		sexChoose = new ButtonGroup();
		sexChoose.add(rbFemale);
		sexChoose.add(rbMale);

		this.txtPatientName = new JTextField(60);
		new JTextField(12);
		this.txtPatientAddress = new JTextField(50);
		this.txtPatientPhone = new JTextField(12);

		this.txtBiographicHealth = new JTextArea();

		// draw GUI //
		drawGUI();

		// Add action listener
		this.btnRegister.addActionListener(this);
	}

	private void drawGUI() {
		setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(2, 2, 2, 2);

		addComponent(lblPatientName, 0, 0);
		addComponent(txtPatientName, 1, 0);
		addComponent(lblPatientBirthDate, 0, 1);
		addComponent(txtBirthDate, 1, 1);
		addComponent(lblPatientSex, 2, 1);
		addComponent(rbMale, 3, 1);
		addComponent(rbFemale, 4, 1);
		addComponent(lblPatientAddress, 0, 2);
		addComponent(txtPatientAddress, 1, 2);
		addComponent(lblPatientPhone, 0, 3);
		addComponent(txtPatientPhone, 1, 3);
		addComponent(lblBiographicHealth, 0, 4);
		addComponent(txtBiographicHealth, 1, 4, 14, 16);
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
		if ("Register".equals(e.getActionCommand())) {

			int patientSex = "Male".equals(e.getActionCommand()) ? 1 : 0; // If
																			// Male
																			// then
																			// 0

			// Create Patient object

			Patient patient = new Patient(txtPatientName.getText(),
					txtBirthDate.getDate().toString(),
					txtPatientAddress.getText(), patientSex,
					txtPatientPhone.getText(), txtBiographicHealth.getText());

			// Save patient information to database

			try {
				patient.save();
			} catch (HmsException ex) {
				JOptionPane.showMessageDialog(null,
						"Can not save patient's information");
				return;
			}

			JOptionPane.showMessageDialog(null,
					"Saved patient's information with new patient ID is "
							+ patient.getId());

		}
	}
}