package de.simpleGame.gameComponents;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import de.simpleGame.interfaces.XShape;

public class XRectangle implements XShape
{
	private Color fillColor = Color.green;
	
	private Rectangle2D rectangle = null;
	
	private char typ = 0;
	
	private int hits = 0;		// maximale Treffer
	
	private int hitCount = 0;	// aktuelle Anzahl der Treffer
	
	private boolean lock = false;
	
	public XRectangle(double x, double y, double w, double h, int hits)
	{
		this.rectangle = new Rectangle2D.Double(x, y, w, h);
		this.hits = hits;
	}

	public XRectangle(double x, double y, double w, double h, int hits, char typ)
	{
		this.rectangle = new Rectangle2D.Double(x, y, w, h);
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
		return this.hitCount;
	}

	@Override
	public void incrementHits()
	{
		this.hitCount++;
	}

	@Override
	public int getDifHits()
	{
		return hits - hitCount;
	}

	@Override
	public boolean isLocked()
	{
		return this.lock;
	}

	@Override
	public void lock()
	{
		this.lock = true;
	}

	@Override
	public void unlock()
	{
		this.lock = false;
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
