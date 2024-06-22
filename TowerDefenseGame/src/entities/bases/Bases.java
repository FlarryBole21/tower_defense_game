package entities.bases;

import game.GamePanel;

public enum Bases {
	
	FRIENDLY_CAVE(new Cave(0,GamePanel.SCREENSIZE.height-600,170,200,1000,true)),
	ENEMY_CAVE(new Cave(GamePanel.SCREENSIZE.width-170,GamePanel.SCREENSIZE.height-600,170,200,1000,false)),
	FRIENDLY_FORTRESS(new Fortress(0,GamePanel.SCREENSIZE.height-600,170,200,4000,true)),
	ENEMY_FORTRESS(new Fortress(GamePanel.SCREENSIZE.width-170,GamePanel.SCREENSIZE.height-600,170,200,4000,false));
	
	
	private Base base;
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private double health;
	private boolean friendly;

	Bases(Base base){
		this.base=base;
		this.xPos=base.getX();
		this.yPos=base.getY();
		this.width=base.getWidth();
		this.height=base.getHeight();
		this.health=base.getHealth();
		this.friendly=base.isFriendly();
		
	}

	public Base getBase() {
		return base;
	}

	public int getxPos() {
		return xPos;
	}
	
	public int getyPos() {
		return yPos;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}


	public double getHealth() {
		return health;
	}

	public boolean isFriendly() {
		return friendly;
	}


}
