package com.pabloGames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {

	protected double  x; 
	protected double  y;
	protected int  width;
	protected int height;
	
	private BufferedImage sprite;
	
	public Entity( int x , int y , int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width =  width;
		this.height =  height;
		this.sprite = sprite;
	}

	public int getX() {
		return (int)x;
	}

	public void setX(int newx) {
		this.x = x;
	}

	public int getY() {
		return (int)y;
	}

	public void setY(int newy) {
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
	
	
	public void render( Graphics g) {
		g.drawImage(sprite, this.getX(), this.getY(), null);
	}
	
	
	public void tick() {
		
	}
	
	
}
