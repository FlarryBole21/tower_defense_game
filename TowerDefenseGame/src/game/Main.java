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
import java.io.IOException;
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
import audio.AudioPlayers;
import ui.setter.ButtonSetter;
import ui.setter.FrameSetter;
import ui.setter.LabelSetter;
import ui.setter.MenuBarSetter;
import ui.setter.PanelSetter;
import utils.CoinValues;
import utils.Path;


public class Main {
	
	private JFrame frame;
	public final static JPanel MAINPANEL = new JPanel();
	public final static Main WINDOW = new Main();
	public final static AudioPlayer[] ATTACK_PLAYERS = new AudioPlayer[] {
			AudioPlayers.LIZARD_ATTACK_PLAYER.getPlayer(),
			AudioPlayers.LIZARD_SPEAK_PLAYER.getPlayer(),
			AudioPlayers.BEAR_ATTACK_PLAYER.getPlayer()
			};
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
		try {
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.getMessage();
		}
	}

	
	private void initialize() throws IOException {
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
        JButton quitWinningTryAgainButton2 = ButtonSetter.setButton("Nochmal versuchen", startGameRunnable);
        JButton quitLosingBackToMenuButton = ButtonSetter.setButton("Zurück zum Menü", switchToMainMenuRunnable);
        JButton quitWinningBackToMenuButton = ButtonSetter.setButton("Zurück zum Menü", switchToMainMenuRunnable);
        JButton quitWinningBackToMenuButton2 = ButtonSetter.setButton("Zurück zum Menü", switchToMainMenuRunnable);
        
        LinkedList<String> menuText = new LinkedList<>(Arrays.asList("War Of Age"));
        LinkedList<String> losingText = new LinkedList<>(
        		Arrays.asList("Deine Basis wurde zerstört.","Du hast verloren!"));
        LinkedList<String> winningText = new LinkedList<>(
        		Arrays.asList("Die Basis des Gegners wurde zerstört.","Du hast gewonnen!"));
        LinkedList<String> winningText2 = new LinkedList<>(
        		Arrays.asList("Du hast die Wellen des Gegners überlebt.","Du hast gewonnen!"));
        
        LinkedList<JButton> menuButtons = new LinkedList<>(Arrays.asList(startButton));
        LinkedList<JButton> losingButtons = new LinkedList<>(
        		Arrays.asList(quitLosingTryAgainButton,quitLosingBackToMenuButton));
        LinkedList<JButton> winningButtons = new LinkedList<>(
        		Arrays.asList(quitWinningTryAgainButton,quitWinningBackToMenuButton));
        LinkedList<JButton> winningButtons2 = new LinkedList<>(
        		Arrays.asList(quitWinningTryAgainButton2,quitWinningBackToMenuButton2));
        
        JPanel menuPanel = PanelSetter.setPanel("MenuPanel",menuText, menuButtons,100,18,10,10);
        JPanel losingPanel = PanelSetter.setPanel("LosingPanel", losingText,losingButtons,100,18,10,10);
        JPanel winningPanel = PanelSetter.setPanel("WinningPanel", winningText, winningButtons,100,18,10,10);
        JPanel winningPanel2 = PanelSetter.setPanel("WinningPanel2", winningText2, winningButtons2,100,18,10,10);
        
        
        MAINPANEL.add(menuPanel ,"MenuPanel");
        MAINPANEL.add(gamePanel, "GamePanel");
        MAINPANEL.add(losingPanel, "LosingPanel");
        MAINPANEL.add(winningPanel, "WinningPanel");
        MAINPANEL.add(winningPanel2, "WinningPanel2");
        
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
				(Path.IMAGE_ICON_NORMAL_STONE_TOWER, "NormalStoneTowerButton", CoinValues.NORMAL_STONE_TOWER.getValue(),64, 64);
		JButton normalMagicTowerImageButton = ButtonSetter.setImageIconButton
				(Path.IMAGE_ICON_NORMAL_MAGIC_TOWER, "NormalMagicTowerButton", CoinValues.NORMAL_MAGIC_TOWER.getValue(),64, 64);
		JButton normalStoneTowerRemoveImageButton = ButtonSetter.setImageIconButton
				(Path.IMAGE_ICON_NORMAL_STONE_TOWER_REMOVE, "NormalStoneTowerRemoveButton", 0,64, 64);
		JButton normalLizardImageButton = ButtonSetter.setImageIconButton
				(Path.IMAGE_ICON_NORMAL_LIZARD, "NormalLizardButton", CoinValues.NORMAL_LIZARD.getValue(),64, 64);
		JButton intermediateLizardImageButton = ButtonSetter.setImageIconButton
				(Path.IMAGE_ICON_INTERMEDIATE_LIZARD, "IntermediateLizardButton",CoinValues.INTERMEDIATE_LIZARD.getValue(), 64, 64);
		
		imageIconButtons.add(normalStoneTowerImageButton);
		imageIconButtons.add(normalMagicTowerImageButton);
		imageIconButtons.add(normalStoneTowerRemoveImageButton);
		normalStoneTowerRemoveImageButton.setEnabled(false);
        imageIconButtons.add(normalLizardImageButton);
        imageIconButtons.add(intermediateLizardImageButton);
	}
	
	
	
	
	public static void startGame(GamePanel gamePanel, CardLayout cardLayout) {
		if(FIRSTTIME) {
			WINDOW.startAudio(AudioPlayers.BACKGROUND_PLAYER.getPlayer());
			FIRSTTIME=false;
		}else {
			AudioPlayers.BACKGROUND_PLAYER.getPlayer().resetFrame();
			AudioPlayers.BACKGROUND_PLAYER.getPlayer().play();
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
		for(AudioPlayers players: AudioPlayers.values()) {
			AudioPlayer player = players.getPlayer();

    		if(player != null) {
    			player.stop();            		
    		}
    	}
	}
	
	

}
