package com.pabloGames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.pabloGames.Game;

public class Player extends Entity{

	
	public boolean right , up , left , down;
	public int right_dir = 0, left_dir =1,up_dir=2, down_dir = 3;
	public int dir = right_dir;
	public double speed = 1.4;
	
	private int frames = 0,maxframes = 5,index=0 ,maxindex = 3;
	private boolean moved = false;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		upPlayer  = new BufferedImage[4];
		downPlayer  = new BufferedImage[4];
		for(int i = 0; i < 4 ;i++) {
		rightPlayer[i] = Game.spritesheet.getSprite(0+(i*16),16, 16, 16);
		}
		for(int i = 0; i < 4 ;i++) {
			leftPlayer[i] = Game.spritesheet.getSprite(0+ (i*16),32, 16, 16);
			}
		for(int i = 0; i < 4 ;i++) {
			upPlayer[i] = Game.spritesheet.getSprite(0+ (i*16),48, 16, 16);
			}
		for(int i = 0; i < 4 ;i++) {
			downPlayer[i] = Game.spritesheet.getSprite(0+ (i*16),0, 16, 16);
			}
	}

	
	public void tick() {
		moved= false;
		if(right) {
			moved = true;
			dir= right_dir;
			x+=speed;
		}
		else if (left) {
			moved = true;
			dir = left_dir;
			x-=speed;
		}
		if(up) { 
			moved = true;
			dir=up_dir;
			y-=speed;
			}
		else if(down) {
			moved = true;
			dir=down_dir;
			y+=speed;
		}
		if(moved) {
			frames++;
			if(frames == maxframes) {
				frames=0;
				index++;
				if(index> maxindex)
					index=0;
			}
		}
		
	}
	
	public void render(Graphics g) {
		if(dir == right_dir) {
			g.drawImage(rightPlayer[index], this.getX(),this.getY() ,null);
		}else if(dir== left_dir) {
			g.drawImage(leftPlayer[index], this.getX(),this.getY() ,null);
		}else if (dir == up_dir) {
			g.drawImage(upPlayer[index], this.getX(),this.getY() ,null);
		}else if(dir==down_dir) {
			g.drawImage(downPlayer[index], this.getX(),this.getY() ,null);
		}
		
		}
	
	
}
