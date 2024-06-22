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
import game.GamePanel;
import game.Main;
import utils.Path;

public abstract class Lizard extends LivingBeing{

	private static final long serialVersionUID = 1L;

	public Lizard(int xPos, int yPos, int width, int height, int attack, double health,boolean friendly) {
		super(xPos, yPos, width,height, attack, health,friendly);
		super.addSpeed(2);
		super.addAttackingAudio(Main.LIZARD_ATTACK_PLAYER);
		super.addAttackingAudio(Main.LIZARD_SPEAK_PLAYER);
		super.setAttackingDistance(20);
		super.setWaitingDistance(110);
		super.setFrameDelay(100);
		super.setTotalDeathFrames(6);
	}
	
	
}
