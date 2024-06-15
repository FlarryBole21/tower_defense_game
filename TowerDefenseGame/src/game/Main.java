package game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import audio.AudioPlayer;
import ui.GamePanel;
import ui.PanelSetter;
import utils.Path;


public class Main {
	
	private JFrame frame;
	public final static AudioPlayer BACKGROUND_PLAYER=new AudioPlayer(Path.MAIN_BACKGROUND_MUSIC.getName(),true,true);
	public final static AudioPlayer LIZARD_ATTACK_PLAYER= new AudioPlayer(Path.LIZARD_ATTACK_SOUND.getName(),false,true);

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
					window.startAudio(BACKGROUND_PLAYER);
					window.startAudio(LIZARD_ATTACK_PLAYER);
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
		JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        //label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 70));
        label.setForeground(Color.WHITE);
        label.setOpaque(true);
        label.setBackground(Color.BLACK);
        
        int padding = 20;
        label.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        GamePanel gamePanel = new GamePanel(170,600,100,20,frame,label);
        gamePanel.add(label, BorderLayout.PAGE_END);
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
                if (BACKGROUND_PLAYER != null) {
                	BACKGROUND_PLAYER.stop();
                    
                }
                
                if (LIZARD_ATTACK_PLAYER != null) {
                	LIZARD_ATTACK_PLAYER.stop();
                    
                }
            }
        });
	
	}


	public void startAudio(AudioPlayer player) {
        new Thread(new Runnable() {
            @Override
            public void run() {
            	if(player.isPlay()) {
            		player.play();
    	        }
            }
        }).start();
        
    }
	
	

}
