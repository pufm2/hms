package puf.m2.hms.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Utils {
	public static void createAndShowGUI(String testFrameName, JPanel newContentPane){
        //Create and set up the window.
        JFrame frame = new JFrame(testFrameName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Create and set up the content pane.
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}