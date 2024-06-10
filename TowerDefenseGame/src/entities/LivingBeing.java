package entities;

public abstract class LivingBeing extends Entity{
	
	private int attack;

	public LivingBeing(int xPos, int yPos, int width, int height, int attack, int health,boolean friendly) {
		super(xPos, yPos, width, height, health,friendly);
		this.attack=attack;
	
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	

}
