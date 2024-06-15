package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public abstract class LabelSetter {
	
	
	public static JLabel setLabel(int allignment,String fontStyle, int fontWeight, 
			int fontSize,Color fontColor,Color backgroundColor,int padding) {
		JLabel label = new JLabel();
        label.setHorizontalAlignment(allignment);
        //label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(fontStyle, fontWeight, fontSize));
        label.setForeground(fontColor);
        label.setOpaque(true);
        label.setBackground(backgroundColor);
        
      
        label.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        return label;
	}

}
