package utils;

public enum Path {
	
	IMAGE_BACKGROUND_01("file:///D:/Karriere/fi23a/Aufgaben/Java/Code/GIT/"
			+ "tower_defense_game/TowerDefenseGame/img/game_background_4.png"),
	IMAGE_CAVE_PLAYER("file:///D:/Karriere/fi23a/Aufgaben/Java/Code/GIT/"
			+ "tower_defense_game/TowerDefenseGame/img/cave_03.png"),
	IMAGE_CAVE_ENEMY("file:///D:/Karriere/fi23a/Aufgaben/Java/Code/GIT/"
			+ "tower_defense_game/TowerDefenseGame/img/cave_04.png"),
	IMAGE_CAVE_TOWER_PLAYER("file:///D:/Karriere/fi23a/Aufgaben/Java/Code/GIT/"
			+ "tower_defense_game/TowerDefenseGame/img/cave_tower_01.png"),
	IMAGE_CAVE_TOWER_ENEMY("file:///D:/Karriere/fi23a/Aufgaben/Java/Code/GIT/"
			+ "tower_defense_game/TowerDefenseGame/img/cave_tower_02.png"),
	IMAGE_LIZARD_PLAYER_WALK("file:///D:/Karriere/fi23a/Aufgaben/Java/Code/GIT/"
			+ "tower_defense_game/TowerDefenseGame/img/lifeforms/lizard_01.gif"),
	IMAGE_LIZARD_PLAYER_ATTACK("file:///D:/Karriere/fi23a/Aufgaben/Java/Code/GIT/"
			+ "tower_defense_game/TowerDefenseGame/img/lifeforms/lizard_02.gif"),
	IMAGE_LIZARD_PLAYER_DEATH("file:///D:/Karriere/fi23a/Aufgaben/Java/Code/GIT/"
			+ "tower_defense_game/TowerDefenseGame/img/lifeforms/lizard_03.gif"),
	IMAGE_LIZARD_PLAYER_WAIT("file:///D:/Karriere/fi23a/Aufgaben/Java/Code/GIT/"
			+ "tower_defense_game/TowerDefenseGame/img/lifeforms/lizard_04.gif"),
	IMAGE_LIZARD_ENEMY_WALK("file:///D:/Karriere/fi23a/Aufgaben/Java/Code/GIT/"
			+ "tower_defense_game/TowerDefenseGame/img/lifeforms/lizard_05.gif"),
	IMAGE_LIZARD_ENEMY_ATTACK("file:///D:/Karriere/fi23a/Aufgaben/Java/Code/GIT/"
			+ "tower_defense_game/TowerDefenseGame/img/lifeforms/lizard_06.gif"),
	IMAGE_LIZARD_ENEMY_DEATH("file:///D:/Karriere/fi23a/Aufgaben/Java/Code/GIT/"
			+ "tower_defense_game/TowerDefenseGame/img/lifeforms/lizard_07.gif"),
	IMAGE_LIZARD_ENEMY_WAIT("file:///D:/Karriere/fi23a/Aufgaben/Java/Code/GIT/"
			+ "tower_defense_game/TowerDefenseGame/img/lifeforms/lizard_08.gif");
	
	private String name;
	
	Path(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}


}
