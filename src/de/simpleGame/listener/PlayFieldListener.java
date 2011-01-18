package de.simpleGame.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

import de.simpleGame.gameComponents.GamePanel;
import de.simpleGame.gameLogic.ShapeController;
import de.simpleGame.interfaces.XShape;

/**
 * 
 * @author bettray
 */
public class PlayFieldListener implements MouseMotionListener, MouseListener
{
	private XShape basher = null;	// der Schlaeger-Shape
	
	private ShapeController shapeController = null;	// Kontrolliert die Shape-Objekte
	
	private GamePanel playField = null;
	
	public PlayFieldListener(GamePanel playField, ShapeController shapeController) // (ArrayList<XShape> shapeList, 
	{
		this.playField = playField;
		this.shapeController = shapeController;
	}
	
	public ShapeController getShapeController() {
		return shapeController;
	}

	// MouseMotionListener Ereignisse
	
	/**
	 * Mousereignis an Controller weiterleiten
	 */
	@Override
	public void mouseMoved(MouseEvent arg0)
	{
		if(playField.isRubberBand())
			playField.revalidate();
		
		shapeController.mouseMoved(arg0);
	}

	/**
	 * Mousereignis an Controller weiterleiten
	 */
	@Override
	public void mouseDragged(MouseEvent arg0)
	{
		if(playField.isRubberBand())
			playField.revalidate();
		
		shapeController.mouseMoved(arg0);
	}

	// MouseListener Ereignisse
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}