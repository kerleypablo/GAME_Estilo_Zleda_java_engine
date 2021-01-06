package com.pabloGames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.pabloGames.Game;
import com.pabloGames.word.Camera;

public class Entity {

	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(4*16, 0, 16, 16);
	public static BufferedImage WAPON_EN = Game.spritesheet.getSprite(4*16, 16, 16, 16);
	public static BufferedImage ENERGY_EN = Game.spritesheet.getSprite(6*16, 0, 16, 16);
	public static BufferedImage ENEMY_EN = Game.spritesheet.getSprite(5*16, 16, 16, 16);
	
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
		this.x = newx;
	}

	public int getY() {
		return (int)y;
	}

	public void setY(int newy) {
		this.y = newy;
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
		g.drawImage(sprite, this.getX()  - Camera.x, this.getY() - Camera.y, null);
	}
	
	
	public void tick() {
		
	}
	
	
}
