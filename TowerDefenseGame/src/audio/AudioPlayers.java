package audio;

import utils.Path;

public enum AudioPlayers {
	
	BACKGROUND_PLAYER(new AudioPlayer(Path.MAIN_BACKGROUND_MUSIC.getName(),true,true,true)),
	DANGER_PLAYER(new AudioPlayer(Path.DANGER_BACKGROUND_MUSIC.getName(),false,true,false)),
	LIZARD_ATTACK_PLAYER(new AudioPlayer(Path.LIZARD_ATTACK_SOUND.getName(),false,true,false)),
	LIZARD_SPEAK_PLAYER( new AudioPlayer(Path.LIZARD_SPEAK_SOUND.getName(),false,false,false)),
	BEAR_ATTACK_PLAYER(new AudioPlayer(Path.BEAR_ATTACK_SOUND.getName(),false,true,false));
	
	private AudioPlayer player;
	
	AudioPlayers(AudioPlayer player){
		this.player=player;
		
	}

	public AudioPlayer getPlayer() {
		return player;
	}

	public void setPlayer(AudioPlayer player) {
		this.player = player;
	}
	
	

}
