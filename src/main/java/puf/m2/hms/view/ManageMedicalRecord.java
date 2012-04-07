package puf.m2.hms.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import puf.m2.hms.model.MedicalRecord;

public class ManageMedicalRecord extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MedicalRecord medicalRecord = new MedicalRecord();
	protected GridBagConstraints gbc = new GridBagConstraints();

	// Define GUI component
	JLabel lblRecordID, lblPatientID, lblDateAffect, lblRecordDetail;
	JComboBox cboRecordID, cboPatientID;
	JTextField txtDateAffect, txtRecordDetail;
	JButton btnInsert, btnUpdate, btnDelete;

	public ManageMedicalRecord(){
		initGUIComponent();
	}

	private void initGUIComponent(){
		lblRecordID = new JLabel("Record ID");
		lblPatientID = new JLabel("Patient ID");
		lblDateAffect = new JLabel("Date affect");
		lblRecordDetail = new JLabel("Record detail");

		cboRecordID = new JComboBox();
		cboPatientID = new JComboBox();

		txtDateAffect = new JTextField();
		txtRecordDetail = new JTextField(50);

		btnInsert = new JButton("Insert");
		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Delete");

		drawGUI();
		addActionListener();

		try {
			fillInCombobox();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Add component to specify row and column
	public void addComponent(JComponent comp, int gridx, int gridy, int gridwidth, int gridheight){
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth; 
		gbc.gridheight = gridheight;
		add(comp, this.gbc);
	}

	// Add component to specify row and column
	public void addComponent(JComponent comp, int gridx, int gridy){
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(comp, this.gbc);
	}

	private void drawGUI(){

		setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(2, 2, 2, 2);

		addComponent(lblRecordID, 0, 0);
		addComponent(cboRecordID, 1, 0);
		addComponent(lblPatientID, 0, 1);
		addComponent(cboPatientID, 1, 1);
		addComponent(lblDateAffect, 0, 2);
		addComponent(txtDateAffect, 1, 2);
		addComponent(lblRecordDetail, 0, 3);
		addComponent(txtRecordDetail, 1, 3);

		addComponent(btnInsert, 0, 5);
		addComponent(btnUpdate, 1, 5);
		addComponent(btnDelete, 2, 5);

	}
	
	public void addActionListener(){
		btnInsert.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
	}

	private void fillInCombobox() throws SQLException{
		ResultSet rs = medicalRecord.loadListOfPatient();
		while (rs.next()){
			cboPatientID.addItem(rs.getString("PatientID"));
		}
	}

	public int insertMedicalRecord() throws SQLException{
		/*
		 * Return 1 if insert successful
		 * Otherwise, return 0
		 */
		int result = 0;
		
		medicalRecord = new MedicalRecord(Integer.parseInt(cboPatientID.getSelectedItem().toString()), txtDateAffect.getText(), txtRecordDetail.getText());
		result = medicalRecord.insertMedicalRecord();
		
		JOptionPane.showMessageDialog(null, "Inserted medical record");
		
		return result;
	}

	public int updateMedicalRecord() throws SQLException{
		/*
		 * Return 1 if update successful
		 * Otherwise, return 0
		 */
		int result = 0;
		
		medicalRecord = new MedicalRecord(Integer.parseInt(cboPatientID.getSelectedItem().toString()), txtDateAffect.getText(), this.txtRecordDetail.toString());
		result = medicalRecord.updateMedicalRecord();

		return result;
	}

	public int deleteMedicalRecord(){
		/*
		 * Return 1 if deelete successful
		 * Otherwise, return 0
		 */
		int result = 0;
		return result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Insert".equals(e.getActionCommand())){
			try {
				insertMedicalRecord();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if ("Update".equals(e.getActionCommand())){
			try {
				updateMedicalRecord();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if ("Delete".equals(e.getActionCommand())){
			deleteMedicalRecord();
		}
	}}
