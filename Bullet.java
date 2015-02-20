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

public class Bullet extends Tanks
{
	GOval bullet;
	private double xSpeed;
	private double ySpeed;	
	
	public Bullet(double xCoor, double yCoor, double currentRotation)
	{
		bullet = new GOval(xCoor, yCoor, BULLETDIAMETER, BULLETDIAMETER);
		bullet.setFilled(true);
		
		xSpeed = -Math.cos(Math.toRadians(currentRotation)) * BULLETSPEED;
		ySpeed = Math.sin(Math.toRadians(currentRotation)) * BULLETSPEED;
	}
	
	public double getBulletSpeedX()
	{
		return xSpeed;	
	}
	public double getBulletSpeedY()
	{
		return ySpeed;	
	}
}











