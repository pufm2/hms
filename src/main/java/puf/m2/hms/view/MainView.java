package puf.m2.hms.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainView extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton registerNewPatient_button, lookupInformationOfPatient_button, assignNurse_button;
	private JPanel controlPanel;
	private JLayeredPane layeredPanel;
	
	public MainView(){
	
		this.setPreferredSize(new Dimension(800, 450));
		this.setOpaque(true);
		this.registerNewPatient_button = new JButton("Register new patient");
		this.registerNewPatient_button.setActionCommand("Register");
		this.registerNewPatient_button.addActionListener(this);

		this.lookupInformationOfPatient_button = new JButton("Lookup information of patient");
		this.lookupInformationOfPatient_button.setActionCommand("Lookup");
		this.lookupInformationOfPatient_button.addActionListener(this);

		this.assignNurse_button = new JButton("Assign avaiable nurse");
		this.assignNurse_button.setActionCommand("Assign");
		this.assignNurse_button.addActionListener(this);

		this.controlPanel = new JPanel(new GridBagLayout());
		this.layeredPanel = new JLayeredPane();
		//---------------------------Control panel------------------------------------------//
		this.controlPanel.setBackground(Color.cyan);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2); // insets for all components
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0; // column 0
		c.gridy = 0; // row 0
		c.ipadx = 5; 
		c.ipady = 5; 
		this.controlPanel.add(registerNewPatient_button,c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx =0;
		c.gridy =1;
		this.controlPanel.add(lookupInformationOfPatient_button,c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx =0;
		c.gridy=2;
		this.controlPanel.add(assignNurse_button,c);
		//---------------------------Main panel--------------------------------------------//
		this.setLayout(new BorderLayout());
		this.add(controlPanel,BorderLayout.WEST);
		this.layeredPanel.setSize(400,500);
		this.add(layeredPanel,BorderLayout.CENTER);
		
		this.registerNewPatient_button.addActionListener(this);
		this.lookupInformationOfPatient_button.addActionListener(this);
		this.assignNurse_button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Register".equals(e.getActionCommand())) {
			this.add(new RegisterNewPatient(),BorderLayout.CENTER);
			this.validate();
			//System.out.println("Register");
		};

		if ("Lookup".equals(e.getActionCommand())) {

			this.add(new LookupPatientInfo(),BorderLayout.CENTER);
			this.validate();
			//System.out.println("Lookup");
		};

		if ("Assign".equals(e.getActionCommand())) {
			this.add(new AssignNurse(),BorderLayout.CENTER);
			this.validate();
			//System.out.println("Assign");
		};

	}

	/**
	 * @param args
	 */
	/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                Utils.createAndShowGUI("Main View", new MainView());
	            }
	        });
	    }
	 */
}