package ui;

import game.GamePanel;

public abstract class LifeBar {
	
	private GamePanel panel;
	private int lifeBarWidthFriend;
    private int lifeBarWidthEnemy;
    private int lifeBarHeight;
    private int lifeBarX;
    private int lifeBarY;
    
	public LifeBar(GamePanel panel,int lifeBarWidthFriend, int lifeBarWidthEnemy, 
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



	public int getLifeBarWidthFriend() {
		return lifeBarWidthFriend;
	}

	public void setLifeBarWidthFriend(int lifeBarWidthFriend) {
		this.lifeBarWidthFriend = lifeBarWidthFriend;
	}

	public int getLifeBarWidthEnemy() {
		return lifeBarWidthEnemy;
	}

	public void setLifeBarWidthEnemy(int lifeBarWidthEnemy) {
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
