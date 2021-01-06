package com.pabloGames.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.pabloGames.entities.Player;

public class UI {

	
	public void render(Graphics g) {
		// life
		g.setColor(Color.RED);
		g.fillRect(8, 4, 70,5);
		g.setColor(Color.GREEN);
		g.fillRect(8, 4,(int)((Player.life/Player.maxLife)*70),5);
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD,7));
		g.drawString( Player.life + "/" + (int)Player.maxLife ,25, 9);
		//***
		// energy
		g.setColor(Color.MAGENTA);
		g.fillRect(8, 10, 70,5);
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD,7));
		g.drawString( Player.energy + "/" + (int)Player.maxEnergy ,25, 15);
		//**
	}
	
}
