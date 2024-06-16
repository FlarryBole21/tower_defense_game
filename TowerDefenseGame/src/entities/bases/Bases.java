package entities.bases;

import ui.GamePanel;

public enum Bases {
	
	FRIENDLY_CAVE(new Cave(0,GamePanel.SCREENSIZE.height-600,170,600,1000,true)),
	ENEMY_CAVE(new Cave(GamePanel.SCREENSIZE.width-170,GamePanel.SCREENSIZE.height-600,170,600,1000,false)),
	FRIENDLY_FORTRESS(new Fortress(0,GamePanel.SCREENSIZE.height-600,170,600,4000,true)),
	ENEMY_FORTRESS(new Fortress(GamePanel.SCREENSIZE.width-170,GamePanel.SCREENSIZE.height-600,170,600,4000,false));
	
	
	private Base base;

	Bases(Base base){
		this.base=base;
		
	}

	public Base getBase() {
		return base;
	}



}
