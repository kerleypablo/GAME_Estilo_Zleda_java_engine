package com.pabloGames.word;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.pabloGames.Game;

public class Tile {

	public static BufferedImage TILE_FLORR = Game.spritesheet.getSprite(0, 64, 16, 16);
	public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(16, 64, 16, 16);
	
	private BufferedImage sprite;
	private int x, y ;
	
	public Tile(int x , int y , BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	
	
	public void render(Graphics g) {
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}
	
	
	
	
}
