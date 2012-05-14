/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package puf.m2.hms.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import puf.m2.hms.exception.PhysicianException;
import puf.m2.hms.model.Physician;

/**
 * 
 * @author NHPhat
 */
public class ManagePhysician extends javax.swing.JPanel implements
		ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variables declaration - do not modify
	private javax.swing.JButton btnDelete;
	private javax.swing.JButton btnFind;
	private javax.swing.JButton btnInsert;
	private javax.swing.JButton btnUpdate;
	private javax.swing.JComboBox cboRole;
	private javax.swing.JCheckBox chkAvailable;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JTextField txtName;

	// End of variables declaration
	/**
	 * Creates new form ManagePhysician
	 */

	public ManagePhysician() {
		initComponents();
		addActionListener();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String physicianName = txtName.getText();
		if (checkValidFields() != "True") {
			return;
		}

		if ("Insert".equals(e.getActionCommand())) {
			if (isDuplicatePhysicianname(physicianName)) {
				JOptionPane
						.showMessageDialog(
								this,
								"This physician can not be insert, because it is exists in database",
								"Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				// save new physician
				String role = cboRole.getSelectedItem().toString();
				Physician physician = new Physician(physicianName, role,
						chkAvailable.isSelected());
				try {
					physician.save();
					JOptionPane.showMessageDialog(this, "Save successful",
							"Success", JOptionPane.INFORMATION_MESSAGE);
				} catch (PhysicianException e1) {
					JOptionPane.showMessageDialog(this,
							"Can not save physician", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if ("Find".equals(e.getActionCommand())) {
			physicianName = JOptionPane
					.showInputDialog("Please type physician name: ");
			Physician physician = Physician.getPhysicianByName(physicianName);
			if (physician != null) {
				txtName.setText(physician.getName());

				if (physician.getRole() == "Doctor")
					cboRole.setSelectedIndex(0);
				else if (physician.getRole() == "Nurse")
					cboRole.setSelectedIndex(0);

				chkAvailable.setSelected(physician.isAvailable());
			} else {
				JOptionPane.showMessageDialog(this, "Physician "
						+ physicianName + " does not exists", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		} else if ("Update".equals(e.getActionCommand())) {
			Physician physician = Physician.getPhysicianByName(physicianName);
			if (physician != null) {
				try {
					physician.update();
					JOptionPane.showMessageDialog(this, "Update successful",
							"Success", JOptionPane.INFORMATION_MESSAGE);
				} catch (PhysicianException e1) {
					JOptionPane.showMessageDialog(this,
							"Can not update physician", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "User name "
						+ physicianName + " does not exists", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if ("Delete".equals(e.getActionCommand())) {
			if (JOptionPane.showConfirmDialog(this,
					"Do you want delete this physician?", "Alert",
					JOptionPane.OK_CANCEL_OPTION) == 0) { // click OK
				// delete physician
				String role = cboRole.getSelectedItem().toString();
				Physician physician = new Physician(physicianName, role,
						chkAvailable.isSelected());
				physician.setDeleted(true);
				try {
					physician.update();
					JOptionPane.showMessageDialog(this, "Delete successful",
							"Success", JOptionPane.INFORMATION_MESSAGE);
				} catch (PhysicianException e1) {
					JOptionPane.showMessageDialog(this,
							"Can not delete physician", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	private void addActionListener() {
		btnInsert.setActionCommand("Insert");
		btnInsert.addActionListener(this);

		btnFind.setActionCommand("Find");
		btnFind.addActionListener(this);

		btnUpdate.setActionCommand("Update");
		btnUpdate.addActionListener(this);

		btnDelete.setActionCommand("Delete");
		btnDelete.addActionListener(this);

	}

	public String checkValidFields() {
		// check physician name does not blank
		String physicianName = txtName.getText();
		if (physicianName.equals("")) {
			return "You must put physician name";
		}
		return "True";
	}

	public javax.swing.JTextField getTxtName() {
		return txtName;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		txtName = new javax.swing.JTextField();
		cboRole = new javax.swing.JComboBox();
		chkAvailable = new javax.swing.JCheckBox();
		btnInsert = new javax.swing.JButton();
		btnFind = new javax.swing.JButton();
		btnUpdate = new javax.swing.JButton();
		btnDelete = new javax.swing.JButton();

		jLabel1.setText("Name");

		jLabel2.setText("Role");

		jLabel3.setText("Available");

		txtName.setNextFocusableComponent(cboRole);

		cboRole.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Doctor", "Nurse" }));
		cboRole.setNextFocusableComponent(chkAvailable);

		chkAvailable.setNextFocusableComponent(btnInsert);

		btnInsert.setText("Insert");
		btnInsert.setNextFocusableComponent(btnFind);

		btnFind.setText("Find");
		btnFind.setNextFocusableComponent(btnUpdate);

		btnUpdate.setText("Update");
		btnUpdate.setNextFocusableComponent(btnDelete);

		btnDelete.setText("Delete");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel3)
																.addGap(18, 18,
																		18)
																.addComponent(
																		chkAvailable))
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jLabel1)
																				.addComponent(
																						jLabel2))
																.addGap(34, 34,
																		34)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						txtName)
																				.addComponent(
																						cboRole,
																						0,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		btnInsert)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnFind)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnUpdate)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnDelete)))
								.addContainerGap(28, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1)
												.addComponent(
														txtName,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel2)
												.addComponent(
														cboRole,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel3)
												.addComponent(chkAvailable))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnInsert)
												.addComponent(btnFind)
												.addComponent(btnUpdate)
												.addComponent(btnDelete))
								.addContainerGap(19, Short.MAX_VALUE)));
	}// </editor-fold>

	public boolean isDuplicatePhysicianname(String physicianName) {
		Physician physician = Physician.getPhysicianByName(physicianName);
		if (physician != null)
			return true;
		else
			return false;
	}

	public void setTxtName(javax.swing.JTextField txtName) {
		this.txtName = txtName;
	}

}
