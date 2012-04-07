package puf.m2.hms.run;

import puf.m2.hms.view.*;

public class RunHMS {

	/**
	 * This is a Hospital management system
	 * was build for assignment of Project Management course
	 * at PUF - MINF2010
	 * Supervisor: Prof. Emilie Balland 
	 * Students: NGUYEN Huu Phat, LE Xuan Hoan, TRAN Le Nhu Quynh, NGO Minh Anh Thu
	 * Mar-May 2012
	 */
	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Utils.createAndShowGUI("Login", new LoginPanel());
				//Utils.createAndShowGUI("Login", new NurseView());
			}
		});
	}
}
