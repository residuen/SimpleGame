package de.simpleGame.gameComponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import de.simpleGame.interfaces.XShape;

public class XDiamant extends Thread implements XShape
{
	private Color fillColor = Color.green;
	
	private Rectangle2D rectangle = null;
	
	private Component playfield = null;
	
	private char typ = 0;
	
	private int hits = 0;
	
	private int hitCount = 0;
	
//	private boolean lock = false;
	
	public XDiamant(double x, double y, double w, double h, int hits, Component playfield)
	{
		this.rectangle = new Rectangle2D.Double(x, y, w, h);
		this.hits = hits;
		this.playfield = playfield;
		
		this.start();
	}

	public XDiamant(double x, double y, double w, double h, char typ, int hits, Component playfield)
	{
		this.rectangle = new Rectangle2D.Double(x, y, w, h);
		this.hits = hits;
		this.playfield = playfield;
		this.typ = typ;
		
		this.start();
	}

	/**
	 * Objektfarbe holen
	 */
	@Override
	public Color getFillColor() {
		return fillColor;
	}
	
	/**
	 * Objektfarbe setzen
	 */
	@Override
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	/**
	 * Objekt-Typ holen
	 */
	public char getShapeTyp()
	{
		return typ;
	}
	
	/**
	 * Objekttyp setzen
	 */
	public void setShapeTyp(char typ)
	{
		this.typ = typ;
	}
	
	@Override
	public int getHits()
	{
		return this.hits;
	}

	@Override
	public void incrementHits()
	{
		this.hits++;
	}

	@Override
	public int getDifHits()
	{
		return -1;
	}

	@Override
	public boolean isLocked()
	{
		return false;
	}

	@Override
	public void lock()
	{
		// ...
	}

	@Override
	public void unlock()
	{
		// ...
	}

	/**
	 * run-Methode, ueberschreibt run der vererbten Klasse Thread
	 */
	@Override
	public void run()
	{
		while(!isInterrupted())	// Endlosschleife fuer Fuellfarbenwechsel
		{			
			for(int i=0; i<=255; i+=20)	// Farbwechsel von weiss nach schwarz
			{
				fillColor = new Color(i, i, i);	// neue Farbe zuordnen
				
				playfield.repaint();	// Spielfeld neu zeichnen

				try { sleep(100); }		// 100ms Wartezeit
				catch (InterruptedException e) { e.printStackTrace(); }
			}
			
			for(int i=255; i>=0; i-=20)	// Farbwechsel von weiss nach schwarz
			{
				fillColor = new Color(i, i, i);	// neue Farbe zuordnen

				playfield.repaint();	// Spielfeld neu zeichnen

				try { sleep(100); }		// 100ms Wartezeit
				catch (InterruptedException e) { e.printStackTrace(); }
			}
				
		}
	}

	// Methoden aus dem Interfaceteil durch Shape in XShape
	@Override
	public boolean contains(Point2D p) {
		return rectangle.contains(p);
	}

	@Override
	public boolean contains(Rectangle2D r) {
		return rectangle.contains(r);
	}

	@Override
	public boolean contains(double x, double y) {
		return rectangle.contains(x, y);
	}

	@Override
	public boolean contains(double x, double y, double w, double h) {
		return rectangle.contains(x, y, w, h);
	}

	@Override
	public Rectangle getBounds() {
		return rectangle.getBounds();
	}

	@Override
	public Rectangle2D getBounds2D() {
		return rectangle.getBounds2D();
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		return rectangle.getPathIterator(at);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return rectangle.getPathIterator(at, flatness);
	}

	@Override
	public boolean intersects(Rectangle2D r) {
		return rectangle.intersects(r);
	}

	@Override
	public boolean intersects(double x, double y, double w, double h) {
		return rectangle.intersects(x, y, w, h);
	}

	// Methoden des Interfaces, die aus der Klasse Rectangle2D uebernommen werden. Bei Gelegenheit werden die Methoden noch "aussortiert"
	@Override
	public Rectangle2D createIntersection(Rectangle2D arg0) {
		return rectangle.createIntersection(arg0);
	}

	@Override
	public Rectangle2D createUnion(Rectangle2D arg0) {
		return rectangle.createUnion(arg0);
	}

	@Override
	public int outcode(double arg0, double arg1) {
		return rectangle.outcode(arg0, arg0);
	}

	@Override
	public void setRect(double x, double y, double w, double h) {
		rectangle.setRect(x, y, w, h);
	}

	@Override
	public double getHeight() {
		return rectangle.getHeight();
	}

	@Override
	public double getWidth() {
		return rectangle.getWidth();
	}

	@Override
	public double getX() {
		return rectangle.getX();
	}

	@Override
	public double getY() {
		return rectangle.getY();
	}

	@Override
	public boolean isEmpty() {
		return rectangle.isEmpty();
	}

}
