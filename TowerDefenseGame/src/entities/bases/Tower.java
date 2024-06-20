package entities.bases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import entities.Entity;
import game.GamePanel;
import utils.Path;

public abstract class Tower extends Entity{

	private static final long serialVersionUID = 1L;
    private ImageIcon imageIcon;
    private String pathImage;
    private LinkedList<Projectile> projectiles;
    private boolean active;
    private Timer timer;
    //private ScheduledExecutorService scheduler;
    private int loadingDelay;
    private int spawnDelay;
    private boolean cancelled;

    {
        projectiles = new LinkedList<>();
    }

    public Tower(int xPos, int yPos, int width, int height, int health, boolean friendly) {
        super(xPos, yPos, width, height, health, friendly);
        active = true;
        loadingDelay = 700;
        spawnDelay = 2000;
        cancelled=true;
    }
    
    public void resetTower() {
    	active=false;
    	cancelled=false;
        //stopLoading();
        stopSpawning();
        projectiles.clear();
        //setActive(true);
        //scheduler=null;
        //scheduler = Executors.newScheduledThreadPool(1);
    }
    
    
    


//    public ScheduledExecutorService getScheduler() {
//		return scheduler;
//	}
//
//
//
//	public void setScheduler(ScheduledExecutorService scheduler) {
//		this.scheduler = scheduler;
//	}



	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

//	public void startLoading() {
//		active=true;
//        scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.schedule(new LoadingTask(), loadingDelay, TimeUnit.MILLISECONDS);
//    }
//
//    public void stopLoading() {
//        if (scheduler != null && !scheduler.isShutdown()) {
//            scheduler.shutdownNow();
//            scheduler=null;
//            //projectiles.clear();
//        }
//    }

//    private class LoadingTask implements Runnable {
//        @Override
//        public void run() {
//            startSpawning();
//            //stopSpawning();
//        }
//    }

	
	
	
    public void startSpawning() {
    	//stopSpawning(); 
    	 
    	if(timer == null || cancelled == true) {
    		timer = new Timer();
    		cancelled = false;
    	}
        
        active = true;
        
       
        timer.schedule(new SpawnTask(), spawnDelay);
        
      
        
  
    	
    }

    public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public void stopSpawning() {
    	
    	if(timer != null) {
    		timer.cancel();
    		cancelled=true;
    		timer = null;
    	}
    	
    	
        //stopLoading();
    }

    private class SpawnTask extends TimerTask {
        @Override
        public void run() {
            if (isActive()) {
            	setProjectile();
                //stopSpawning();
//                stopLoading();
//                startLoading();
            } else {
            	resetTower();
            }
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public void loadImage() {
        imageIcon = new ImageIcon(getPathImage());
    }

    public LinkedList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void addProjectile(Projectile projectile) {
        this.projectiles.add(projectile);
    }

    public abstract void setProjectile();

    @Override
    public void update(GamePanel panel) {
    	
    	if(panel.isGameStart()) {
    		//startLoading();
    		startSpawning();
    	}
 
      
    }

    @Override
    public void draw(Graphics g) {
        if (imageIcon != null) {
            imageIcon.paintIcon(null, g, getRect().getX(), getRect().getY());
        }
    }


}
