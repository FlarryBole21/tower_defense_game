package game;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.LinkedList;

import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import audio.AudioPlayer;
import ui.setter.ButtonSetter;
import ui.setter.FrameSetter;
import ui.setter.LabelSetter;
import ui.setter.MenuBarSetter;
import ui.setter.PanelSetter;
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
	private static boolean FIRSTTIME;

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
		
		LinkedList<JButton> imageIconButtons = new LinkedList<>();
		
		resetImageIcons(imageIconButtons);

        
		JLabel label = LabelSetter.setLabel(SwingConstants.CENTER,"Serif",Font.BOLD,70,Color.WHITE,new Color(0, 0, 0, 0),20);
        GamePanel gamePanel = PanelSetter.setGamePanel(frame,cardLayout,label,imageIconButtons);
        
        
        Runnable startGameRunnable = ()->{
        	startGame(gamePanel, cardLayout);
        };
        
        Runnable switchToMainMenuRunnable = ()->{
        	cardLayout.show(MAINPANEL, "MenuPanel");
        };
        
        
        Runnable closeRunnable = ()->{
        	frame.dispose();
			System.exit(0);
        };
        
        
        JButton startButton = ButtonSetter.setButton("Spiel starten", startGameRunnable);
        JButton quitLosingTryAgainButton = ButtonSetter.setButton("Nochmal versuchen", startGameRunnable);
        JButton quitWinningTryAgainButton = ButtonSetter.setButton("Nochmal versuchen", startGameRunnable);
        JButton quitLosingBackToMenuButton = ButtonSetter.setButton("Zurück zum Menü", switchToMainMenuRunnable);
        JButton quitWinningBackToMenuButton = ButtonSetter.setButton("Zurück zum Menü", switchToMainMenuRunnable);
        
        
        LinkedList<String> menuText = new LinkedList<>(Arrays.asList("War Of Age"));
        LinkedList<String> losingText = new LinkedList<>(
        		Arrays.asList("Deine Basis wurde zerstört.","Du hast verloren!"));
        LinkedList<String> winningText = new LinkedList<>(
        		Arrays.asList("Die Basis des Gegners wurde zerstört.","Du hast gewonnen!"));
        
        LinkedList<JButton> menuButtons = new LinkedList<>(Arrays.asList(startButton));
        LinkedList<JButton> losingButtons = new LinkedList<>(
        		Arrays.asList(quitLosingTryAgainButton,quitLosingBackToMenuButton));
        LinkedList<JButton> winningButtons = new LinkedList<>(
        		Arrays.asList(quitWinningTryAgainButton,quitWinningBackToMenuButton));
        
        JPanel menuPanel = PanelSetter.setPanel("MenuPanel",menuText, menuButtons,100,18,10,10);
        JPanel losingPanel = PanelSetter.setPanel("LosingPanel", losingText,losingButtons,100,18,10,10);
        JPanel winningPanel = PanelSetter.setPanel("WinningPanel", winningText, winningButtons,100,18,10,10);
        
        
        MAINPANEL.add(menuPanel ,"MenuPanel");
        MAINPANEL.add(gamePanel, "GamePanel");
        MAINPANEL.add(losingPanel, "LosingPanel");
        MAINPANEL.add(winningPanel, "WinningPanel");
        
        JMenuBar mb = MenuBarSetter.setMenuBar(closeRunnable,"Menü","Exit");
        
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if (gd.isFullScreenSupported()) {
            frame.setUndecorated(true);
            gd.setFullScreenWindow(frame);
        } else {
            System.err.println("Full screen not supported");
            frame.setSize(800, 600); 
            frame.setVisible(true);
        }
		
		FrameSetter.setFrame(frame,mb);
		
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	stopAudio();
            	System.exit(0);
            }
        });
	
	}
	
	
	public static void resetImageIcons(LinkedList<JButton> imageIconButtons) {
		JButton normalStoneTowerImageButton = ButtonSetter.setImageIconButton
				(Path.IMAGE_ICON_NORMAL_STONE_TOWER, "NormalStoneTowerButton", 64, 64);
		JButton normalMagicTowerImageButton = ButtonSetter.setImageIconButton
				(Path.IMAGE_ICON_NORMAL_MAGIC_TOWER, "NormalMagicTowerButton", 64, 64);
		JButton normalStoneTowerRemoveImageButton = ButtonSetter.setImageIconButton
				(Path.IMAGE_ICON_NORMAL_STONE_TOWER_REMOVE, "NormalStoneTowerRemoveButton", 64, 64);
		JButton normalLizardImageButton = ButtonSetter.setImageIconButton
				(Path.IMAGE_ICON_NORMAL_LIZARD, "NormalLizardButton", 64, 64);
		JButton intermediateLizardImageButton = ButtonSetter.setImageIconButton
				(Path.IMAGE_ICON_INTERMEDIATE_LIZARD, "IntermediateLizardButton", 64, 64);
		
		imageIconButtons.add(normalStoneTowerImageButton);
		imageIconButtons.add(normalMagicTowerImageButton);
		imageIconButtons.add(normalStoneTowerRemoveImageButton);
		normalStoneTowerRemoveImageButton.setEnabled(false);
        imageIconButtons.add(normalLizardImageButton);
        imageIconButtons.add(intermediateLizardImageButton);
	}
	
	
	
	
	public static void startGame(GamePanel gamePanel, CardLayout cardLayout) {
		if(FIRSTTIME) {
			WINDOW.startAudio(BACKGROUND_PLAYER);
			FIRSTTIME=false;
		}else {
			BACKGROUND_PLAYER.resetFrame();
			BACKGROUND_PLAYER.play();
		}
		
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
