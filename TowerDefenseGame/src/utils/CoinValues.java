package utils;

public enum CoinValues {
	
	START_COIN_VALUE(175),
	NORMAL_LIZARD(15),
	INTERMEDIATE_LIZARD(0),
	NORMAL_BEAR(0),
	NORMAL_LIZARD_LOOT(20),
	INTERMEDIATE_LIZARD_LOOT(95),
	NORMAL_BEAR_LOOT(180),
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
