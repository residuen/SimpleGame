package de.simpleGame.gameLogic;

import java.awt.Point;

import javax.swing.JPanel;

import de.simpleGame.interfaces.XShape;

/**
 * Simuliert das Abprallen des Balles von den Objekten.
 * Lehnt sich an Wurfparabel mit Kosinus-Funktion an.
 * @author bettray
 *
 */
public class BallBouncer extends Thread
{
//	final double FAKTOR = 0.25; 		// Beeinflusst die Staerke des Abprallverhaltens

	private XShape basher = null;		// Referenz auf Schlaeger-Objekt

	private double dx, dy;				// Differenz zwischen Schlaeger und Mausposition

	private JPanel playField = null;	// Referenz auf Spielfeld
	
	private Point direction = null;		// beinhaltet die Kollisionsrichtung
	
	private  double repulsion = 0.25;	// Faktor Impulsverhalten des Balles

	/**
	 * Uebernimmt relevante Eigenschaften und Referenzen
	 * @param basher
	 * @param dx
	 * @param dy
	 * @param playField
	 */
	public BallBouncer(XShape basher, double dx, double dy, JPanel playField, Point direction, double repulsion)
	{
		this.basher = basher;
		this.dx = dx;
		this.dy = dy;
		this.playField = playField;
		this.direction = direction;
		this.repulsion= repulsion; 
	}
	
	/**
	 * Neuberechnung der Ballposition abhaengig von Flugrichtung
	 */
	public void run()
	{
		double x, y;
		
		for(int i=0; i<=90; i+=4)	// Schleife fuer Kosinus von 0 - 90°
		{			
			x =   repulsion * dx * Math.cos(Math.toRadians(i));	// Berechen des Abprallens in x-Richtung
			y = - repulsion * dy * Math.cos(Math.toRadians(i));	// Berechen des Abprallens in y-Richtung	
			
			// Schreiben der neuen Koordinaten-Daten in das Schlaeger-Objekt
			basher.setRect(basher.getX() - x, basher.getY() - y, basher.getWidth(), basher.getHeight());
			
			playField.repaint();
			
			// Wartezeit, bis Neuberechnung der Schlaegerposition durchgefuehrt wird
			try { sleep(25); }
			catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
}

