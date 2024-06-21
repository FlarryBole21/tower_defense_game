package ui.setter;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game.GamePanel;
import game.Main;
import ui.BackgroundPanel;
import utils.Path;

public abstract class PanelSetter {
	
	public static GamePanel setGamePanel(JFrame frame, CardLayout layout, JLabel label,LinkedList<JButton> imageButtons) {
		GamePanel panel = new GamePanel(170,200,frame,layout,label,imageButtons);

		BackgroundPanel bottomPanel = new BackgroundPanel(Path.IMAGE_BACKGROUND_BOTTOM_PANEL);
		//JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.setBackground(Color.BLACK);
		bottomPanel.setName("Bottom Panel");
        //add(bottomPanel, BorderLayout.SOUTH);

		for(JButton imageButton: imageButtons) {
			bottomPanel.add(imageButton); 
		}
		
		bottomPanel.add(label);
		panel.add(bottomPanel,BorderLayout.SOUTH);
		panel.requestFocus();
		//frame.add(jpanel);
		return panel;
	}
	

	
	public static JPanel setPanel(String name,LinkedList<String> text,LinkedList<JButton> buttons,
			int textFontSize, int buttonFontSize,int textButtonDistance, int buttonDistance) {
		BackgroundPanel panel = new BackgroundPanel(Path.IMAGE_BACKGROUND_BOTTOM_PANEL);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createRigidArea(new Dimension(100, 120)));
		
		
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
		subPanel.setBackground(new Color(0, 0, 0, 0));
		
		
		if(text != null) {
			for(String t: text) {
				JLabel userLabel = new JLabel(t);
				userLabel.setFont(new Font("Serif", Font.BOLD, textFontSize));
				userLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
				userLabel.setHorizontalAlignment(JLabel.CENTER);
				userLabel.setForeground(Color.WHITE);
		        subPanel.add(userLabel);
			}
		}
		

		subPanel.add(Box.createVerticalStrut(textButtonDistance)); 
		
		if(buttons != null) {
			for(JButton button: buttons) {
				button.setAlignmentX(Component.CENTER_ALIGNMENT); 
				button.setPreferredSize(new Dimension(100, 50)); 
	            button.setMaximumSize(new Dimension(500, 50));
	            button.setBackground(Color.GRAY);
	            button.setForeground(Color.WHITE); 
	            button.setFont(new Font("Serif", Font.BOLD, buttonFontSize));
	            button.setFocusPainted(false); 
				subPanel.add(button);
				subPanel.add(Box.createVerticalStrut(buttonDistance));
			}

		}
		
		subPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(subPanel);
		
		panel.add(Box.createRigidArea(new Dimension(100, 120)));
		panel.setBackground(Color.BLACK);
		return panel;
	
	}

}