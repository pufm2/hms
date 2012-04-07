package puf.m2.hms.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import puf.m2.hms.model.Patient;
import puf.m2.hms.view.datechooser.JDateChooser;

public class DoctorSchedule extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblPatientName, lblPatientBirthDate, lblPatientAddress, lblPatientSex, lblPatientPhone, lblBiographicHealth;
	private JTextField txtPatientName, txtPatientBirthDate, txtPatientAddress, txtPatientPhone;
	private JTextArea txtBiographicHealth;
	private JButton btnRegister;
	private ButtonGroup sexChoose;
	private JRadioButton rbMale, rbFemale;
	private JDateChooser dateChooser = new JDateChooser();

	public DoctorSchedule(){

		this.lblPatientName = new JLabel("Full name:");
		this.lblPatientBirthDate = new JLabel("Birthdate:");
		this.lblPatientSex = new JLabel("Sex:");
		this.lblPatientAddress = new JLabel("Address:");
		this.lblPatientPhone = new JLabel("Phone number:");
		this.lblBiographicHealth = new JLabel("Biographic Health:");
		this.btnRegister = new JButton("Register");
		
		//-----------------Radio button-----------------------------------------------//
		this.rbMale = new JRadioButton("Male");
		this.rbFemale = new JRadioButton("Female");
		sexChoose = new ButtonGroup();
		sexChoose.add(rbFemale);
		sexChoose.add(rbMale);

		//------------------Text field------------------------------------------------//
		this.txtPatientName = new JTextField(60);
		this.txtPatientBirthDate= new JTextField(12);
		this.txtPatientAddress = new JTextField(50);
		this.txtPatientPhone = new JTextField(12);
		
		//------------------Text Area-------------------------------------------------//
		this.txtBiographicHealth = new JTextArea();

		//---------------ADD TO CONTAINT-------------------------------------------------------------//
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2); // insets for all components
		c.gridx = 0; // column 0
		c.gridy = 0; // row 0
		c.ipadx = 5; 
		c.ipady = 5; 
		c.anchor = GridBagConstraints.LINE_START;

		c.gridx = 0; // column 0
		c.gridy = 1; // row 1
		c.anchor = GridBagConstraints.LINE_START;
		this.add(this.lblPatientName, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1; // column 1
		c.gridy = 1;
		c.gridwidth = 4;
		this.add(this.txtPatientName, c); 
		
		c.gridwidth = 1;
		c.gridx = 0; // column 0
		c.gridy = 2; // row 2
		this.add(this.lblPatientBirthDate, c);

		   //---------------Them vao phan combo box sau
	    c.gridwidth = 1;
	    c.gridx = 1; // column 0
	    c.gridy = 2; // row 2
	    this.add(this.dateChooser, c);
	    this.dateChooser.getDate();// lay ngay duoc chon

		c.gridwidth = 1;
		c.gridx = 2; // column 0
		c.gridy = 2; // row 2
		this.add(this.lblPatientSex, c);

		c.gridx = 3; // column 0
		c.gridy = 2; // row 2
		this.add(this.rbFemale, c);

		c.gridx = 4; // column 0
		c.gridy = 2; // row 2
		this.add(this.rbMale, c);

		c.gridx = 0; // column 0
		c.gridy = 3; // row 2
		this.add(this.lblPatientAddress, c);

		c.gridx = 1; // column 0
		c.gridy = 3;
		c.gridwidth = 4; // row 2
		this.add(this.txtPatientAddress, c);

		c.gridwidth = 1;
		c.gridx = 0; // column 0
		c.gridy = 4; // row 2
		this.add(this.lblPatientPhone, c);

		c.gridx = 1; // column 0
		c.gridy = 4; // row 2
		//  c.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.txtPatientPhone, c);

		c.gridwidth = 1;
		c.gridx = 0; // column 0
		c.gridy = 5; // row 2
		this.add(this.lblBiographicHealth, c);

		c.gridx = 1; // column 0
		c.gridwidth=4;
		c.gridy = 5;
		c.ipady = 40;
		this.add(this.txtBiographicHealth, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,10,0,0);  //top padding
		c.gridwidth =1;
		c.ipady = 0;
		c.gridx = 4; // column 0
		c.gridy = 6; // row 
		this.add(this.btnRegister, c);

		// Add action listener
		this.btnRegister.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
