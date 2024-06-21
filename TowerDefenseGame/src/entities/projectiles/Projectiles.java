package entities.projectiles;

import entities.towers.Tower;
import entities.towers.Towers;

public enum Projectiles {
	
	STONE_PROJECTILE(new StoneProjectile(300, 100, 100, 100, 50,200, true,
			Towers.NORMAL_STONE_TOWER_01.getTower()));
	
	private Projectile projectile;
	private int xPos;
	private int yPos;
	private int width;
	private int heigth;
	private int attack;
	private int health;
	private boolean friendly;
	private Tower tower;
	
	
	Projectiles(Projectile projectile) {
		this.projectile=projectile;
		this.xPos=projectile.getRect().getX();
		this.yPos=projectile.getRect().getY();
		this.width=projectile.getRect().getWidth();
		this.heigth=projectile.getRect().getHeight();
		this.attack=projectile.getAttack();
		this.health=projectile.getHealth();
		this.tower=projectile.getTower();
		this.friendly=projectile.isFriendly();
		
	}
	
	public Tower getTower() {
		return tower;
	}

	public Projectile getProjectile() {
		return projectile;
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


	public int getAttack() {
		return attack;
	}


	public int getHealth() {
		return health;
	}


	public boolean isFriendly() {
		return friendly;
	}


}
