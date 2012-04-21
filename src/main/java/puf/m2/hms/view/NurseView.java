package puf.m2.hms.view;

public class NurseView extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variables declaration - do not modify
	private javax.swing.JTabbedPane tabNurseView;

	// End of variables declaration

	public NurseView() {
		initComponents();

		tabNurseView.add(new InsertDiagnosis(), "Insert medical record");
		tabNurseView.add(new AssignDoctor(), "Assign doctor");

		this.add(tabNurseView);
	}

	private void initComponents() {

		tabNurseView = new javax.swing.JTabbedPane();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tabNurseView,
								javax.swing.GroupLayout.DEFAULT_SIZE, 394,
								Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tabNurseView,
								javax.swing.GroupLayout.DEFAULT_SIZE, 239,
								Short.MAX_VALUE).addContainerGap()));
	}// </editor-fold>

}
