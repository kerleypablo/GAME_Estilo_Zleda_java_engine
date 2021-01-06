package com.pabloGames.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.pabloGames.Game;
import com.pabloGames.word.Camera;
import com.pabloGames.word.World;

public class Player extends Entity{

	
	public boolean right , up , left , down;
	public int right_dir = 0, left_dir =1,up_dir=2, down_dir = 3;
	public int dir = right_dir;
	public double speed = 1.2;
	
	
	private int frames = 0,maxframes = 5,index=0 ,maxindex = 3;
	private boolean moved = false;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;
	
	public static double life = 100, maxLife = 100;
	
	public static double energy = 80, maxEnergy = 100;
	
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
		if(right && World.isFree((int)(x+speed),this.getY())) {
			moved = true;
			dir= right_dir;
			x+=speed;
		}
		else if (left && World.isFree((int)(x-speed),this.getY())) {
			moved = true;
			dir = left_dir;
			x-=speed;
		}
		if(up && World.isFree(this.getX(),(int)(y-speed))) { 
			moved = true;
			dir=up_dir;
			y-=speed;
			}
		else if(down && World.isFree(this.getX(),(int)(y+speed))) {
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
		this.checkColisionLifepack();
		
		this.checkColisionEnergypack();
		
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2),0,World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2),0,World.HEIGTH*16 - Game.HEIGHT);
	}
	
	public void checkColisionLifepack() {
		for(int i = 0 ; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Lifepack) {
				if(Entity.iscolidding(this, atual)) {
					life+=10;
					if(life >=100) {
						life = 100;
					}
					Game.entities.remove(atual);
					return;
				}
			}
			
		}
	}
	
	
	public void checkColisionEnergypack() {
		for(int i = 0 ; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Energy) {
				if(Entity.iscolidding(this, atual)) {
					energy+=10;
					if(energy >=100) {
						energy = 100;
					}
					Game.entities.remove(atual);
					return;
				}
			}
			
		}
	}
	
	
	
	public void render(Graphics g) {
		//g.setColor(Color.red);
		//g.fillRect(this.getX() +maskx  - Camera.x, this.getY() + masky - Camera.y, mwidth , mheight);
		if(dir == right_dir) {
			g.drawImage(rightPlayer[index], this.getX() - Camera.x,this.getY()-Camera.y ,null);
		}else if(dir== left_dir) {
			g.drawImage(leftPlayer[index], this.getX()-Camera.x,this.getY() - Camera.y ,null);
		}else if (dir == up_dir) {
			g.drawImage(upPlayer[index], this.getX()- Camera.x,this.getY()-Camera.y ,null);
		}else if(dir==down_dir) {
			g.drawImage(downPlayer[index], this.getX()- Camera.x,this.getY()- Camera.y ,null);
		}
		
		}
	
	
}
