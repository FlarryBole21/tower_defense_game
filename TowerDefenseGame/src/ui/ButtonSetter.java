package ui;

import javax.swing.JButton;

import game.Main;

public abstract class ButtonSetter {
	
	public static JButton setButton(String text,Runnable runnable) {
		JButton button = new JButton();
		button.setText(text);
		button.addActionListener(e -> {
			runnable.run();
        });
		
		
		return button;
	}

}
