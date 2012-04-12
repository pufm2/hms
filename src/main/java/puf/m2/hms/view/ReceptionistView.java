package puf.m2.hms.view;

import javax.swing.*;

public class ReceptionistView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -38928759686737905L;
	private JTabbedPane containt_tabPanel;
	public ReceptionistView(){
		this.containt_tabPanel = new JTabbedPane();
		//---------------Tab panel-----------------------------//
		this.containt_tabPanel.add(new RegisterPatient(),"Register new patient");
		this.containt_tabPanel.add(new LookupPatientInfo(),"Lookup information patient");
		this.containt_tabPanel.add(new AssignNurse(),"Assign Nurse");
		this.add(containt_tabPanel);
	}
}
