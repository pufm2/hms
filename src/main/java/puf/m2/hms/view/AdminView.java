package puf.m2.hms.view;

import javax.swing.JPanel;

public class AdminView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	// Variables declaration - do not modify
	private javax.swing.JTabbedPane tabAdminView;

	// End of variables declaration

	public AdminView() {
		initComponents();
		tabAdminView.add(new ManageUser(), "Manage user");
		tabAdminView.add(new ManagePhysician(), "Manage physician");
		this.add(tabAdminView);
	}

	private void initComponents() {
		tabAdminView = new javax.swing.JTabbedPane();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tabAdminView,
								javax.swing.GroupLayout.DEFAULT_SIZE, 394,
								Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tabAdminView,
								javax.swing.GroupLayout.DEFAULT_SIZE, 239,
								Short.MAX_VALUE).addContainerGap()));
	}
}
