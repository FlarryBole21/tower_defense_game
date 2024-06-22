package ui.setter;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import game.GamePanel;
import game.Main;
import ui.BackgroundPanel;
import utils.Path;

public abstract class PanelSetter {
	
	public static GamePanel setGamePanel(JFrame frame, CardLayout layout, JLabel label,LinkedList<JButton> imageButtons) throws IOException {
		GamePanel panel = new GamePanel(170,200,frame,layout,label,imageButtons);

		BackgroundPanel bottomPanel = new BackgroundPanel(Path.IMAGE_BACKGROUND_BOTTOM_PANEL);
		//JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.setBackground(Color.BLACK);
		bottomPanel.setName("Bottom Panel");
        //add(bottomPanel, BorderLayout.SOUTH);
		

		for(JButton imageButton: imageButtons) {
			coinLabelSetting(imageButton, bottomPanel);
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
	
	
	public static void coinLabelSetting(JButton button, JPanel subPanel) throws IOException {
		JPanel bottomPanelPanel = new JPanel();
		bottomPanelPanel.setLayout(new BoxLayout(bottomPanelPanel, BoxLayout.Y_AXIS));
	
		bottomPanelPanel.add(button); 
		JPanel coinMainLabel = new JPanel();
		coinMainLabel.setLayout(new BoxLayout(coinMainLabel, BoxLayout.Y_AXIS));
		String coinValue = ""+button.getClientProperty("coinValue");
		JLabel coinLabel = new JLabel(coinValue);
		coinLabel.setForeground(Color.WHITE);
		BufferedImage image = ImageIO.read(new File(Path.COIN_IMG.getName()));
		int width = 12; // Desired width of the scaled image
        int height = 15; // Desired height of the scaled image
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // Create ImageIcon from scaled image
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
		coinLabel.setIcon(scaledIcon);
		int topPadding = 7;
        int bottomPadding = 5;
        coinLabel.setBorder(BorderFactory.createEmptyBorder(topPadding, 0, bottomPadding, 0));
        coinMainLabel.add(coinLabel);
		int leftPadding=18;

		if(coinValue.length() > 1) {
			for(int i = 1; i < coinValue.length(); i++) {
				if(leftPadding != 0) {
					leftPadding-=4;
				}
			}
		}
		
		
	    coinMainLabel.setBorder(BorderFactory.createEmptyBorder(0, leftPadding, 0, 0));
		coinMainLabel.setBackground(Color.BLACK);
	
		bottomPanelPanel.add(coinMainLabel);
		bottomPanelPanel.setBackground(Color.BLACK);
		
		subPanel.add(bottomPanelPanel);
	}

}
