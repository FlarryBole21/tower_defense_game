package utils;

public enum Path {
	
	IMAGE_BACKGROUND("file:///D:/Karriere/fi23a/Aufgaben/Java/Code/GIT/"
			+ "tower_defense_game/TowerDefenseGame/img/background_01.png"),
	IMAGE_CAVE_PLAYER("file:///D:/Karriere/fi23a/Aufgaben/Java/Code/GIT/"
			+ "tower_defense_game/TowerDefenseGame/img/cave_01.png"),
	IMAGE_CAVE_ENEMY("file:///D:/Karriere/fi23a/Aufgaben/Java/Code/GIT/"
			+ "tower_defense_game/TowerDefenseGame/img/cave_02.png"),
	IMAGE_CAVE_TOWER_PLAYER("file:///D:/Karriere/fi23a/Aufgaben/Java/Code/GIT/"
			+ "tower_defense_game/TowerDefenseGame/img/cave_tower_01.png"),
	IMAGE_CAVE_TOWER_ENEMY("file:///D:/Karriere/fi23a/Aufgaben/Java/Code/GIT/"
			+ "tower_defense_game/TowerDefenseGame/img/cave_tower_02.png");
	
	private String name;
	
	Path(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}


}
