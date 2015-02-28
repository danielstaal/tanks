/*
 * File: AItank.java
 * -------------------------
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AI extends Tank
{
	ArrayList<Character> AIkeys = new ArrayList<Character>();
	
	private int turnTimer = 10;
	private int dodging = 0;
	
	public AI(int width, int height, int backSpeed, int speed)
		{
			APPLICATION_WIDTH = width;
			APPLICATION_HEIGHT = height;
			TANKSPEED = speed;
			TANKBACKSPEED = backSpeed;
		}	
	
	public ArrayList<Character> getAIkeys()
	{
		return AIkeys;
	}
	
	public GPoint[] dodgeBullet(ArrayList<Bullet> bullets)
	{
		
		GPoint[] bulletPoints = new GPoint[1];
	
		if(bullets.size() > 0){
			bulletPoints = new GPoint[bullets.size()*100]; 
			for(int j=0; j<bullets.size(); j++)
			{
				Bullet temp = bullets.get(j);
				double x = temp.getGOval().getX();
				double y = temp.getGOval().getY();
		
				double xSpeed = temp.getBulletSpeedX();
				double ySpeed = temp.getBulletSpeedY();
			
				for(int i=0; i<100; i++)
				{
					GPoint tempPoint = new GPoint(x,y);
					bulletPoints[j*100+i] = tempPoint;
					x += xSpeed;
					y += ySpeed;
				}
			}
		}
		return bulletPoints;
	}
	
	public int getTurnTimer()
	{
		return turnTimer;
	}
	
	public void decreaseTurnTimer()
	{
		turnTimer--;
	}
	
	public void resetTurnTimer()
	{	
		turnTimer = 20;
	} 
	
	public int getDodging()
	{
		return dodging;
	}
	
	public void increaseDodging()
	{
		dodging++;
	}
	
	public void resetDodging()
	{
		dodging = 0;
	}
}








