package de.simpleGame.interfaces;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public interface XShape extends Shape
{
	// Zusaetzliche Eigenschaften fuer Rechteck
	public Color getFillColor();
	public void setFillColor(Color fillColor);
	public char getShapeTyp();
	public void setShapeTyp(char typ);
	public int getHits();
	public int getDifHits();
	public void incrementHits();
	public void lock();
	public void unlock();
	public boolean isLocked();
	
	// Methoden der Klasse Rectangle2D
	public Rectangle2D createIntersection(Rectangle2D arg0);
	public Rectangle2D createUnion(Rectangle2D arg0);
	public int outcode(double arg0, double arg1);
	public void setRect(double x, double y, double w, double h);
	public double getHeight();
	public double getWidth();
	public double getX();
	public double getY();
	public boolean isEmpty();

}
