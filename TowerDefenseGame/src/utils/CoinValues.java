package utils;

public enum CoinValues {
	
	//Setzen aller Münzwerte für die Icons
	START_COIN_VALUE(175),
	NORMAL_LIZARD(15),
	INTERMEDIATE_LIZARD(100),
	ADVANCED_LIZARD(250),
	NORMAL_SPIDER(650),
	NORMAL_BEAR(1000),
	NORMAL_LIZARD_LOOT(20),
	INTERMEDIATE_LIZARD_LOOT(105),
	ADVANCED_LIZARD_LOOT(255),
	NORMAL_SPIDER_LOOT(605),
	NORMAL_BEAR_LOOT(1005),
	NORMAL_STONE_TOWER(150),
	NORMAL_MAGIC_TOWER(200);
	
	private int value;
	
	CoinValues(int value){
		this.value=value;
		
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	

}
