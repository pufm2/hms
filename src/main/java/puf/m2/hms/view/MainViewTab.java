package puf.m2.hms.view;

import javax.swing.*;

public class MainViewTab extends JPanel{
	private JTabbedPane containt_tabPanel;
	public MainViewTab(){
		this.containt_tabPanel = new JTabbedPane();
		//---------------Tab panel-----------------------------//
		this.containt_tabPanel.add(new RegisterNewPatient(),"Register new patient");
		this.containt_tabPanel.add(new LookupPatientInfo(),"Lookup information patient");
		this.containt_tabPanel.add(new AssignNurse(),"Assign Nurse");
		this.add(containt_tabPanel);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Utils.createAndShowGUI("ReceptionistRole", new MainViewTab());
            }
        });
	}

}
