package puf.m2.hms.view;

import javax.swing.*;

public class NurseView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTabbedPane containt_tabPanel;
		
	public NurseView(){
		this.containt_tabPanel = new JTabbedPane();
		//---------------Tab panel-----------------------------//
		this.containt_tabPanel.add(new ManageMedicalRecord(),"Medical record");
		this.containt_tabPanel.add(new AssignDoctor(),"Assign doctor");
		this.add(containt_tabPanel);
	}

}
