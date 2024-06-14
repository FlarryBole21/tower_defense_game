package utils;

public enum Path {
	
	IMAGE_BACKGROUND_01("img/game_background_4.png"),
	IMAGE_CAVE_PLAYER("img/cave_03.png"),
    IMAGE_CAVE_ENEMY("img/cave_04.png"),
    IMAGE_CAVE_TOWER_PLAYER("img/cave_tower_01.png"),
    IMAGE_CAVE_TOWER_ENEMY("img/cave_tower_02.png"),
	IMAGE_LIZARD_PLAYER_WALK("img/lifeforms/lizard_01.gif"),
    IMAGE_LIZARD_PLAYER_ATTACK("img/lifeforms/lizard_02.gif"),
    IMAGE_LIZARD_PLAYER_DEATH("img/lifeforms/lizard_03.gif"),
    IMAGE_LIZARD_PLAYER_WAIT("img/lifeforms/lizard_04.gif"),
    IMAGE_LIZARD_ENEMY_WALK("img/lifeforms/lizard_05.gif"),
    IMAGE_LIZARD_ENEMY_ATTACK("img/lifeforms/lizard_06.gif"),
    IMAGE_LIZARD_ENEMY_DEATH("img/lifeforms/lizard_07.gif"),
    IMAGE_LIZARD_ENEMY_WAIT("img/lifeforms/lizard_08.gif"),
	MAIN_BACKGROUND_MUSIC("audio/GloriousMorning2.wav");
	
	private String name;
	
	Path(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}


}
