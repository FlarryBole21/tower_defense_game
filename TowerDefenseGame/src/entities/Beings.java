package entities;

import ui.GamePanel;

public enum Beings {
	
	FRIENDLY_LIZARD(new Lizard(100, 550, 100, 100, 10, 500, true)),
	ENEMY_LIZARD(new Lizard(GamePanel.SCREENSIZE.width-Bases.ENEMY_CAVE.getBase().getRect().getWidth()-50,
			550, 100, 100, 10, 1000, false));
	
	
	private LivingBeing being;
	private int xPos;
	private int yPos;
	private int width;
	private int heigth;
	private int attack;
	private int health;
	private boolean friendly;
	
	Beings(LivingBeing being){
		this.being=being;
		this.xPos=being.getRect().getX();
		this.yPos=being.getRect().getY();
		this.width=being.getRect().getWidth();
		this.heigth=being.getRect().getHeight();
		this.attack=being.getAttack();
		this.health=being.getHealth();
		this.friendly=being.isFriendly();

	}
	
	public int getAttack() {
		return attack;
	}

	public int getHealth() {
		return health;
	}


	public boolean isFriendly() {
		return friendly;
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


	public int getHeigth() {
		return heigth;
	}


	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}



	public LivingBeing getBeing() {
		return being;
	}

	public void setBeing(LivingBeing being) {
		this.being = being;
	}
	
	

}
