package utils;

public enum Path {
	
	IMAGE_BACKGROUND_01("img/backgrounds/game_background_4.png"),
	IMAGE_BACKGROUND_02("img/backgrounds/game_background_14.png"),
	IMAGE_BACKGROUND_BOTTOM_PANEL("img/backgrounds/bottom_panel_background.png"),
	IMAGE_CAVE_PLAYER("img/bases/cave_03.png"),
    IMAGE_CAVE_ENEMY("img/bases/cave_04.png"),
    IMAGE_FORTRESS_PLAYER("img/bases/fortress_03.png"),
    IMAGE_FORTRESS_ENEMY("img/bases/fortress_04.png"),
    IMAGE_CAVE_TOWER_01("img/towers/gif/cave_tower_03.gif"),
    IMAGE_CAVE_TOWER_02("img/towers/gif/cave_tower_04.gif"),
    IMAGE_MAGIC_TOWER_01("img/towers/gif/magic_tower_04.gif"),
    IMAGE_MAGIC_TOWER_02("img/towers/gif/magic_tower_05.gif"),
    IMAGE_CAVE_TOWER_ROCK("img/towers/pics/cave_tower_rock_02.png"),
	IMAGE_NORMAL_LIZARD_PLAYER_WALK("img/lifeforms/lizard_01.gif"),
    IMAGE_NORMAL_LIZARD_PLAYER_ATTACK("img/lifeforms/lizard_02.gif"),
    IMAGE_NORMAL_LIZARD_PLAYER_DEATH("img/lifeforms/lizard_03.gif"),
    IMAGE_NORMAL_LIZARD_PLAYER_WAIT("img/lifeforms/lizard_04.gif"),
    IMAGE_NORMAL_LIZARD_ENEMY_WALK("img/lifeforms/lizard_05.gif"),
    IMAGE_NORMAL_LIZARD_ENEMY_ATTACK("img/lifeforms/lizard_06.gif"),
    IMAGE_NORMAL_LIZARD_ENEMY_DEATH("img/lifeforms/lizard_07.gif"),
    IMAGE_NORMAL_LIZARD_ENEMY_WAIT("img/lifeforms/lizard_08.gif"),
    IMAGE_INTERMEDIATE_LIZARD_PLAYER_WALK("img/lifeforms/lizard_09.gif"),
    IMAGE_INTERMEDIATE_LIZARD_PLAYER_ATTACK("img/lifeforms/lizard_10.gif"),
    IMAGE_INTERMEDIATE_LIZARD_PLAYER_DEATH("img/lifeforms/lizard_11.gif"),
    IMAGE_INTERMEDIATE_LIZARD_PLAYER_WAIT("img/lifeforms/lizard_12.gif"),
    IMAGE_INTERMEDIATE_LIZARD_ENEMY_WALK("img/lifeforms/lizard_13.gif"),
    IMAGE_INTERMEDIATE_LIZARD_ENEMY_ATTACK("img/lifeforms/lizard_14.gif"),
    IMAGE_INTERMEDIATE_LIZARD_ENEMY_DEATH("img/lifeforms/lizard_15.gif"),
    IMAGE_INTERMEDIATE_LIZARD_ENEMY_WAIT("img/lifeforms/lizard_16.gif"),
    IMAGE_NORMAL_BEAR_PLAYER_WALK("img/lifeforms/bear_01.gif"),
    IMAGE_NORMAL_BEAR_PLAYER_ATTACK("img/lifeforms/bear_02.gif"),
    IMAGE_NORMAL_BEAR_PLAYER_DEATH("img/lifeforms/bear_03.gif"),
    IMAGE_NORMAL_BEAR_PLAYER_WAIT("img/lifeforms/bear_04.gif"),
    IMAGE_NORMAL_BEAR_ENEMY_WALK("img/lifeforms/bear_05.gif"),
    IMAGE_NORMAL_BEAR_ENEMY_ATTACK("img/lifeforms/bear_06.gif"),
    IMAGE_NORMAL_BEAR_ENEMY_DEATH("img/lifeforms/bear_07.gif"),
    IMAGE_NORMAL_BEAR_ENEMY_WAIT("img/lifeforms/bear_08.gif"),
    IMAGE_ICON_NORMAL_STONE_TOWER("img/towers_icons/normal_stone_tower.png"),
    IMAGE_ICON_NORMAL_MAGIC_TOWER("img/towers_icons/normal_magic_tower.png"),
    IMAGE_ICON_NORMAL_STONE_TOWER_REMOVE("img/towers_icons/normal_stone_tower_remove.png"),
    IMAGE_ICON_NORMAL_LIZARD("img/lifeforms_icons/normal_lizard.png"),
    IMAGE_ICON_INTERMEDIATE_LIZARD("img/lifeforms_icons/intermediate_lizard.png"),
    IMAGE_ICON_NORMAL_BEAR("img/lifeforms_icons/normal_bear.png"),
	MAIN_BACKGROUND_MUSIC("audio/GloriousMorning2.wav"),
	DANGER_BACKGROUND_MUSIC("audio/Danger.wav"),
	LIZARD_ATTACK_SOUND("audio/LizardAttack.wav"),
	LIZARD_SPEAK_SOUND("audio/LizardSpeak.wav"),
	BEAR_ATTACK_SOUND("audio/BearAttack.wav");
	
	private String name;
	
	Path(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}


}
