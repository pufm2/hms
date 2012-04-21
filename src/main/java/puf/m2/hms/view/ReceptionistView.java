package puf.m2.hms.view;

import javax.swing.JPanel;

public class ReceptionistView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	// Variables declaration - do not modify
	private javax.swing.JTabbedPane tabReceptionistView;

	// End of variables declaration

	public ReceptionistView() {
		initComponents();
		tabReceptionistView.add(new RegisterPatient(), "Register patient");
		tabReceptionistView.add(new LookupPatient(),
				"Lookup patient information");
		tabReceptionistView.add(new AssignNurse(), "Assign nurse");
		this.add(tabReceptionistView);
	}

	private void initComponents() {
		tabReceptionistView = new javax.swing.JTabbedPane();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tabReceptionistView,
								javax.swing.GroupLayout.DEFAULT_SIZE, 394,
								Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tabReceptionistView,
								javax.swing.GroupLayout.DEFAULT_SIZE, 239,
								Short.MAX_VALUE).addContainerGap()));
	}
}
