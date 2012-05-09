package puf.m2.hms.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import puf.m2.hms.exception.HmsException;
import puf.m2.hms.exception.PhysicianException;
import puf.m2.hms.model.Physician;
import puf.m2.hms.model.Schedule;
import puf.m2.hms.view.datechooser.JDateChooser;

public class ManageSchedule extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Variables declaration - do not modify
	private javax.swing.JButton btnSave;
	private javax.swing.JComboBox cboDoctorID;
	private javax.swing.JCheckBox chkAvailable;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel lblDoctorID;
	private javax.swing.JLabel lblEndDate;
	private javax.swing.JLabel lblStartDate;
	private JDateChooser txtEndDate;
	private JDateChooser txtStartDate;

	public ManageSchedule() {
		initComponents();
		addActionListener();
		fillComboBox();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Save".equals(e.getActionCommand())) {
			int doctorID = Integer.parseInt(cboDoctorID.getSelectedItem()
					.toString());
			Physician physician = null;

			try {
				physician = Physician.getPhysicianById(doctorID);
			} catch (PhysicianException e1) {
				System.out.println(e1.getMessage());
			}

			if (!isValidFields()) {
				return;
			} else {

				Schedule schedule = new Schedule(physician,
						txtStartDate.getDate(), txtEndDate.getDate(),
						chkAvailable.isSelected());
				try {
					schedule.save();
					JOptionPane.showMessageDialog(this,
							"Save schedule successful", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (HmsException e1) {
					System.out.println("Save unsuccessful: " + e1.getMessage());
				}
			}
		}
	}

	// End of variables declaration

	private void addActionListener() {
		btnSave.setActionCommand("Save");
		btnSave.addActionListener(this);
	}

	private void fillComboBox() {

		try {
			for (Physician doctor : Physician.getDoctors()) {
				cboDoctorID.addItem(doctor.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public JDateChooser getTxtEndDate() {
		return txtEndDate;
	}

	public JDateChooser getTxtStartDate() {
		return txtStartDate;
	}

	private void initComponents() {

		lblDoctorID = new javax.swing.JLabel();
		cboDoctorID = new javax.swing.JComboBox();
		jLabel2 = new javax.swing.JLabel();
		lblEndDate = new javax.swing.JLabel();
		lblStartDate = new javax.swing.JLabel();
		txtStartDate = new JDateChooser();
		txtEndDate = new JDateChooser();
		chkAvailable = new javax.swing.JCheckBox();
		btnSave = new javax.swing.JButton();

		lblDoctorID.setText("Doctor ID");

		jLabel2.setText("Available");

		lblEndDate.setText("End date");

		lblStartDate.setText("Start date");

		btnSave.setText("Save");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(btnSave)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						lblDoctorID)
																				.addComponent(
																						lblStartDate)
																				.addComponent(
																						lblEndDate)
																				.addComponent(
																						jLabel2))
																.addGap(27, 27,
																		27)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						chkAvailable)
																				.addComponent(
																						txtStartDate,
																						javax.swing.GroupLayout.Alignment.TRAILING)
																				.addComponent(
																						cboDoctorID,
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						0,
																						102,
																						Short.MAX_VALUE)
																				.addComponent(
																						txtEndDate))))
								.addContainerGap(33, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(lblDoctorID)
												.addComponent(
														cboDoctorID,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(lblStartDate)
												.addComponent(
														txtStartDate,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(8, 8, 8)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		lblEndDate)
																.addGap(11, 11,
																		11)
																.addComponent(
																		jLabel2))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		txtEndDate,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		chkAvailable)))
								.addGap(1, 1, 1).addComponent(btnSave)
								.addContainerGap(25, Short.MAX_VALUE)));
	}// </editor-fold>

	public boolean isValidFields() {
		boolean result = true;
		// Check date
		Date current = new Date();
		Date startDate = txtStartDate.getDate();
		Date endDate = txtEndDate.getDate();

		if (startDate == null) {
			JOptionPane.showMessageDialog(this,
					"You must put a valid start date", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (endDate == null) {
			JOptionPane.showMessageDialog(this,
					"You must put a valid end date", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (startDate.compareTo(endDate) > 0) {
			JOptionPane.showMessageDialog(this,
					"Start date must be ealier than end date", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return result;
	}

	public void setTxtEndDate(JDateChooser txtEndDate) {
		this.txtEndDate = txtEndDate;
	}

	public void setTxtStartDate(JDateChooser txtStartDate) {
		this.txtStartDate = txtStartDate;
	}
}
