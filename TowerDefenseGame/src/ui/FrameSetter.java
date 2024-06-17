package ui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import game.Main;

public abstract class FrameSetter {
	
	
	public static void setFrame(JFrame frame,JMenuBar mb) {
		frame.setJMenuBar(mb);
		frame.add(Main.MAINPANEL);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack(); 
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
		frame.setVisible(true);
	}
	

	public static void setFrame(JFrame frame ,JMenuBar mb,JPanel jpanel) {
		frame.add(mb);
		frame.add(jpanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack(); 
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
		frame.setVisible(true);
	}
	
	

}
