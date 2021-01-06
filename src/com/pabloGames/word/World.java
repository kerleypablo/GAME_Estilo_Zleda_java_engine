package com.pabloGames.word;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.pabloGames.Game;
import com.pabloGames.entities.Enemy;
import com.pabloGames.entities.Energy;
import com.pabloGames.entities.Entity;
import com.pabloGames.entities.Lifepack;
import com.pabloGames.entities.Wapon;

public class World {

	public static  Tile[] tiles;
	public static int WIDTH , HEIGTH;
	public static final int Tile_SIZE = 16;
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];// saber quantos pixel tem, a imagem
			tiles = new Tile[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGTH = map.getHeight();		
			map.getRGB(0, 0,map.getWidth(),map.getHeight(),pixels , 0 , map.getWidth());//coloca a quantidade dentro do array
			for(int xx = 0; xx <map.getWidth() ; xx++) {
				for(int yy = 0; yy <map.getHeight() ; yy++) {
					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy*16, Tile.TILE_FLORR);
					if(pixelAtual == 0xFF000000 ) {
						// floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy*16, Tile.TILE_FLORR);
					}else if( pixelAtual == 0xFFFFFFFF ) {
						// wall
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy*16, Tile.TILE_WALL);
					}else if ( pixelAtual == 0xFF0026FF) {
						// player
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
					}else if (pixelAtual == 0xffFF0000) {
						// Enemy 
						Enemy en = new Enemy(xx*16,yy*16,16,16,Entity.ENEMY_EN);
						Game.entities.add(en);
						Game.enemies.add(en);
						
					}else if (pixelAtual == 0xffFF6A00) {
						//wapon
						Game.entities.add(new Wapon(xx*16,yy*16,16,16,Entity.WAPON_EN));
						
					}else if(pixelAtual == 0xffFF00DC) {
						//energy
						Game.entities.add(new Energy(xx*16,yy*16,16,16,Entity.ENERGY_EN));
						
					}else if(pixelAtual == 0xff00FF21) {
						// life
						Game.entities.add(new Lifepack(xx*16,yy*16,16,16,Entity.LIFEPACK_EN));
					}
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static boolean isFree (int xnext, int ynext ) {
		int x1 = xnext / Tile_SIZE;
		int y1 = ynext / Tile_SIZE;
		
		int x2 = (xnext +Tile_SIZE - 1) / Tile_SIZE;
		int y2 = ynext /Tile_SIZE ;
		
		int x3 = xnext  / Tile_SIZE;
		int y3 = (ynext +Tile_SIZE - 1) / Tile_SIZE ;
		
		int x4 = (xnext +Tile_SIZE - 1) / Tile_SIZE;
		int y4 = (ynext +Tile_SIZE - 1) / Tile_SIZE ;
		
		return !((tiles[x1 + (y1 * World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2 * World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3 * World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4 * World.WIDTH)] instanceof WallTile));
	}
	
	
	public void render(Graphics g) {
		int xstart = Camera.x>>4;
		int ystart = Camera.y>>4;
		
		int xfinal = xstart + (Game.WIDTH>>4) ;
		int yfinal = ystart + (Game.HEIGHT>>4) ;
		
		for(int xx = xstart ; xx <= xfinal ; xx++) {
			for(int yy = ystart ; yy <= yfinal ; yy++) {
				if(xx<0 || yy < 0 || xx>= WIDTH || yy >= HEIGTH)
					continue;
				Tile tile = tiles[xx+(yy* WIDTH)];
				tile.render(g);
			}
		}
		
	}
	
}
