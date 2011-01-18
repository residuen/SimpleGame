package de.simpleGame.gameLogic;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.simpleGame.gameComponents.GamePanel;
import de.simpleGame.interfaces.XShape;
import de.simpleGame.model.MapData;

public class BasherMover extends Thread
{
	// Konstanten fuer die Auswahl der verschiedenen Bouncer-Typen
	public final int SIMPLE_BOUNCER = 0;
	public final int EXTENDED_BOUNCER = 1;

	private final int SPEED = 40;
	
	private double repulsion = 40;
	
	private double dx1 = 0, dx2 = 0, dy1 = 0, dy2 = 0, dx = 0, dy = 0;
	
	private int bouncerTyp = SIMPLE_BOUNCER;	// Ausgewaehlter Bouncer
	
	private Point mausPosition = null;			// Koordinanten des Mauscursors

	private ArrayList<XShape> shapeList = null;	// Liste der Shapes
	
	private XShape basher = null;				// Temporaeres Shape-Objekt
	
	private CollisionsControl collisionsControl = null;	// Kontrolliert die Kollisions-Ereignisse

	private GamePanel playField = null;	// Referenz auf Spielfeld

	public BasherMover(ArrayList<XShape> shapeList, XShape basher, Point mausPosition, GamePanel playField, MapData mapData)
	{
		this.shapeList = shapeList;
		this.basher = basher;
		this.mausPosition = mausPosition;
		this.playField = playField;
		
		collisionsControl = new CollisionsControl(shapeList, this.basher, playField, mapData); // Initialisierung der Kollisionskontrolle
	}
	
	/**
	 * Setzen der Mausposition
	 * @param mausPosition
	 */
	public void setMousePosition(Point mausPosition)
	{
		this.mausPosition.setLocation(mausPosition);
	}
	
	/**
	 * Setzen des Bouncer-Typs
	 * @param bouncerTyp
	 */
	public void setBouncerTyp(int bouncerTyp)
	{
		this.bouncerTyp = bouncerTyp;
	}

	/**
	 * Setzt den Faktor fuer das Impulsverhalten des Balles
	 * @param repulsion
	 */
	public void setRepulsion(double repulsion) {
		this.repulsion = repulsion;
	}

	@Override
	public void run()
	{
		while(!isInterrupted())
		{
			// Ermitteln des Abstandes zwischen Schlaeger und Mauszeiger
			// y-Abstand
			dy1 = basher.getBounds2D().getCenterY();
			dy2 = mausPosition.getY();
			dy = dy2 - dy1;		// Y-Abstandes zwischen Schlaeger und Mauszeiger
			// x-Abstand
			dx1 = basher.getBounds2D().getCenterX();
			dx2 = mausPosition.getX();
			dx = dx2 - dx1;		// X-Abstandes zwischen Schlaeger und Mauszeiger
			
//			System.out.println("Start: Basher moved. dx="+dx+" dy="+dy);
			
			// Wartezeit, falls Kugel steht, dient der Entlastung des Systems
			try { sleep(50); }
			catch (InterruptedException e) { e.printStackTrace(); }
			
			while(!((int)dy==0) || !((int)dx==0))
			{
				mover();
			}
		}
	}
	
	private void mover()
	{
		// Ermitteln des Abstandes zwischen Schlaeger und Mauszeiger
		// y-Abstand
		dy1 = basher.getBounds2D().getCenterY();
		dy2 = mausPosition.getY();
		dy = dy2 - dy1;		// Y-Abstandes zwischen Schlaeger und Mauszeiger
		// x-Abstand
		dx1 = basher.getBounds2D().getCenterX();
		dx2 = mausPosition.getX();
		dx = dx2 - dx1;		// X-Abstandes zwischen Schlaeger und Mauszeiger
		
		// Wartezeit, falls Kugel steht, dient der Entlastung des Systems
		try { sleep(50); }
		catch (InterruptedException e) { e.printStackTrace(); }
		
		while((!((int)dy==0) || !((int)dx==0)))
		{
			// Ermitteln des Abstandes zwischen Schlaeger und Mauszeiger
			// y-Abstand
			dy1 = basher.getBounds2D().getCenterY();
			dy2 = mausPosition.getY();
			dy =dy2 - dy1;		// Y-Abstandes zwischen Schlaeger und Mauszeiger
			// x-Abstand
			dx1 = basher.getBounds2D().getCenterX();
			dx2 = mausPosition.getX();
			dx = dx2 - dx1;		// X-Abstandes zwischen Schlaeger und Mauszeiger
			
			// Algoritmus zum annaehern an Mauszeiger mittels f(x) = xPosition_alt + a*Abstand/breite_oder_hoehe_Spielfeld
			basher.setRect(basher.getX() + SPEED*(dx / playField.getWidth()), basher.getY() + SPEED*(dy / playField.getHeight()), basher.getWidth(), basher.getHeight());
			
			if(collisionsControl.collisionsHandling(dx, dy))	// Aufrufen der Kollisionskontrolle
			{
				bouncer(collisionsControl.getDirection());	// Wenn Kollision erfolgt, dann rufe den Bouncer auf
			}
			
			playField.repaint();
			
			// Wartezeit, bis Neuberechnung der Schlaegerposition durchgefuehrt wird
			try { sleep(25); }
			catch (InterruptedException e) { e.printStackTrace(); }
		}

	}
	
	/**
	 * Erzeugt Bouncer-Objekt, um das Abprallen von einem Objekt zu simulieren
	 */
	private void bouncer(Point direction)
	{
		Thread ballBouncer = null;
		
//		System.out.println("bouncerTyp="+bouncerTyp);
		
		if(bouncerTyp == SIMPLE_BOUNCER)
			ballBouncer = new BallBouncer(basher, dx, dy, playField, direction, repulsion);
		else
			ballBouncer = new ExtendedBallBouncer(basher, dx, dy, playField, direction, repulsion);
		
		ballBouncer.start();
		
//		try { ballBouncer.join(); } 
//		catch (InterruptedException e) { e.printStackTrace(); }
		
		playField.repaint();
	}
}
