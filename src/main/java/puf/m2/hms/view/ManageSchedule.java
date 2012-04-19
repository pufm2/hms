package puf.m2.hms.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import puf.m2.hms.model.HmsException;
import puf.m2.hms.model.Physician;
import puf.m2.hms.model.Schedule;
import puf.m2.hms.view.datechooser.JDateChooser;

public class ManageSchedule extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected GridBagConstraints gbc = new GridBagConstraints();

	private JLabel lblDoctorID, lblStartDate, lblEndDate, lblAvailable;
	private JComboBox cboDoctorID;
	private JDateChooser startDate, endDate;
	private JCheckBox chkAvailable;

	private JButton btnSave;

	public ManageSchedule() {
		super();
		initGUIComponent();
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

		addComponent(lblDoctorID, 0, 0);
		addComponent(cboDoctorID, 1, 0);
		addComponent(lblStartDate, 0, 1);
		addComponent(startDate, 1, 1);
		addComponent(lblEndDate, 0, 2);
		addComponent(endDate, 1, 2);
		addComponent(lblAvailable, 0, 3);
		addComponent(chkAvailable, 1, 3);
		addComponent(btnSave, 0, 4);
	}

	private void initGUIComponent() {
		lblDoctorID = new JLabel("Doctor");
		lblStartDate = new JLabel("Start date");
		lblEndDate = new JLabel("End date");
		lblAvailable = new JLabel("Available");
		startDate = new JDateChooser();
		endDate = new JDateChooser();
		chkAvailable = new JCheckBox();
		btnSave = new JButton("Save");

		drawGUI();
		addActionListener();
		fillComboBox();
	}

	private void fillComboBox() {
		try {
			for (Physician doctor : Physician.getDoctors()) {
				cboDoctorID.addItem(doctor.getId());
			}
		} catch (HmsException e) {
			e.printStackTrace();
		}

	}

	private void addActionListener() {
		btnSave.setActionCommand("Save");
		btnSave.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Save".equals(e.getActionCommand())) {
			int doctorID = Integer.parseInt(cboDoctorID.getSelectedItem()
					.toString());
			Physician physician = null;
			try {
				physician = Physician.getPhysicianById(doctorID);
			} catch (HmsException e1) {
				e1.printStackTrace();
			}
			Schedule schedule = new Schedule(physician, startDate.getDate(),
					endDate.getDate(), chkAvailable.isSelected());
			try {
				schedule.save();
			} catch (HmsException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Save schedule successful");
		}
	}
}
