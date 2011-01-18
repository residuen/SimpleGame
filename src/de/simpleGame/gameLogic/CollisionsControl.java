package de.simpleGame.gameLogic;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import de.simpleGame.gameComponents.GamePanel;
import de.simpleGame.interfaces.XShape;
import de.simpleGame.model.MapData;

public class CollisionsControl
{
	private ArrayList<XShape> shapeList = null;
	
	private MapData mapData = null;
	
	private XShape basher = null;		// Temporaeres Shape-Objekt
	
//	private BallBouncer ballBouncer = null;
	
	private boolean game = true;	// Verhindern die Kollisionsabfrage ab, wenn Diamant erreicht wurde
	
	private GamePanel playField = null;

	private Point direction = new Point(0, 0);
	
	public CollisionsControl(ArrayList<XShape> shapeList, XShape basher, GamePanel playField, MapData mapData)
	{
		this.shapeList = shapeList;
		this.basher = basher;
		this.playField = playField;
		this.mapData = mapData;
	}
	
	/**
	 * Kollisionskontrolle:
	 * Wechselt die Fuellfarbe, nach einer vollstaendigen Kollision 
	 */
	public boolean collisionsHandling(double dx, double dy)
	{
		XShape s;
		
		boolean retValue = false;
		
		if(game)
		{
			for(int i=0; i< shapeList.size(); i++)
			{
				s = shapeList.get(i);
				
				if(s.getShapeTyp() != 's')
				{
	//				if(s.contains(basher.getBounds2D()))
					if(s.intersects(basher.getBounds2D()))	// Farbe auf weiss setzen, wenn Kollision
					{
						direction = detectedDirection(s, basher);
						
//						System.out.println("direction="+direction.toString());
						
						if(!s.isLocked())	// Wenn Objekt noch nicht beruehrt wurde ...
						{
							if(s.getShapeTyp() == '9')	// Wenn Diamant erreicht wurde, Erfolgsmeldung ausgeben und Diamant loeschen
							{
								// Timer stoppen
								playField.getTimer().cancel();
								
								// Textnachricht herausgeben
								JOptionPane.showMessageDialog(null, "Sie haben gewonnen!\n Benoetigte Zeit: "+playField.getSeconds()+" Sekunden.");
								
								// Levelzaehler um 1 erhoehen
								playField.getMenuListener().incLevelCounter();
								
								// Diamant loeschen
								shapeList.remove(i);
								
								game = false;	// Kolllisionsabfrage verhindert
								
								break;			// Schleife abbrechen
							}
							
							s.lock();		// ... dann das Objekt verriegeln
							s.incrementHits();	// ... und den Treffer-Zaehler um 1 erhoehen
							s.setFillColor(Color.WHITE);	// ... und die Fuellfarbe auf weiss setzen
	//						System.out.println(i+" betreten. Treffer-countdown: "+s.getDifHits());
							
							if(s.getDifHits() == 0)		// Loeschen des Objektes, wenn es maximal oft getroffen wurde
								shapeList.remove(i);
							
							retValue = true;
						}
						
						break;			// Schleife abbrechen
					}
					else
					{
						if(s.getFillColor().equals(Color.WHITE) && s.isLocked())		// Farbe zuruecksetzen, wenn Schlaeger das Objekt wieder verlaesst
						{
	//						System.out.println(i+" verlassen");
							s.unlock();						// Objekt entriegeln ...
							s.setFillColor(PlayfieldBuilder.BASH_COLORS[s.getDifHits()]);	// ... und die Fuellfarbe auf die naechst tiefere Farbe zuruecksetzen
						
							break;			// Schleife abbrechen
						}
					}
				}
			}
		}
		return retValue;
	}
	
	
	/**
	 * Ermittelt, von welcher Richtung und auf welche Seite des Objektes der Ball auftrifft
	 */
	private Point detectedDirection(XShape shape, XShape ball)
	{
		Point direction = new Point(0, 0);
		
		double shapeX = shape.getBounds().getX();
		double shapeY = shape.getBounds().getY();
		double shapeW = shapeX + shape.getBounds().getWidth();
		double shapeH = shapeY + shape.getBounds().getHeight();
		
		double ballX = ball.getBounds().getX();
		double ballY = ball.getBounds().getY();
		double ballW = ballX + ball.getBounds().getWidth();
		double ballH = ballY + ball.getBounds().getHeight();
		
//		System.out.println("(Stone/Ball: "+ballX+"/"+shapeX+"  " +ballY+"/"+shapeY+"  "+ballW+"/"+shapeW+"  "+ballH+"/"+shapeH+")");
	
		// Test fuer links
		if(ballX <= shapeX && ballW >= shapeX)
			direction.x = 1; // System.out.println("Links");
		
		// Test fuer oben
		if(ballY <= shapeY && ballH >= shapeY)
			direction.y = 1; // System.out.println("Oben");
		
		// Test fuer rechts
		if(ballX <= shapeW  && ballW >= shapeW)
			direction.x = -1; // System.out.println("Rechts");

		// Test fuer unten
		if(ballY <= shapeH && ballH >= shapeH)
			direction.y = -1; // System.out.println("Unten");
	
		return direction;
	}

	public Point getDirection()
	{
		return direction;
	}

}
