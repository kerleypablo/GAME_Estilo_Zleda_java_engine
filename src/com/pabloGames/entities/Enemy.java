package com.pabloGames.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.pabloGames.Game;
import com.pabloGames.word.Camera;
import com.pabloGames.word.World;

public class Enemy extends Entity {

	private double speed = 0.4;
	public int right_dir = 0, left_dir =1,up_dir=2, down_dir = 3;
	public int dir = down_dir;
	
	private int maskx = 8, masky = 8 , maskw = 8 , maskch = 8;
	private int scalemask = 8;
	
	private int frames = 0,maxframes = 18,index=0 ,maxindex = 1;
	private BufferedImage[] rightEnemy;
	private BufferedImage[] leftEnemy;
	private BufferedImage[] upEnemy;
	private BufferedImage[] downEnemy;
	
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightEnemy = new BufferedImage[2];
		leftEnemy = new BufferedImage[2];
		upEnemy  = new BufferedImage[2];
		downEnemy  = new BufferedImage[2];
		for(int i = 0; i < 2 ;i++) {
			rightEnemy[i] = Game.spritesheet.getSprite(80+(i*16),48, 16, 16);
		}
		for(int i = 0; i < 2 ;i++) {
			leftEnemy[i] = Game.spritesheet.getSprite(80+ (i*16),2*16, 16, 16);
			}
		for(int i = 0; i < 2 ;i++) {
			upEnemy[i] = Game.spritesheet.getSprite((7*16)+ (i*16),16, 16, 16);
			}
		for(int i = 0; i < 2 ;i++) {
			downEnemy[i] = Game.spritesheet.getSprite((5*16)+ (i*16),16, 16, 16);
			}
		
		
	}
	
	public void tick() {
		if (isPlayerVisible() == true) {
		if (iscoliddingwithPlayer() == false){
		if((int)x < Game.player.getX() && World.isFree((int)(x+speed),this.getY())
				&& !isColidding((int)(x+speed),this.getY())){
			x+=speed;
			dir = right_dir;
		}else if((int)x > Game.player.getX() && World.isFree((int)(x-speed),this.getY())
				&& !isColidding((int)(x-speed),this.getY())) {
			x-=speed;
			dir =left_dir;
		}
		 if((int)y < Game.player.getY()&& World.isFree(this.getX(),(int)(y+speed))
				 && !isColidding(this.getX(),(int)(y+speed))) {
			y+=speed;
			dir = down_dir;
		}else if((int)y > Game.player.getY() && World.isFree(this.getX(),(int)(y-speed))
				&& !isColidding(this.getX(),(int)(y-speed))) {
			y-=speed;
			dir =up_dir;
		}
		}else {
			//estamos estamos colidindo
			if(Game.rand.nextInt(100)< 10) {
			Game.player.life -= Game.rand.nextInt(3);
			System.out.println(Game.player.life);
			if (Game.player.life <= 0) {
				//Game Over
				System.exit(1);
			}
			}
		}
		}
				frames++;
				if(frames == maxframes) {
					frames=0;
					index++;
					if(index> maxindex)
						index=0;
				}
			
		
	}
	

	public boolean isPlayerVisible() {
		Rectangle enemyCurrent2 = new Rectangle(((this.getX() -4) + maskx)- Camera.x ,((this.getY() -4)+ masky) - Camera.y ,maskw,maskch);
		Rectangle player = new Rectangle(((Game.player.getX() - (scalemask * scalemask) )-Camera.x ),(Game.player.getY()- (scalemask * scalemask)) - Camera.y  ,16*scalemask,16*scalemask);
		if (enemyCurrent2.intersects(player)) {
		return true;
		}
		return false;
	}
	
	public boolean iscoliddingwithPlayer() {
		Rectangle enemyCurrent = new Rectangle(this.getX() + maskx,this.getY() + masky,maskw,maskch);
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),16,16);
		return enemyCurrent.intersects(player);
	}
	
	public boolean isColidding(int xnext , int ynext) {
		Rectangle enemyCurrent = new Rectangle(xnext + maskx,ynext + masky,maskw,maskch);
		for(int i = 0 ; i < Game.enemies.size();i++) {
			Enemy e = Game.enemies.get(i);
			if(e == this)
				continue;
		Rectangle targetEnemy = new Rectangle(e.getX()+ maskx,e.getY()+ masky,maskw,maskch);
		if(enemyCurrent.intersects(targetEnemy)) {
			return true;
		}
		}
		
		return false;
	}
	
	public void render(Graphics g) {
		//g.setColor(Color.blue);
		//g.fillRect(this.getX() + maskx,this.getY() + masky,maskw,maskch);
		if(dir == right_dir) {
			g.drawImage(rightEnemy[index], this.getX() - Camera.x,this.getY()-Camera.y ,null);
		}else if(dir== left_dir) {
			g.drawImage(leftEnemy[index], this.getX()-Camera.x,this.getY() - Camera.y ,null);
		}else if (dir == up_dir) {
			g.drawImage(upEnemy[index], this.getX()- Camera.x,this.getY()-Camera.y ,null);
		}else if(dir==down_dir) {
			g.drawImage(downEnemy[index], this.getX()- Camera.x,this.getY()- Camera.y ,null);
		}
		
		
		
		
		//g.fillRect(this.getX() + maskx - Camera.x, this.getY()+ masky - Camera.y, maskw, maskch);
	}
	
}
