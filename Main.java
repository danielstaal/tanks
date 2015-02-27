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
	
	private static final int rotationSpeed = 5;

	
	// current keys pressed arraylist
	ArrayList<Character> keysPressed = new ArrayList<Character>();
	
	Tank tank1 = new Tank(WIDTH, HEIGHT, TANKSPEED, TANKBACKSPEED);
	AI tank2 = new AI(WIDTH, HEIGHT, TANKSPEED, TANKBACKSPEED);

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
		runAI();
		tankActions();
		bulletMovement();
	}
	
	private void tankActions()
	{
		int size = keysPressed.size(); 
		for(int i=0; i<size; i++)
		{
			//to fix outofboundsexception
			size = keysPressed.size();
			
			
			// Tank 1
			if(keysPressed.get(i) == 'a')			
			{
				tank1.getPolygon().rotate(rotationSpeed);
				tank1.changeCurrentRotation(rotationSpeed);
			}
			else if(keysPressed.get(i) == 'd')
			{
				tank1.getPolygon().rotate(-rotationSpeed);
				tank1.changeCurrentRotation(-rotationSpeed);
			}
			else if(keysPressed.get(i) == 'w')
			{
				double xSpeed = -1 * Math.cos(Math.toRadians(tank1.getCurrentRotation())) * TANKSPEED;
				double ySpeed = Math.sin(Math.toRadians(tank1.getCurrentRotation())) * TANKSPEED;
				double x = tank1.getPolygon().getX();
				double y = tank1.getPolygon().getY();
				
				// not tank out of screen (needs optimizing)
				if((x<=0 && xSpeed<0) || (x>=WIDTH && xSpeed>0))
				{
					xSpeed = 0;
				} 
				if((y<=0 && ySpeed<0) || (y>=HEIGHT && ySpeed>0))
				{
					ySpeed = 0;
				} 
				tank1.getPolygon().move(xSpeed,ySpeed);
			}
			else if(keysPressed.get(i) == 's')
			{
				double xSpeed = Math.cos(Math.toRadians(tank1.getCurrentRotation())) * TANKBACKSPEED;
				double ySpeed = -1*Math.sin(Math.toRadians(tank1.getCurrentRotation())) * TANKBACKSPEED;
				double x = tank1.getPolygon().getX();
				double y = tank1.getPolygon().getY();
				// not tank out of screen (needs optimizing)
				if((x<=0 && xSpeed<0) || (x>=WIDTH && xSpeed>0))
				{
					xSpeed = 0;
				} 
				if((y<=0 && ySpeed<0) || (y>=HEIGHT && ySpeed>0))
				{
					ySpeed = 0;
				} 
				tank1.getPolygon().move(xSpeed,ySpeed);
			}
			else if(keysPressed.get(i) == 'l')
			{
				if(tank1.getShootTimerTank() == 0)
				{
					shootBullet1();
					tank1.resetShootTimerTank();
				}
			}
			
			
			
			
			
			// Tank 2 (AI)
			
			 
			if(keysPressed.get(i) == 'L')			
			{
				tank2.getPolygon().rotate(rotationSpeed);
				tank2.changeCurrentRotation(rotationSpeed);
			}
			else if(keysPressed.get(i) == 'R')
			{
				tank2.getPolygon().rotate(-rotationSpeed);
				tank2.changeCurrentRotation(-rotationSpeed);
			}
			else if(keysPressed.get(i) == 'U')
			{
				double xSpeed = -1 * Math.cos(Math.toRadians(tank2.getCurrentRotation())) * TANKSPEED;
				double ySpeed = Math.sin(Math.toRadians(tank2.getCurrentRotation())) * TANKSPEED;
				double x = tank2.getPolygon().getX();
				double y = tank2.getPolygon().getY();
				
				// not tank out of screen (needs optimizing)
				if((x<=0 && xSpeed<0) || (x>=WIDTH && xSpeed>0))
				{
					xSpeed = 0;
				} 
				if((y<=0 && ySpeed<0) || (y>=HEIGHT && ySpeed>0))
				{
					ySpeed = 0;
				} 
				tank2.getPolygon().move(xSpeed,ySpeed);
			}
			else if(keysPressed.get(i) == 'D')
			{
				double xSpeed = Math.cos(Math.toRadians(tank2.getCurrentRotation())) * TANKBACKSPEED;
				double ySpeed = -1*Math.sin(Math.toRadians(tank2.getCurrentRotation())) * TANKBACKSPEED;
				double x = tank2.getPolygon().getX();
				double y = tank2.getPolygon().getY();
				// not tank out of screen (needs optimizing)
				if((x<=0 && xSpeed<0) || (x>=WIDTH && xSpeed>0))
				{
					xSpeed = 0;
				} 
				if((y<=0 && ySpeed<0) || (y>=HEIGHT && ySpeed>0))
				{
					ySpeed = 0;
				} 
				tank2.getPolygon().move(xSpeed,ySpeed);
			}
			else if(keysPressed.get(i) == 'S')
			{
				if(tank2.getShootTimerTank() == 0)
				{
					shootBullet2();
					tank2.resetShootTimerTank();
				}
			}
		}	
		if(tank1.getShootTimerTank() != 0)
		{
			tank1.decreaseShootTimer();
		}	
		if(tank2.getShootTimerTank() != 0)
		{
			tank2.decreaseShootTimer();
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
				checkHitTank(temp);
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
		
	private void checkHitTank(Bullet temp)
	{
		GObject collider = getElementAt(temp.getGOval().getX(), temp.getGOval().getY());
		if(collider == null){}
		else if(collider.equals(tank1.getPolygon()))
			{
				//print("Player 2 won!");
			}
		else if(collider.equals(tank2.getPolygon()))
			{
				//print("Player 1 won!");
			}
	}
	
	private void addTanksToScreen()
	{
		add(tank1.GPolygonAtCoordinates());
		add(tank2.GPolygonAtCoordinates());
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
	
	private void shootBullet1()
	{
		Bullet newBullet = new Bullet(tank1.getPolygon().getX(), tank1.getPolygon().getY(), tank1.getCurrentRotation(), WIDTH, HEIGHT, TANKWIDTH, TANKHEIGHT);
		add(newBullet.getGOval());
		bullets.add(newBullet);
	}
	
	private void shootBullet2()
	{
		Bullet newBullet = new Bullet(tank2.getPolygon().getX(), tank2.getPolygon().getY(), tank2.getCurrentRotation(), WIDTH, HEIGHT, TANKWIDTH, TANKHEIGHT);
		add(newBullet.getGOval());
		bullets.add(newBullet);
	}
	
	
	////////////////////////////////////////////////////////////
	
	private void runAI()
	{
		GPoint[] bulletPoints = tank2.dodgeBullet(bullets);
		
		GObject collider;
		
		boolean dodgeFinished = true;
		
		if(bulletPoints[0] != null)
		{
			for(int j=0; j<bulletPoints.length; j++)
			{
				collider = getElementAt(bulletPoints[j]);
			
				if(collider == null){}
				else if(collider.equals(tank2.getPolygon()))
				{
					dodge();
					dodgeFinished = false;
					// stop searching
					break;
				}
			}
			if(dodgeFinished == true)
			{
				tank2.resetDodging();
				removeCharFromkeysPressed('L');
				removeCharFromkeysPressed('U');
				removeCharFromkeysPressed('R');
				removeCharFromkeysPressed('D');
			}
		}
	}
	
	private void dodge()
	{
		if(tank2.getDodging() == 0)
		{
			int rand = randInt(0, 100);
			int rand2 = randInt(0, 100);
			
			if(rand > 50)keysPressed.add('L');
			else keysPressed.add('R');
			
			if(rand > 50)keysPressed.add('U');
			else keysPressed.add('D');
		}
		tank2.increaseDodging();
	}
	
	public static int randInt(int min, int max) {

    // NOTE: Usually this should be a field rather than a method
    // variable so that it is not re-seeded every call.
    Random rand = new Random();

    // nextInt is normally exclusive of the top value,
    // so add 1 to make it inclusive
    int randomNum = rand.nextInt((max - min) + 1) + min;

    return randomNum;
}
}






















