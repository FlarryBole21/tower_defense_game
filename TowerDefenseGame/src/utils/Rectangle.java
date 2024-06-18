package utils;

import java.awt.Color;
import java.awt.Graphics;

import utils.interfaces.Drawable;
import utils.interfaces.Moveable;

public class Rectangle implements Drawable, Moveable{
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	
	public Rectangle(int x, int y, int width, int height) {
		this(x,y,width,height, Color.BLACK);
	}
	
	
	public Rectangle(int x, int y, int width, int height, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	

	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	@Override
	public void move(int x, int y) {
		this.x += x;
		this.y += y;
		
	}

	@Override
	public void draw(Graphics g) {
		Color oldColor = g.getColor();
		g.setColor(color);
		g.fillRect(this.x, this.y, this.width, this.height);
		g.setColor(oldColor);
		
	}

}
