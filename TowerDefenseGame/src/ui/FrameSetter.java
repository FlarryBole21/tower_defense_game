package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class FrameSetter {
	

	public static void setFrame(JFrame frame ,JPanel jpanel) {
		frame.add(jpanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack(); 
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
		frame.setVisible(true);
	}
	
	

}
