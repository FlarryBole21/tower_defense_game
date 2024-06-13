package game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import audio.AudioPlayer;
import ui.GamePanel;
import ui.PanelSetter;


public class Main {
	
	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
//		AudioPlayer player = new AudioPlayer();
//		player.play();

	}
	
	
	public Main() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		GamePanel gamePanel = new GamePanel(170,600,100,20);
		PanelSetter.setPanel(frame,gamePanel,Color.decode("#66CED6"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack(); 
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		gamePanel.requestFocus();
	
	}
	
	

}
