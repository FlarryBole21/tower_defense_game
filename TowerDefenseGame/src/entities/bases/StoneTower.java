package entities.bases;

import entities.livingbeings.Lizard;
import entities.livingbeings.NormalLizard;
import utils.Path;

public abstract class StoneTower extends Tower{

	private static final long serialVersionUID = 1L;

	public StoneTower(int xPos, int yPos, int width, int height, int health, boolean friendly) {
		super(xPos, yPos, width, height, health, friendly);
			
	}
	
	public void setProjectile() {

		if(getProjectiles().size() == 0) {
			Projectiles projectile = Projectiles.FRIENDLY_STONE_PROJECTILE;
			StoneProjectile newProjectile = new StoneProjectile(projectile.getxPos(),projectile.getyPos(),
					projectile.getWidth(),projectile.getHeigth(),projectile.getAttack(),
					projectile.getHealth(),projectile.isFriendly(),this);
			newProjectile.resetState(projectile);
			super.addProjectile(newProjectile);
		}
		
		
	}

}
