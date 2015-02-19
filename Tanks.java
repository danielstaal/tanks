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

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
		(WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;




	private static final int TANKSPEED = 5;
	private static final int BULLETDIAMETER = 3;
	
	ArrayList<Character> keyspressed = new ArrayList<Character>();
	
	GPolygon tank1;
	GPoint[] tankCoordinates = new GPoint[4];
	double currentRotation = 0;

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
			keyspressed.clear();
		}
		if(e.getKeyCode() == KeyEvent.VK_D)
		{
			keyspressed.clear();
		}
		if(e.getKeyCode() == KeyEvent.VK_W)
		{
			keyspressed.clear();
		}
		if(e.getKeyCode() == KeyEvent.VK_L)
		{
			keyspressed.clear();
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
		GOval bullet = new GOval(tank1.getX(), tank1.getY(),BULLETDIAMETER, BULLETDIAMETER);
		add(bullet); 
	}
}






















