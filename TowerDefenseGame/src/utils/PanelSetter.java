package utils;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class PanelSetter {
	
	public static void setPanel(JFrame frame,JPanel jpanel, Color color) {
		jpanel.setBackground(color);
		frame.add(jpanel);
	}

}
