package ui.setter;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
  
public abstract class MenuBarSetter {
	
	
	public static JMenuBar setMenuBar(Runnable runnable,String name, String option) {
		JMenuBar mb = new JMenuBar();
		
		JMenu menu = new JMenu(name);
		JMenuItem mi = new JMenuItem(option);
		mi.addActionListener( (evt) -> {
			runnable.run();
		});
		
		menu.add(mi);
		
		mb.add(menu);
		
		return mb;
	}

}
