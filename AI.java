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
		GPoint[] bulletPoints = new GPoint[100];
		if(bullets.size() > 0){ 
			Bullet temp = bullets.get(0);
			double x = temp.getGOval().getX();
			double y = temp.getGOval().getY();
		
			double xSpeed = temp.getBulletSpeedX();
			double ySpeed = temp.getBulletSpeedY();
			
			for(int i=0; i<100; i++)
			{
				GPoint tempPoint = new GPoint(x,y);
				bulletPoints[i] = tempPoint;
				x += xSpeed;
				y += ySpeed;
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
}








