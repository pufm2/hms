package puf.m2.hms.view;

import javax.swing.JPanel;

public class DoctorView extends JPanel {

	private static final long serialVersionUID = 1L;

	// Variables declaration - do not modify
	private javax.swing.JTabbedPane tabDoctorView;

	// End of variables declaration

	public DoctorView() {
		initComponents();
		tabDoctorView.add(new InsertDiagnosis(), "Insert diagnosis");
		tabDoctorView.add(new UpdateDiagnosis(), "Update diagnosis");
		tabDoctorView.add(new ManageSchedule(), "Manage schedule");
		this.add(tabDoctorView);
	}

	private void initComponents() {
		tabDoctorView = new javax.swing.JTabbedPane();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tabDoctorView,
								javax.swing.GroupLayout.DEFAULT_SIZE, 200,
								Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tabDoctorView,
								javax.swing.GroupLayout.DEFAULT_SIZE, 200,
								Short.MAX_VALUE).addContainerGap()));
	}
}