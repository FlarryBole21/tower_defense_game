package game;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.LinkedList;

import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import audio.AudioPlayer;
import ui.ButtonSetter;
import ui.FrameSetter;
import ui.GamePanel;
import ui.LabelSetter;
import ui.PanelSetter;
import utils.Path;


public class Main {
	
	private JFrame frame;
	public final static JPanel MAINPANEL = new JPanel();
	public final static Main WINDOW = new Main();
	public final static AudioPlayer BACKGROUND_PLAYER=new AudioPlayer(Path.MAIN_BACKGROUND_MUSIC.getName(),true,true,true);
	public final static AudioPlayer DANGER_PLAYER=new AudioPlayer(Path.DANGER_BACKGROUND_MUSIC.getName(),false,true,false);
	public final static AudioPlayer LIZARD_ATTACK_PLAYER= new AudioPlayer(Path.LIZARD_ATTACK_SOUND.getName(),false,true,false);
	public final static AudioPlayer LIZARD_SPEAK_PLAYER= new AudioPlayer(Path.LIZARD_SPEAK_SOUND.getName(),false,false,false);
	public final static AudioPlayer BEAR_ATTACK_PLAYER= new AudioPlayer(Path.BEAR_ATTACK_SOUND.getName(),false,true,false);
	public final static AudioPlayer[] ATTACK_PLAYERS = new AudioPlayer[] {LIZARD_ATTACK_PLAYER,LIZARD_SPEAK_PLAYER,BEAR_ATTACK_PLAYER};
	public final static AudioPlayer[] AUDIO_FILES = new AudioPlayer[] {BACKGROUND_PLAYER,DANGER_PLAYER,LIZARD_ATTACK_PLAYER,LIZARD_SPEAK_PLAYER,
			BEAR_ATTACK_PLAYER};

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					WINDOW.frame.setVisible(true);
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
		
		MAINPANEL.setLayout(new CardLayout());
		CardLayout cardLayout = (CardLayout) MAINPANEL.getLayout();
		JLabel label = LabelSetter.setLabel(SwingConstants.CENTER,"Arial",Font.BOLD,70,Color.WHITE,Color.BLACK,20);
        GamePanel gamePanel = PanelSetter.setGamePanel(frame,label,cardLayout);
        
        
        Runnable startGameRunnable = ()->{
        	startGame(gamePanel, cardLayout);
        };
        
        Runnable switchToMainMenuRunnable = ()->{
        	cardLayout.show(MAINPANEL, "MenuPanel");
        };
        
        JButton startButton = ButtonSetter.setButton("Spiel starten", startGameRunnable);
        JButton quitLosingTryAgainButton = ButtonSetter.setButton("Nochmal versuchen", startGameRunnable);
        JButton quitWinningTryAgainButton = ButtonSetter.setButton("Nochmal versuchen", startGameRunnable);
        JButton quitLosingBackToMenuButton = ButtonSetter.setButton("Zurück zum Menü", switchToMainMenuRunnable);
        JButton quitWinningBackToMenuButton = ButtonSetter.setButton("Zurück zum Menü", switchToMainMenuRunnable);
        
        
        JPanel menuPanel = PanelSetter.setPanel("MenuPanel", new LinkedList<>(Arrays.asList(startButton)));
        JPanel losingPanel = PanelSetter.setPanel("LosingPanel", 
        		new LinkedList<>(Arrays.asList(quitLosingTryAgainButton,quitLosingBackToMenuButton)));
        JPanel winningPanel = PanelSetter.setPanel("WinningPanel", 
        		new LinkedList<>(Arrays.asList(quitWinningTryAgainButton,quitWinningBackToMenuButton)));
        
        
        MAINPANEL.add(menuPanel ,"MenuPanel");
        MAINPANEL.add(gamePanel, "GamePanel");
        MAINPANEL.add(losingPanel, "LosingPanel");
        MAINPANEL.add(winningPanel, "WinningPanel");
		FrameSetter.setFrame(frame,MAINPANEL);
		
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	stopAudio();
            }
        });
	
	}
	
	
	public static void startGame(GamePanel gamePanel, CardLayout cardLayout) {
		WINDOW.startAudio(BACKGROUND_PLAYER);
        gamePanel.setGameStart(true);
        gamePanel.startConfig();
        cardLayout.show(MAINPANEL, "GamePanel");
		
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
	
	
	public static void stopAudio() {
		for(AudioPlayer player: AUDIO_FILES) {
    		if(player != null) {
    			player.stop();            		
    		}
    	}
	}
	
	

}
