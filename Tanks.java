/*
 * File: Breakout.java
 * -------------------
 * Names: Sierk Kanis & Daniel Staal
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
	private static final int BULLETDIAMETER = 3;
	
	ArrayList<Character> keyspressed = new ArrayList<Character>();
	
	GPolygon tank1;
	GPoint[] tankCoordinates = new GPoint[4];
	double currentRotation = 0;
	
	// bullets arraylist
	ArrayList<GOval> bullets = new ArrayList<GOval>();

	/** Runs the Breakout program. */
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
		tankMovement();
		bulletMovement();
	}
	
	private void tankMovement()
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
			else if(keyspressed.get(i) == 'l')
			{
				shootBullet();
			}
		}		
	}
	
	private void bulletMovement()
	{
//		int noOfBullets = bullets.size();
//		for(int i=0; i<size; i++)
//		{
//			
//		}
	}
	
	private void addTankToCenter()
	{
		tank1 = new GPolygon(tankCoordinates);
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
		if(e.getKeyCode() == KeyEvent.VK_D)
		{
			keyspressed.add('d');
		}
		if(e.getKeyCode() == KeyEvent.VK_W)
		{
			keyspressed.add('w');
		}
		if(e.getKeyCode() == KeyEvent.VK_L)
		{
			keyspressed.add('l');
		}	
	}
	
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
		tankCoordinates[0] = GPoint1;
		tankCoordinates[1] = GPoint2;
		tankCoordinates[2] = GPoint3;
		tankCoordinates[3] = GPoint4;
	}
	
	private void shootBullet()
	{
		//Bullet newBullet = new Bullet();

	}
}






















