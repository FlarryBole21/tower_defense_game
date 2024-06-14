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
import utils.Path;


public class Main {
	
	private JFrame frame;
	private AudioPlayer player;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
					window.startAudio();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	public Main() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		GamePanel gamePanel = new GamePanel(170,600,100,20);
		PanelSetter.setPanel(frame,gamePanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack(); 
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		gamePanel.requestFocus();
		
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (player != null) {
                    player.stop();
                    
                }
            }
        });
	
	}
	
	
	public void startAudio() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                player = new AudioPlayer();
                player.play(Path.MAIN_BACKGROUND_MUSIC.getName(),true);
            }
        }).start();
    }
	
	

}
