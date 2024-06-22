package ui;

public enum ImageIconCoinValues {
	
	NORMAL_LIZARD(15),
	INTERMEDIATE_LIZARD(50),
	NORMAL_BEAR(50),
	NORMAL_STONE_TOWER(150),
	NORMAL_MAGIC_TOWER(200);
	
	private int value;
	
	ImageIconCoinValues(int value){
		this.value=value;
		
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	

}
