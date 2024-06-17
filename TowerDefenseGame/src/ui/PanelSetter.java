package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.Main;

public abstract class PanelSetter {
	
	public static GamePanel setGamePanel(JFrame frame, JLabel label,CardLayout layout) {
		GamePanel panel = new GamePanel(170,600,100,20,frame,label,layout);
		panel.add(label, BorderLayout.PAGE_END);
		panel.requestFocus();
		//frame.add(jpanel);
		return panel;
	}
	

	public static GamePanel setGamePanel(JFrame frame, JLabel label,CardLayout layout,Color color) {
		GamePanel panel = new GamePanel(170,600,100,20,frame,label,layout);
		panel.add(label, BorderLayout.PAGE_END);
		panel.requestFocus();
		panel.setBackground(color);
		//frame.add(jpanel);
		return panel;
	}
	
	
	public static JPanel setPanel(String name,LinkedList<JButton> buttons) {
		JPanel panel = new JPanel();
		
		
		for(JButton button: buttons) {
			panel.add(button);
		}
		
		return panel;
	
	}

}
