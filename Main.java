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

public class Main extends GraphicsProgram
{
	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 600;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Tank width and height */
	private static final int TANKWIDTH = 20;
	private static final int TANKHEIGHT = 30;

	/** Tank forward and backward speed */
	private static final int TANKSPEED = 5;
	private static final int TANKBACKSPEED = 2;
	
	/** Bullet diameter and speed */
	public static final int BULLETDIAMETER = 3;
	public static final int BULLETSPEED = 6;
	
	// current keys pressed arraylist
	ArrayList<Character> keysPressed = new ArrayList<Character>();
	
	GPolygon tank1;
	GPolygon tank2;
	
	double currentRotationTank1 = 0;
	double currentRotationTank2 = 0;
	
	// to make sure not to keep shooting bullets
	int shootTimerTank1 = 20;
	int shootTimerTank2 = 20;
	
	// current bullets arraylist
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	/** Runs the Tanks program. */
	public void run()
	{	
		addTanksToScreen();
		addKeyListeners();
		while(true)
		{
			pause(20);
			playGameLoop();
		}
	}
	
	private void createMaze()
	{
//		Maze maze = new Maze();
//		add(maze.setupMaze());
	}
	
	private void playGameLoop()
	{
		tankActions();
		bulletMovement();
	}
	
	private void tankActions()
	{
		int size = keysPressed.size(); 
		for(int i=0; i<size; i++)
		{
			// Tank 1
			if(keysPressed.get(i) == 'a')			
			{
				tank1.rotate(10);
				currentRotationTank1 += 10;
			}
			else if(keysPressed.get(i) == 'd')
			{
				tank1.rotate(-10);
				currentRotationTank1 -= 10;
			}
			else if(keysPressed.get(i) == 'w')
			{
				double xSpeed = -1 * Math.cos(Math.toRadians(currentRotationTank1)) * TANKSPEED;
				double ySpeed = Math.sin(Math.toRadians(currentRotationTank1)) * TANKSPEED;
				double x = tank1.getX();
				double y = tank1.getY();
				
				// not tank out of screen (needs optimizing)
				if((x<=0 && xSpeed<0) || (x>=WIDTH && xSpeed>0))
				{
					xSpeed = 0;
				} 
				if((y<=0 && ySpeed<0) || (y>=HEIGHT && ySpeed>0))
				{
					ySpeed = 0;
				} 
				tank1.move(xSpeed,ySpeed);
			}
			else if(keysPressed.get(i) == 's')
			{
				double xSpeed = Math.cos(Math.toRadians(currentRotationTank1)) * TANKBACKSPEED;
				double ySpeed = -1*Math.sin(Math.toRadians(currentRotationTank1)) * TANKBACKSPEED;
				double x = tank1.getX();
				double y = tank1.getY();
				// not tank out of screen (needs optimizing)
				if((x<=0 && xSpeed<0) || (x>=WIDTH && xSpeed>0))
				{
					xSpeed = 0;
				} 
				if((y<=0 && ySpeed<0) || (y>=HEIGHT && ySpeed>0))
				{
					ySpeed = 0;
				} 
				tank1.move(xSpeed,ySpeed);
			}
			else if(keysPressed.get(i) == 'l')
			{
				if(shootTimerTank1 == 0)
				{
					shootBullet1(currentRotationTank1);
					shootTimerTank1 = 20;
				}
			}
			
			// Tank 2
			if(keysPressed.get(i) == 'L')			
			{
				tank2.rotate(10);
				currentRotationTank2 += 10;
			}
			else if(keysPressed.get(i) == 'R')
			{
				tank2.rotate(-10);
				currentRotationTank2 -= 10;
			}
			else if(keysPressed.get(i) == 'U')
			{
				double xSpeed = -1 * Math.cos(Math.toRadians(currentRotationTank2)) * TANKSPEED;
				double ySpeed = Math.sin(Math.toRadians(currentRotationTank2)) * TANKSPEED;
				double x = tank2.getX();
				double y = tank2.getY();
				
				// not tank out of screen (needs optimizing)
				if((x<=0 && xSpeed<0) || (x>=WIDTH && xSpeed>0))
				{
					xSpeed = 0;
				} 
				if((y<=0 && ySpeed<0) || (y>=HEIGHT && ySpeed>0))
				{
					ySpeed = 0;
				} 
				tank2.move(xSpeed,ySpeed);
			}
			else if(keysPressed.get(i) == 'D')
			{
				double xSpeed = Math.cos(Math.toRadians(currentRotationTank2)) * TANKBACKSPEED;
				double ySpeed = -1*Math.sin(Math.toRadians(currentRotationTank2)) * TANKBACKSPEED;
				double x = tank2.getX();
				double y = tank2.getY();
				// not tank out of screen (needs optimizing)
				if((x<=0 && xSpeed<0) || (x>=WIDTH && xSpeed>0))
				{
					xSpeed = 0;
				} 
				if((y<=0 && ySpeed<0) || (y>=HEIGHT && ySpeed>0))
				{
					ySpeed = 0;
				} 
				tank2.move(xSpeed,ySpeed);
			}
			else if(keysPressed.get(i) == 'S')
			{
				if(shootTimerTank2 == 0)
				{
					shootBullet2(currentRotationTank2);
					shootTimerTank2 = 20;
				}
			}
		}	
		if(shootTimerTank1 != 0)
		{
			shootTimerTank1--;
		}	
		if(shootTimerTank2 != 0)
		{
			shootTimerTank2--;
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
				// collision with wall
				temp.checkForCollision();
				// move bullet in right direction
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
	
	private void addTanksToScreen()
	{
		GPoint[] tankCoordinates = createGPointArray();
		
		tank1 = new GPolygon(tankCoordinates);
		tank1.setLocation(APPLICATION_WIDTH/2-TANKWIDTH,APPLICATION_HEIGHT/2-TANKHEIGHT);
		add(tank1);
		
		// for the right rotation 
		tank1.recenter();
		
		tank2 = new GPolygon(tankCoordinates);
		tank2.setLocation(APPLICATION_WIDTH/2-3*TANKWIDTH,APPLICATION_HEIGHT/2-3*TANKHEIGHT);
		add(tank2);
		
		// for the right rotation 
		tank2.recenter();
	}
	
	public void keyPressed(KeyEvent e)
	{
		// Tank 1
		if(e.getKeyCode() == KeyEvent.VK_A && !keysPressed.contains('a'))
		{
			keysPressed.add('a');
		}
		if(e.getKeyCode() == KeyEvent.VK_D && !keysPressed.contains('d'))
		{
			keysPressed.add('d');
		}
		if(e.getKeyCode() == KeyEvent.VK_W && !keysPressed.contains('w'))
		{
			keysPressed.add('w');
		}
		if(e.getKeyCode() == KeyEvent.VK_S && !keysPressed.contains('s'))
		{
			keysPressed.add('s');
		}
		if(e.getKeyCode() == KeyEvent.VK_L && !keysPressed.contains('l'))
		{
			keysPressed.add('l');
		}
				
		// Tank 2		
		if(e.getKeyCode() == KeyEvent.VK_LEFT && !keysPressed.contains('L'))
		{
			keysPressed.add('L');
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && !keysPressed.contains('R'))
		{
			keysPressed.add('R');
		}
		if(e.getKeyCode() == KeyEvent.VK_UP && !keysPressed.contains('U'))
		{
			keysPressed.add('U');
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN && !keysPressed.contains('D'))
		{
			keysPressed.add('D');
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE && !keysPressed.contains('S'))
		{
			keysPressed.add('S');
		}	
	}
	
	public void keyReleased(KeyEvent e)
	{
		// Tank 1
		if(e.getKeyCode() == KeyEvent.VK_A)
		{
			removeCharFromkeysPressed('a');
		}
		if(e.getKeyCode() == KeyEvent.VK_D)
		{
			removeCharFromkeysPressed('d');
		}
		if(e.getKeyCode() == KeyEvent.VK_W)
		{
			removeCharFromkeysPressed('w');
		}
		if(e.getKeyCode() == KeyEvent.VK_S)
		{
			removeCharFromkeysPressed('s');
		}
		if(e.getKeyCode() == KeyEvent.VK_L)
		{
			removeCharFromkeysPressed('l');
		}
		
		// Tank 2
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			removeCharFromkeysPressed('L');
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			removeCharFromkeysPressed('R');
		}
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			removeCharFromkeysPressed('U');
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			removeCharFromkeysPressed('D');
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			removeCharFromkeysPressed('S');
		}
	}
	
	private void removeCharFromkeysPressed(char Char)
	{
		int size = keysPressed.size();
		
		for(int i=0; i<size; i++)
		{
			if(keysPressed.get(i) == Char)
			{
				keysPressed.remove(i);
				// to fix outofboundsexception
				size -= 1;
			}
		}
	} 
	
	private GPoint[] createGPointArray()
	{
		GPoint[] tankCoordinates = new GPoint[4];
		GPoint GPoint1 = new GPoint();
		GPoint GPoint2 = new GPoint(0, TANKWIDTH);
		GPoint GPoint3 = new GPoint(TANKHEIGHT, TANKWIDTH);
		GPoint GPoint4 = new GPoint(TANKHEIGHT, 0);
		tankCoordinates[0] = GPoint1;
		tankCoordinates[1] = GPoint2;
		tankCoordinates[2] = GPoint3;
		tankCoordinates[3] = GPoint4;
		return tankCoordinates;
	}
	
	private void shootBullet1(double currentRotation)
	{
		Bullet newBullet = new Bullet(tank1.getX(), tank1.getY(), currentRotation);
		add(newBullet.getGOval());
		bullets.add(newBullet);
	}
	
	private void shootBullet2(double currentRotation)
	{
		Bullet newBullet = new Bullet(tank2.getX(), tank2.getY(), currentRotation);
		add(newBullet.getGOval());
		bullets.add(newBullet);
	}
}






















