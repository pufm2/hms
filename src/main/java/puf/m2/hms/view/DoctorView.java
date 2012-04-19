package puf.m2.hms.view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class DoctorView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4046457495686930163L;
	private JTabbedPane containt_tabPanel;

	public DoctorView() {

		this.containt_tabPanel = new JTabbedPane();
		this.containt_tabPanel.add(new InsertDiagnosis(), "Insert diagnosis");
		this.containt_tabPanel.add(new UpdateDiagnosis(), "Update diagnosis");
		this.containt_tabPanel.add(new ManageSchedule(), "Manage schedule");
		this.add(containt_tabPanel);
	}

}
