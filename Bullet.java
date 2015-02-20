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
	double x;
	double y;
	GOval bullet;
	double xSpeed = 4;
	double ySpeed = 4;

	public Bullet(double xCoor, double yCoor)
	{
		x = xCoor;
		y = yCoor;
		bullet = new GOval(x, y, BULLETDIAMETER, BULLETDIAMETER);
		bullet.setFilled(true);
	}
}
