package de.simpleGame.gameLogic;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import de.simpleGame.gameComponents.GamePanel;
import de.simpleGame.interfaces.XShape;
import de.simpleGame.model.MapData;

/**
 * Controller fuer die grafischen Objekte, sowohl Schlaeger und Gegendstaende
 * @author bettray
 *
 */
public class ShapeController
{
	private ArrayList<XShape> shapeList = null;
	
	private XShape basher = null;
	
	private GamePanel playField = null;
	
	private Point mausPosition = new Point(0, 0);	// Koordinanten des Mauscursors
	
	private BasherMover basherMover = null;			// Bewegungs-Thread fuer den Schlaeger
	
	public ShapeController(ArrayList<XShape> shapeList, GamePanel playField, MapData mapData)
	{
		this.shapeList = shapeList;
		this.playField = playField;
		
		fetchBasher(mapData);	// Aufruf zum ermitteln des Schlaegers
	}

	/**
	 * Ermitteln und zwischenspeichern des Schlaegers
	 */
	private void fetchBasher(MapData mapData)
	{
		for(XShape s : shapeList)
		{
			if(s.getShapeTyp() == 's')	// Shapetyp 's' ist der Schlaeger
			{
				basher = s;
			}
		}
		
		basherMover = new BasherMover(shapeList, basher, mausPosition ,playField, mapData);	// Bewegungs-Thread fuer den Schlaeger initialisieren
		basherMover.start();
	}
	
	public Point getMausPosition() {
		return mausPosition;
	}

	public XShape getBasher() {
		return basher;
	}
	
	public BasherMover getBasherMover() {
		return basherMover;
	}


	public void mouseMoved(MouseEvent arg0)
	{
		Point p = arg0.getPoint();
		
		mausPosition.setLocation(p);	// Koordinanten des Mauscursors setzen
		
		basherMover.setMousePosition(mausPosition);	// Koordinanten des Mauscursors setzen
		
		playField.repaint();
	}
}
