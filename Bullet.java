/*
 * File: Bullet.java
 * -------------------------
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Bullet extends Main
{
	private GOval bullet;
	private double xSpeed;
	private double ySpeed;
	private int lifeSpan = 100;	
	
	public Bullet(double xCoor, double yCoor, double currentRotation)
	{
		bullet = new GOval(xCoor, yCoor, BULLETDIAMETER, BULLETDIAMETER);
		bullet.setFilled(true);
		
		xSpeed = -Math.cos(Math.toRadians(currentRotation)) * BULLETSPEED;
		ySpeed = Math.sin(Math.toRadians(currentRotation)) * BULLETSPEED;
	}
	
	public GOval getGOval()
	{
		return bullet;
	}
	
	public double getBulletSpeedX()
	{
		return xSpeed;	
	}
	
	public double getBulletSpeedY()
	{
		return ySpeed;	
	}
	
	public int getLifeSpan()
	{
		return lifeSpan;
	}
	
	public void lowerLifeSpan()
	{
		lifeSpan--;
	}
	
	public void checkForCollision()
	{
		double x = bullet.getX();
		if((x < 0 && xSpeed < 0)|| (x > APPLICATION_WIDTH && xSpeed > 0))
		{
			xSpeed *= -1;
		}
		double y = bullet.getY();
		if((y < 0 && ySpeed < 0)|| (y > APPLICATION_WIDTH && ySpeed > 0))
		{
			ySpeed *= -1;
		}
	}
}











