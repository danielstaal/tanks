/*
 * File: Breakout.java
 * -------------------
 * Name: Daniel Staal
 * 
 * This file will eventually implement the game of Tanks.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Tanks extends GraphicsProgram
{
	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 600;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Tank width and height */
	private static final int TANKWIDTH = 20;
	private static final int TANKHEIGHT = 40;

	private static final int TANKSPEED = 5;
	private static final int TANKBACKSPEED = 2;
	public static final int BULLETDIAMETER = 3;
	public static final int BULLETSPEED = 6;
	
	ArrayList<Character> keyspressed = new ArrayList<Character>();
	
	GPolygon tank1;
	GPolygon tank2;
	
	GPoint[] tank1Coordinates = new GPoint[4];
	double currentRotation = 0;
	
	// to make sure not keeping shooting bullets
	int shootTimer = 20;
	
	// bullets arraylist
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	/** Runs the Tanks program. */
	public void run()
	{
		createGPointArray();
		addTankToCenter();
		addKeyListeners();
		while(true)
		{
			pause(20);
			playGameLoop();
		}
	}
	
	private void playGameLoop()
	{
		tankActions();
		bulletMovement();
	}
	
	private void tankActions()
	{
		int size = keyspressed.size(); 
		for(int i=0; i<size; i++)
		{
			if(keyspressed.get(i) == 'a')			
			{
				tank1.rotate(10);
				currentRotation += 10;
			}
			else if(keyspressed.get(i) == 'd')
			{
				tank1.rotate(-10);
				currentRotation -= 10;
			}
			else if(keyspressed.get(i) == 'w')
			{
				double x = Math.cos(Math.toRadians(currentRotation)) * TANKSPEED;
				double y = Math.sin(Math.toRadians(currentRotation)) * TANKSPEED;
				tank1.move(-x,y);
			}
			else if(keyspressed.get(i) == 's')
			{
				double x = Math.cos(Math.toRadians(currentRotation)) * TANKBACKSPEED;
				double y = Math.sin(Math.toRadians(currentRotation)) * TANKBACKSPEED;
				tank1.move(x,-y);
			}
			else if(keyspressed.get(i) == 'l')
			{
				if(shootTimer == 0)
				{
					shootBullet(currentRotation);
					shootTimer = 20;
				}
			}
		}	
		if(shootTimer != 0)
		{
			shootTimer--;
		}	
	}
	
	private void bulletMovement()
	{
		int noOfBullets = bullets.size();
		for(int i=0; i<noOfBullets; i++)
		{
			Bullet temp = bullets.get(i);
			if(temp.getLifeSpan() > 0)
			{
				temp.getGOval().move(temp.getBulletSpeedX(), temp.getBulletSpeedY());
			}else
			{
				remove(temp.getGOval());
				bullets.remove(i);
				noOfBullets--;
			}
			temp.lowerLifeSpan();
		}
	}
	
	private void addTankToCenter()
	{
		tank1 = new GPolygon(tank1Coordinates);
		tank1.setLocation(APPLICATION_WIDTH/2-TANKWIDTH,APPLICATION_HEIGHT/2-TANKHEIGHT);
		add(tank1);
		
		// for the right rotation 
		tank1.recenter();
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_A && !keyspressed.contains('a'))
		{
			keyspressed.add('a');
		}
		if(e.getKeyCode() == KeyEvent.VK_D && !keyspressed.contains('d'))
		{
			keyspressed.add('d');
		}
		if(e.getKeyCode() == KeyEvent.VK_W && !keyspressed.contains('w'))
		{
			keyspressed.add('w');
		}
		if(e.getKeyCode() == KeyEvent.VK_S && !keyspressed.contains('s'))
		{
			keyspressed.add('s');
		}
		if(e.getKeyCode() == KeyEvent.VK_L && !keyspressed.contains('l'))
		{
			keyspressed.add('l');
		}	
	}
	
	// possibly to switch-statement
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_A)
		{
			removeCharFromKeysPressed('a');
		}
		if(e.getKeyCode() == KeyEvent.VK_D)
		{
			removeCharFromKeysPressed('d');
		}
		if(e.getKeyCode() == KeyEvent.VK_W)
		{
			removeCharFromKeysPressed('w');
		}
		if(e.getKeyCode() == KeyEvent.VK_S)
		{
			removeCharFromKeysPressed('s');
		}
		if(e.getKeyCode() == KeyEvent.VK_L)
		{
			removeCharFromKeysPressed('l');
		}
	}
	
	private void removeCharFromKeysPressed(char Char)
	{
		int size = keyspressed.size();
		
		for(int i=0; i<size; i++)
		{
			if(keyspressed.get(i) == Char)
			{
				keyspressed.remove(i);
				// to fix outofboundsexception
				size -= 1;
			}
		}
	} 
	
	private void createGPointArray()
	{
		GPoint GPoint1 = new GPoint();
		GPoint GPoint2 = new GPoint(0, TANKWIDTH);
		GPoint GPoint3 = new GPoint(TANKHEIGHT, TANKWIDTH);
		GPoint GPoint4 = new GPoint(TANKHEIGHT, 0);
		tank1Coordinates[0] = GPoint1;
		tank1Coordinates[1] = GPoint2;
		tank1Coordinates[2] = GPoint3;
		tank1Coordinates[3] = GPoint4;
	}
	
	private void shootBullet(double currentRotation)
	{
		Bullet newBullet = new Bullet(tank1.getX(), tank1.getY(), currentRotation);
		add(newBullet.getGOval());
		bullets.add(newBullet);
	}
}






















