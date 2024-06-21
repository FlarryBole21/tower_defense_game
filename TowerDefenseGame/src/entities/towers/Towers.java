package entities.towers;

public enum Towers {
	
	NORMAL_STONE_TOWER_01(new NormalStoneTower(100,600,100,
    		100,1000,900,2000)),
	NORMAL_STONE_TOWER_02(new NormalStoneTower(300,600,100,
    		100,1000,900,2500)),
	NORMAL_STONE_TOWER_03(new NormalStoneTower(500,600,100,
    		100,1000,900,3000));
	
	
	private Tower tower;
	
	
	Towers(Tower tower) {
		this.tower=tower;
		
	}


	public Tower getTower() {
		return tower;
	}


}
