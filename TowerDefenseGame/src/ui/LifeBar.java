package ui;

import game.GamePanel;

public abstract class LifeBar {
	
	private GamePanel panel;
	private double lifeBarWidthFriend;
    private double lifeBarWidthEnemy;
    private int lifeBarHeight;
    private int lifeBarX;
    private int lifeBarY;
    
	public LifeBar(GamePanel panel,double lifeBarWidthFriend, double lifeBarWidthEnemy, 
			int lifeBarHeight, int lifeBarX, int lifeBarY) {
		this.panel=panel;
		this.lifeBarWidthFriend = lifeBarWidthFriend;
		this.lifeBarWidthEnemy = lifeBarWidthEnemy;
		this.lifeBarHeight = lifeBarHeight;
		this.lifeBarX = lifeBarX;
		this.lifeBarY = lifeBarY;
	}
	
	

	public GamePanel getPanel() {
		return panel;
	}



	public double getLifeBarWidthFriend() {
		return lifeBarWidthFriend;
	}

	public void setLifeBarWidthFriend(double lifeBarWidthFriend) {
		this.lifeBarWidthFriend = lifeBarWidthFriend;
	}

	public double getLifeBarWidthEnemy() {
		return lifeBarWidthEnemy;
	}

	public void setLifeBarWidthEnemy(double lifeBarWidthEnemy) {
		this.lifeBarWidthEnemy = lifeBarWidthEnemy;
	}

	public int getLifeBarHeight() {
		return lifeBarHeight;
	}


	public int getLifeBarX() {
		return lifeBarX;
	}


	public int getLifeBarY() {
		return lifeBarY;
	}

	
}
