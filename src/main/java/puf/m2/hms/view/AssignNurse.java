package puf.m2.hms.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import puf.m2.hms.db.Database;
import puf.m2.hms.db.DatabaseImpl;
import puf.m2.hms.model.AssignPhysician;
import puf.m2.hms.model.Physician;

public class AssignNurse extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JLabel lblPatientID, lblAvaiableNurse;
	private JComboBox cboPatientID, cboAvaiableNurse;
	private JButton btnMakeAssign;

	private static Database db = DatabaseImpl.defaultDb;
	private ResultSet rs = null;
	private String query;
	
	public AssignNurse(){
		
		this.lblPatientID = new JLabel("ID Patient:");
		this.lblAvaiableNurse = new JLabel("Available nurse:");
		this.btnMakeAssign = new JButton("Make assign");

		//----------------------Combo box----------------------------------------//
		this.cboPatientID = new JComboBox();
		this.cboAvaiableNurse = new JComboBox();

		//----------------------ADD CONTAINT------------------------------------//
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
		this.add(this.cboPatientID, c);//ID is generate by system

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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Add action listener
		btnMakeAssign.addActionListener(this);
	}

	private void fillComboBox() throws ClassNotFoundException, SQLException {
		// Fill patientID
		db.createConnection();
		db.createStatement();
		
		query = "SELECT Distinct(PatientID) FROM Patient";
		rs = db.getResultSet(query);
		while (rs.next()){
			cboPatientID.addItem(rs.getString("PatientID"));
		}

		// Fill nurseID
		query = "SELECT Distinct(PhysicianID) FROM Physician" +
				" WHERE PhysicianRole = 'Nurse' " +
				" AND Avaiable = 1";
		rs = db.getResultSet(query);
		while (rs.next()){
			cboAvaiableNurse.addItem(rs.getString("PhysicianID"));
		}
	
		db.closeConnection();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if ("Make assign".equals(e.getActionCommand())) {
			AssignPhysician assignPhysician = new AssignPhysician();
			int insertResult=-1;
			int updateResult=-1;
			
			// Save assign information to database
			try {
				insertResult = assignPhysician.insertNewAssign(cboPatientID.getSelectedItem().toString(), cboAvaiableNurse.getSelectedItem().toString());
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			// Update status of nurse become unavailable
			Physician physician = new Physician();
			try {
				String physicianIDToUpdate;
				physicianIDToUpdate = cboAvaiableNurse.getSelectedItem().toString();
				updateResult = physician.updateStatus(physicianIDToUpdate);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			// Show message
			if (insertResult!=0){
				JOptionPane.showMessageDialog(null, "Insert new assign successful");
			}
			else
				JOptionPane.showMessageDialog(null, "Fail to insert new assign");
			
			//Show message
			if (updateResult!=0){
				JOptionPane.showMessageDialog(null, "Update nurse's status successful");
			}
			else
				JOptionPane.showMessageDialog(null, "Fail to update nurse's status");
		}
	}
}
