package puf.m2.hms.view;

import javax.swing.*;

public class DoctorView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4046457495686930163L;
	private JTabbedPane containt_tabPanel;
	public DoctorView(){
		this.containt_tabPanel = new JTabbedPane();
		//---------------Tab panel-----------------------------//
		this.containt_tabPanel.add(new DoctorSchedule(),"Doctor schedule");
		this.add(containt_tabPanel);
	}

}
