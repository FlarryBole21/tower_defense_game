package entities.towers;

public abstract class MagicTower extends Tower {

	private static final long serialVersionUID = 1L;
	private double buff;

	public MagicTower(int xPos, int yPos, int width, int height, int health, int rangeShot, int spawnDelay) {
		super(xPos, yPos, width, height, health, rangeShot, spawnDelay);
	}
	

	public double getBuff() {
		return buff;
	}


	public void setBuff(double buff) {
		this.buff = buff;
	}


	@Override
	public void setProjectile() {
		
	}

}
