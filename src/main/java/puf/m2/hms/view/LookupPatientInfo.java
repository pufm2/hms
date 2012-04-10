package puf.m2.hms.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import puf.m2.hms.model.Patient;


public class LookupPatientInfo extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JRadioButton rbPatientID, rbFullname;
	private JTextField txtPatientID, txtFullname;
	private JButton btnLookup;
	
	public LookupPatientInfo(){
	
		this.rbPatientID = new JRadioButton("ID Patient:",true);
		this.rbFullname = new JRadioButton("Full name of Patient:");
		this.txtPatientID = new JTextField(30);
		this.txtFullname = new JTextField(30);
		this.btnLookup = new JButton("Lookup");
		this.btnLookup.setActionCommand("btnLookup");
		this.btnLookup.addActionListener(this);
		
		//-------------------Group radio button------------------------------//
		ButtonGroup chooseGroup = new ButtonGroup();
		chooseGroup.add(rbPatientID);
		chooseGroup.add(rbFullname);
		
		//------------------ADD CONTAINT------------------------------------//
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2); // insets for all components
		c.gridx = 0; // column 0
		c.gridy = 0; // row 0
		c.ipadx = 5; 
		c.ipady = 5; 
		c.anchor = GridBagConstraints.LINE_START;
		this.add(this.rbPatientID, c); // constraints passed in
		
		c.gridx = 1; 
		c.gridy = 0;
		// c.gridy = 0; // comment out this for reusing the obj
		c.ipadx = 0; // resets the pad to 0
		c.ipady = 0;
		this.add(this.txtPatientID, c);
		
		c.gridx = 0; 
		c.gridy = 1;
		// c.gridy = 0; // comment out this for reusing the obj
		c.ipadx = 0; // resets the pad to 0
		c.ipady = 0;
		this.add(this.rbFullname, c);
		
		c.gridx = 1; 
		c.gridy = 1;
		// c.gridy = 0; // comment out this for reusing the obj
		c.ipadx = 0; // resets the pad to 0
		c.ipady = 0;
		this.add(this.txtFullname, c);
		
		c.gridx = 1; 
		c.gridy = 2;
		// c.gridy = 0; // comment out this for reusing the obj
		c.ipadx = 0; // resets the pad to 0
		c.ipady = 0;
		c.anchor = GridBagConstraints.LAST_LINE_END;
		this.add(this.btnLookup, c);
	}

	/**
	 * @param args
	 */
	/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                Utils.createAndShowGUI("LookupInformationOfStudent", new LookupPatientInfo());
	            }
	        });
	}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ("btnLookup".equals(e.getActionCommand())) {
			// Return patient ResulSet
			int patientID = Integer.parseInt(txtPatientID.getText());
			String patientName = txtFullname.getText();
			
			/*try {
				ResultSet rs = Patient.lookupPatient(patientID, patientName);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			
			// Notify to result form - Quỳnh chưa viết form này nên không kết nối được
			/*this.removeAll();
			this.add(new LookupPatientInfo_Result());
			validate();
			System.out.println("Lookup");*/
		};
	}

}
