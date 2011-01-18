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
public class ExtendedBallBouncer extends Thread
{
//	final double FAKTOR = 0.25; 		// Beeinflusst die Staerke des Abprallverhaltens

	private XShape basher = null;		// Referenz auf Schlaeger-Objekt

	private double dx, dy;				// Differenz zwischen Schlaeger und Mausposition

	private JPanel playField = null;	// Referenz auf Spielfeld

	private Point direction = null;		// beinhaltet die Kollisionsrichtung
	
	private double repulsion = 0.25;	// Faktor Impulsverhalten des Balles

	/**
	 * Uebernimmt relevante Eigenschaften und Referenzen
	 * @param basher
	 * @param dx
	 * @param dy
	 * @param playField
	 */
	public ExtendedBallBouncer(XShape basher, double dx, double dy, JPanel playField, Point direction, double repulsion)
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
		double x = 0, y = 0;
		double cosinus = 0;
		
//		System.out.println(direction.toString());
		
		for(int i=0; i<=90; i+=4)	// Schleife fuer Kosinus von 0 - 90°
		{
			cosinus = Math.cos(Math.toRadians(i));	// Kosinus des Winkels i
			x = repulsion * dx * cosinus;			// Berechen des Abprallens in x-Richtung
			y = repulsion * dy * cosinus;			// Berechen des Abprallens in y-Richtung
			
			// Vorzeichen fuer Abprallen fuer oben und unten
			if(direction.x == 0 && (direction.y == 1 || direction.y == -1) )
			{
				x = -x; // Berechen des Abprallens in x-Richtung
			}
			else
				// Vorzeichen fuer Abprallen fuer links und rechts
				if(direction.y == 0 && (direction.x == 1 || direction.x == -1) )
				{
					y = -y;	// Berechen des Abprallens in y-Richtung
				}
				else
					// Vorzeichen fuer Abprallen fuer Ecken links oben und unten
					if(direction.x == 1 && (direction.y == 1 || direction.y == -1 ) )
					{
//						System.out.println("dx="+dx);
						if(!(dx >= 0)) 
							x = -x;	// Berechen des Abprallens in x-Richtung					
					}
					else
						// Vorzeichen fuer Abprallen fuer Ecken rechts oben und unten
						if(direction.x == -1 && (direction.y == 1 || direction.y == -1 ) )
						{
//							System.out.println("dx="+dx);
							if(dx >= 0) 
								x = -x;	// Berechen des Abprallens in x-Richtung
						}

			// Schreiben der neuen Koordinaten-Daten in das Schlaeger-Objekt
			basher.setRect(basher.getX() - x, basher.getY() - y, basher.getWidth(), basher.getHeight());
			
			playField.repaint();
			
			// Wartezeit, bis Neuberechnung der Schlaegerposition durchgefuehrt wird
			try { sleep(25); }
			catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
}

