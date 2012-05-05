package puf.m2.hms.run;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import puf.m2.hms.view.Login;
import puf.m2.hms.view.Utils;

public class RunHMS {

	/**
	 * This is a Hospital management system was build for assignment of Project
	 * Management course at PUF - MINF2010 Supervisor: Prof. Emilie Balland
	 * Students: NGUYEN Huu Phat, LE Xuan Hoan, TRAN Le Nhu Quynh, NGO Minh Anh
	 * Thu Mar-May 2012
	 */
	public static void main(String[] args) {
	    UIManager.put("swing.boldMetal", Boolean.FALSE);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

		        JFrame frame = new JFrame("Login");
	            Login loginPanel = new Login();
	            loginPanel.setParent(frame);
	            Utils.createAndShowGUI(frame, loginPanel);

			}
		});
	}
}
