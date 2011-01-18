package de.simpleGame.gameComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import de.simpleGame.gameLogic.CollisionsControl;
import de.simpleGame.gameLogic.PlayfieldBuilder;
import de.simpleGame.gameLogic.ShapeController;
import de.simpleGame.interfaces.XShape;
import de.simpleGame.listener.MenuListener;
import de.simpleGame.listener.PlayFieldListener;
import de.simpleGame.model.MapData;

/**
 * Panel zur Darstellung der Spielegrafik,
 * zeigt zunaechst nur ein Kreuz an
 * @author l_bettray
 *
 */
public class GamePanel extends JPanel
{
	private ArrayList<XShape> shapeList = null;
	
	private MapData mapData = null;		// Daten mit Spiel(feld)informationen
	
	private PlayFieldListener playFieldListener = null;
	
	private ShapeController shapeController = null;	// Kontrolliert die Shape-Objekte
	
	private Timer timer = null;
	
	private MenuListener menuListener = null;
	
	private String timerText = "Zeit: ";
	
	private long starttime, seconds = 0;
	
	private boolean rubberBand = false;	// Hilfsvariable als BEdingung fuer Hilfslinie
	
	public int testId = 0;
	
	/**
	 * Konstuktor erhaelt die Groesse des Uebergeordneten Panels
	 * @param panelSize
	 */
	public GamePanel(Dimension panelSize, String[] playFieldData, MenuListener menuListener, MapData mapData)
	{
		this.menuListener = menuListener;
		this.mapData = mapData;
		
		initPanel(panelSize, playFieldData);	// Panelgroesse uebergeben
	}
	
	/**
	 * Derzeit ungenutzt
	 */
	private void initPanel(Dimension panelSize, String[] playFieldData)
	{
		this.timer = new Timer();

		PlayfieldBuilder pb = new PlayfieldBuilder(panelSize, this);	// Klasse zum erzeugen der Spielfeldobjekte
		
		pb.getPlayfieldObjects(playFieldData, mapData);			// Spielfeldobjekte erzeugen und in Liste speichern
		
		this.shapeList = mapData.getShapeList();				// Spielfeldobjekte der Shape-Liste uebergeben
		
		this.shapeController = new ShapeController(shapeList, this, mapData);

		this.playFieldListener = new PlayFieldListener(this, this.shapeController);	// shapeList, 				// Listener erhaelt Refenrenz auf alle die Shape-Liste

		// Zufuegen des Listeners um Maus-Klick und Mausbewegung zu erfassen
		addMouseListener(playFieldListener);
		addMouseMotionListener(playFieldListener);
		
		starttime = System.currentTimeMillis();
		
		timer.schedule(new Task(), 100, 100 );
	}
	
	public void paint(Graphics g)
	{
		super.paintComponents(g);
		
//		System.out.println(testId);
		
		XShape shape = null;	// Temporaerer Shape
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.clearRect(0, 0, getWidth(), getHeight());	// Loeschen des aktuellen Bildinhalts
		
		// Hintergrund fuer Anzeige der gespielten Sekunden
		g2.setColor(Color.YELLOW);
		g2.fillRect(10, 10, 80, 25);
		
		for(int i=0; i<shapeList.size(); i++)	// Shapes aus Liste auslesen und zeichnen
		{
			shape = shapeList.get(i);			// Shape aus Liste holen
			
			g2.setColor(shape.getFillColor());	// Fuellfarbe auslesen und festlegen
			g2.fill(shape);						// Flaeche zeichnen

			g2.setColor(Color.BLACK);			// Rahmenfarbe festlegen
			g2.draw(shape);						// Rahmen zeichnen
		}
		
		shape = playFieldListener.getShapeController().getBasher();
		
		// Zeichen einer Hilfslinie, wenn test==true
		if(rubberBand)
			g2.draw(new Line2D.Double(shape.getBounds2D().getCenterX(), shape.getBounds2D().getCenterY(), playFieldListener.getShapeController().getMausPosition().getX(), playFieldListener.getShapeController().getMausPosition().getY()));

		// Zeichnen der gespielten Sekunden
		g2.drawRect(10, 10, 80, 25);
		g2.drawString(timerText + seconds/10 + "." +seconds%10, 25, 25);
	}
	
	public ShapeController getShapeController() {
		return shapeController;
	}

	public float getSeconds()
	{
		return Float.parseFloat(seconds/10 + "." +seconds%10);
	}
	
	/**
	 * Abfrage des Gummiband-Flags
	 * @return
	 */
	public boolean isRubberBand() {
		return rubberBand;
	}

	/**
	 * Setzen des Gummiband-Status
	 * @param rubberBand
	 */
	public void setRubberBand(boolean rubberBand) {
		this.rubberBand = rubberBand;
	}

	public Timer getTimer() {
		return timer;
	}

	public MenuListener getMenuListener() {
		return menuListener;
	}
	
	/**
	 * Stoppen und entfernen der Timer-Referenz
	 */
	@Override
	protected void finalize() throws Throwable
	{
		super.finalize();
		
		timer.cancel();
		
		timer = null;
	}

	/**
	 * Timer-Objekt, sorgt fuer eine aktualisierung der Spieldauer
	 * @author bettray
	 *
	 */
	private class Task extends TimerTask  
	{
		public void run()  
		{
			seconds = (System.currentTimeMillis() - starttime) / 100;
		}
	}
}
