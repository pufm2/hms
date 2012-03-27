package puf.m2.hms.view;

import java.awt.FlowLayout;

import javax.swing.*;

public class LookupPatientInfo_Result extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextArea txtLookupResult;

	public LookupPatientInfo_Result(){
		this.txtLookupResult = new JTextArea("");
		this.setLayout(new FlowLayout());
		this.add(txtLookupResult);
	}

	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	 */
}
