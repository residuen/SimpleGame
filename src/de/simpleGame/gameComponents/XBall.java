package de.simpleGame.gameComponents;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import de.simpleGame.interfaces.XShape;

public class XBall implements XShape
{
	private Color fillColor = Color.GRAY;
	
	private Arc2D arc = null;
	
	private char typ = 0;
	
	private int hits = 0;
	
	private int hitCount = 0;
	
	public XBall(double x, double y, double w, double h, int hits)
	{
		this.arc = new Arc2D.Double(x, y, w, h, 0, 360, Arc2D.CHORD);
		this.hits = hits;
	}

	public XBall(double x, double y, double w, double h, int hits, char typ)
	{
		this.arc = new Arc2D.Double(x, y, w, h, 0, 360, Arc2D.CHORD);
		this.hits = hits;
		this.typ = typ;
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

	// Methoden aus dem Interfaceteil durch Shape in XShape
	@Override
	public boolean contains(Point2D p) {
		return arc.contains(p);
	}

	@Override
	public boolean contains(Rectangle2D r) {
		return arc.contains(r);
	}

	@Override
	public boolean contains(double x, double y) {
		return arc.contains(x, y);
	}

	@Override
	public boolean contains(double x, double y, double w, double h) {
		return arc.contains(x, y, w, h);
	}

	@Override
	public Rectangle getBounds() {
		return arc.getBounds();
	}

	@Override
	public Rectangle2D getBounds2D() {
		return arc.getBounds2D();
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		return arc.getPathIterator(at);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return arc.getPathIterator(at, flatness);
	}

	@Override
	public boolean intersects(Rectangle2D r) {
		return arc.intersects(r);
	}

	@Override
	public boolean intersects(double x, double y, double w, double h) {
		return arc.intersects(x, y, w, h);
	}

	// Methoden des Interfaces, die aus der Klasse Rectangle2D uebernommen werden. Bei Gelegenheit werden die Methoden noch "aussortiert"
	@Override
	public Rectangle2D createIntersection(Rectangle2D arg0) {
		return null; // rectangle.createIntersection(arg0);
	}

	@Override
	public Rectangle2D createUnion(Rectangle2D arg0) {
		return null; // rectangle.createUnion(arg0);
	}

	@Override
	public int outcode(double arg0, double arg1) {
		return 0; // arc.outcode(arg0, arg0);
	}

	@Override
	public void setRect(double x, double y, double w, double h) {
		arc.setArc(x, y, w, h, 0, 360, Arc2D.CHORD);
	}

	@Override
	public double getHeight() {
		return arc.getHeight();
	}

	@Override
	public double getWidth() {
		return arc.getWidth();
	}

	@Override
	public double getX() {
		return arc.getX();
	}

	@Override
	public double getY() {
		return arc.getY();
	}

	@Override
	public boolean isEmpty() {
		return arc.isEmpty();
	}

}
