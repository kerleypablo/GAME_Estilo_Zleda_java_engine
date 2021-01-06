package com.pabloGames.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
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
	
	public int maskx , masky , mwidth , mheight ;
	
	private BufferedImage sprite;
	
	public Entity( int x , int y , int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width =  width;
		this.height =  height;
		this.sprite = sprite;
		
		this.maskx = 0;
		this.masky = 0;
		this.mwidth = width;
		this.mheight = height;
	}
	
	public void setMask(int maskx, int maksy, int maksh, int maksw) {
		this.maskx = maskx;
		this.masky = maksy;
		this.mwidth = maksw;
		this.mheight = maksh;
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
	
	
	
	public void tick() {
		
	}
	
	public static boolean iscolidding(Entity e1 , Entity e2) {
		Rectangle e1mask = new Rectangle(e1.getX() + e1.maskx, e1.getY() + e1.masky, e1.mwidth,e1.mheight);
		Rectangle e2mask = new Rectangle(e2.getX() + e2.maskx, e2.getY() + e2.masky, e2.mwidth,e2.mheight);	
		
		return e1mask.intersects(e2mask);
	}

	public void render( Graphics g) {
		g.drawImage(sprite, this.getX()  - Camera.x, this.getY() - Camera.y, null);
		//g.setColor(Color.red);
		//g.fillRect(this.getX() +maskx  - Camera.x, this.getY() + masky - Camera.y, mwidth , mheight);
	}
	
}
