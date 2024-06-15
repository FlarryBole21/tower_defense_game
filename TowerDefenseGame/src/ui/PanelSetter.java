package ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class PanelSetter {
	
	public static JPanel setPanel(JFrame frame, JLabel label) {
		JPanel jpanel = new GamePanel(170,600,100,20,frame,label);
		jpanel.add(label, BorderLayout.PAGE_END);
		jpanel.requestFocus();
		//frame.add(jpanel);
		return jpanel;
	}
	

	public static JPanel setPanel(JFrame frame, JLabel label,Color color) {
		JPanel jpanel = new GamePanel(170,600,100,20,frame,label);
		jpanel.add(label, BorderLayout.PAGE_END);
		jpanel.requestFocus();
		jpanel.setBackground(color);
		//frame.add(jpanel);
		return jpanel;
	}

}
