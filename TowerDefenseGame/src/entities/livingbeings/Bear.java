package entities.livingbeings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import audio.AudioPlayer;
import game.Main;
import ui.GamePanel;
import utils.Path;

public abstract class Bear extends LivingBeing{

	private static final long serialVersionUID = 1L;

	public Bear(int xPos, int yPos, int width, int height, int attack, int health,boolean friendly) {
		super(xPos, yPos, width,height, attack, health,friendly);
		super.addSpeed(2);
		super.addAttackingAudio(Main.BEAR_ATTACK_PLAYER);
		super.setAttackingDistance(20);
		super.setWaitingDistance(110);
		super.setFrameDelay(100);
		super.setTotalDeathFrames(6);
	}
	

}
