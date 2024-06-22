package entities.towers;

public abstract class MagicTower extends Tower {

	private static final long serialVersionUID = 1L;
	private double debuff;

	public MagicTower(int xPos, int yPos, int width, int height, int health, int rangeShot, int spawnDelay) {
		super(xPos, yPos, width, height, health, rangeShot, spawnDelay);
		debuff=0.5;
	}
	

	public double getDebuff() {
		return debuff;
	}


	public void setDebuff(double debuff) {
		this.debuff = debuff;
	}


	@Override
	public void setProjectile() {
		
	}

}
