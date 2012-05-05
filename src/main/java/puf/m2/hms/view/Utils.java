package puf.m2.hms.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Utils {
	public static void createAndShowGUI(JFrame frame, JPanel panel){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setOpaque(true);
        
        frame.setContentPane(panel);
        frame.pack();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (screenSize.height - frame.getHeight()) / 2;
        int width = (screenSize.width - frame.getWidth()) / 2;

        frame.setLocation(width, height);
        frame.setVisible(true);
    }
}