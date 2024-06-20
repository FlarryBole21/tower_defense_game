package entities.bases;

public enum Towers {
	
	FRIENDLY_NORMAL_STONE_TOWER_01(new NormalStoneTower(100,600,100,
    		100,1000,true)),
	FRIENDLY_NORMAL_STONE_TOWER_02(new NormalStoneTower(300,600,100,
    		100,1000,true));
	
	
	private Tower tower;
	
	
	Towers(Tower tower) {
		this.tower=tower;
		
	}


	public Tower getTower() {
		return tower;
	}


}
