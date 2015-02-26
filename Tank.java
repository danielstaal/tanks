/*
 * File: Tank.java
 * -------------------------
 */
 
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Tank extends GraphicsProgram
{
	private GPolygon tankPolygon;
	private int currentRotation;
	private int shootTimerTank;
	
	private static final int APPLICATION_WIDTH = 600;
	private static final int APPLICATION_HEIGHT = 600;
		/** Tank width and height */
	private static final int TANKWIDTH = 20;
	private static final int TANKHEIGHT = 30;

	/** Tank forward and backward speed */
	private static final int TANKSPEED = 5;
	private static final int TANKBACKSPEED = 2;
	
	public Tank()
	{
		currentRotation = 0;
		shootTimerTank = 20;
	}

	public GPolygon GPolygonAtCoordinates()
	{
		GPoint[] tankCoordinates = createGPointArray();
		
		mtankPolygon = new GPolygon(tankCoordinates);
		tankPolygon.setLocation(APPLICATION_WIDTH/2-TANKWIDTH,APPLICATION_HEIGHT/2-TANKHEIGHT);
		
		// for the right rotation 
		tankPolygon.recenter();
		
		return tankPolygon;
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
	
	public int getCurrentRotation()
	{
		return currentRotation;
	}
	
	public GPolygon getPolygon()
	{
		return tankPolygon;
	}
	
	public int getShootTimerTank()
	{
		return shootTimerTank;
	}
	
	public void decreaseShootTimer()
	{
		shootTimerTank--;
	}
	
	public void changeCurrentRotation(int rot)
	{
		currentRotation += rot;
	}
	
	public void resetShootTimerTank()
	{
		shootTimerTank = 20;
	}
}











