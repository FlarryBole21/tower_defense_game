package entities.towers;

import entities.livingbeings.Lizard;
import entities.livingbeings.NormalLizard;
import projectiles.Projectiles;
import projectiles.StoneProjectile;
import utils.Path;

public abstract class StoneTower extends Tower{

	private static final long serialVersionUID = 1L;

	public StoneTower(int xPos, int yPos, int width, int height, int health, int rangeShot,int spawnDelay) {
		super(xPos, yPos, width, height, health, rangeShot,spawnDelay);
			
	}
	
	public void setProjectile() {

		if(getProjectiles().size() == 0) {
			Projectiles projectile = Projectiles.STONE_PROJECTILE;
			StoneProjectile newProjectile = new StoneProjectile(projectile.getxPos(),projectile.getyPos(),
					projectile.getWidth(),projectile.getHeigth(),projectile.getAttack(),this);
			newProjectile.resetState(projectile);
			super.addProjectile(newProjectile);
		}
		
		
	}

}
