package entities;

import ui.GamePanel;

public enum Bases {
	
	FRIENDLY_CAVE(new Cave(0,GamePanel.SCREENSIZE.height-600,170,600,1000,true)),
	ENEMY_CAVE(new Cave(GamePanel.SCREENSIZE.width-170,GamePanel.SCREENSIZE.height-600,170,600,1000,false));
	
	
	private Base base;
	
	
	
	Bases(Base base){
		this.base=base;
		
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}
	
	

}
