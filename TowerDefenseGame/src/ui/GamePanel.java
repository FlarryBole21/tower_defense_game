package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.Timer;

import javax.swing.JPanel;

import entities.Base;
import entities.Cave;
import entities.LivingBeing;

public class GamePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private LinkedList<Base> bases;
    private LinkedList<LivingBeing> livingBeings;
    private Dimension screenSize; 
    
    public GamePanel(Dimension screenSize) {
    	this.screenSize=screenSize;
    	this.setPreferredSize(this.screenSize);
        bases = new LinkedList<>();
        addBases(new Cave(0,this.screenSize.height-500,500,500,1000,true));
        addBases(new Cave(this.screenSize.width-500,this.screenSize.height-500,500,500,1000,false));
   
        timer = new Timer(1200, this);
        timer.start();
    }
    
    public void addBases(Base base) {
    	if(bases.size()>1) {
    		for(Base el: bases) {
    			bases.remove(el);
    		}
    	}
    	
    	bases.add(base);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		updateGame(); 
        repaint(); 
		
	}
	
	
	private void updateGame() {
        for (Base base : bases) {
            base.update(); 
        }
    }
	
	
	@Override
	public void paint( Graphics g ) {
		super.paint(g);

		for (Base base : bases) {
            base.draw(g);
        }
	}
	
}
