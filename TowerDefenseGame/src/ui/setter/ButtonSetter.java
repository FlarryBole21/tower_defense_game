package ui.setter;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import game.Main;
import utils.Path;

public abstract class ButtonSetter {
	
	public static JButton setButton(String text,Runnable runnable) {
		JButton button = new JButton();
		button.setText(text);
		button.addActionListener(e -> {
			runnable.run();
        });
		
		
		return button;
	}
	
	
	public static JButton setImageIconButton(Path path,String name,int coinValue,int sizeX, int sizeY) {
		
		ImageIcon icon = new ImageIcon(path.getName());
		JButton button = new JButton(icon);
		button.setPreferredSize(new Dimension(sizeX,sizeY));
		button.setName(name);
		button.putClientProperty("coinValue", coinValue);
		
		return button;
	}

}
