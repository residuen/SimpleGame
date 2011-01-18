package de.simpleGame.gameLogic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import de.simpleGame.gameComponents.XBall;
import de.simpleGame.gameComponents.XDiamant;
import de.simpleGame.gameComponents.XRectangle;
import de.simpleGame.interfaces.XShape;
import de.simpleGame.model.MapData;

/**
 * Erzeugt ein Spielfeld und liefert eine Shape-Liste zurueck
 * @author l_bettray
 *
 */
public class PlayfieldBuilder
{
	// Farbcodes fuer die Schlaeger
	public static Color[] BASH_COLORS = new Color[] { Color.CYAN, Color.GREEN, Color.MAGENTA,
													  Color.ORANGE, Color.PINK, Color.RED,
													  Color.YELLOW, Color.BLUE, new Color(15, 127, 15) };
	
	/*** Char-Kodierung der Spielfeldobjekte ***/
	// 's' -> leere Stelle
	// "1" -> Schlaeger
	// "2" -> Gegenstand
	public static String[] PLAYFIELD1 = new String[] {	"0000000000",
														"0000003211",
														"0000000211",
														"0000000021",
														"0000000232",
														"s000004449",
														"0000000232",
														"0000000021",
														"0000000211",
														"0000003211",
														"0000000000" };

	public static String[] PLAYFIELD2 = new String[] {	"0000012345",
														"0000003222",
														"0000000212",
														"0000002021",
														"0000022442",
														"s000024395",
														"0000022442",
														"0000002021",
														"0000000212",
														"0000003222",
														"0000012345" };

	public static String[] PLAYFIELD3 = new String[] {	"0000000000000",
														"0001123456000",
														"0000066666000",
														"0000000555000",
														"0000005557800",
														"0000087770200",
														"s004588980500",
														"0000087770200",
														"0000005557800",
														"0000000555000",
														"0000066666000",
														"0001123456000",
														"0000000000000" };
	
	public static String[] PLAYFIELD4 = new String[] {	"0000000000000",
														"0000000666600",
														"0000000665500",
														"0000007888570",
														"0000007887770",
														"s000007889870",
														"0000007887770",
														"0000007888570",
														"0000000665500",
														"0000000666600",
														"0000000000000" };
	
	public static String[][] FIELDS = new String[][] { PLAYFIELD1, PLAYFIELD2, PLAYFIELD3, PLAYFIELD4 };
	
	private MapData mapData = null;
	
	private Dimension panelSize = null;
	
	private Component playfield = null;
	
	public PlayfieldBuilder(Dimension panelSize, Component playfield)
	{
		this.panelSize = panelSize;
		this.playfield = playfield;
	}
	
	/**
	 * Auslesen des Zeichenketten-Arrays und erzeugen einer Shape-Liste
	 * @param playfield
	 * @return
	 */
	public boolean getPlayfieldObjects(String[] playfield, MapData mapData)
	{
		boolean retvalue = false;
		
		ArrayList<XShape> shapeList = new ArrayList<XShape>();
		shapeList.clear();
		
		double xStepsWidth = panelSize.getWidth() / playfield[0].length();	// "Rasterbreite" abhaengig von StringArray festlegen
		double yStepsHeight = panelSize.getHeight() / playfield.length;		// "Rasterhoehe" abhaengig von StringArray festlegen
		
		// Bool-Array, speichert ob sich ein Objekt in einer Gitterzelle befindet oder nicht
		boolean[][] shapeArray = new boolean[playfield.length][playfield[0].length()];
		
		// Array mit false vorinitialisieren
		for(int i=0; i<playfield.length; i++)
			for(int j=0; j<playfield[i].length(); j++)
				shapeArray[i][j] = false;
		
//		System.out.println("Spielfeld: ("+shapeArray[0].length+"x"+shapeArray.length+")");
		
		char [] charArr = null;		// char-Array zum speichern von Strings deklatrieren
		
		XShape shape = null;
		
		for(int i=0; i<playfield.length; i++)
		{
			charArr = playfield[i].toCharArray();	// Zeilenelement des Stringarrays in chararray ablegen
			
			for(int j=0; j < charArr.length; j++)
			{
				if(charArr[j] == 's')	// Schlaeger-Objekt erzeugen
				{
					shape = new XBall(j*xStepsWidth + 5, i*yStepsHeight + 5, yStepsHeight * 0.4, yStepsHeight * 0.4, -1); // Ball erzeugen
					shape.setShapeTyp(charArr[j]);	// Shape-Typ setzen
					shape.setFillColor(Color.GRAY);	// Schlaegerfarbe setzen
					
					shapeList.add(shape);
				}
				else
				{
//					System.out.println("charArr[j]="+charArr[j]);
					if(charArr[j] > '0' && charArr[j] < '9')	// Gegenstand erzeugen
					{
						shape = new XRectangle(j*xStepsWidth + 5, i*yStepsHeight + 3, xStepsWidth -  xStepsWidth * 0.1, yStepsHeight - yStepsHeight * 0.1  - 2, Integer.parseInt(""+charArr[j])); // Rechteck erzeugen
						shape.setShapeTyp(charArr[j]);	// Shape-Typ setzen
						shape.setFillColor(PlayfieldBuilder.BASH_COLORS[Integer.parseInt(""+charArr[j])]);
						shapeList.add(shape);
						
						shapeArray[i][j] = true;
					}
					else
					{
						if(charArr[j] == '9')	// Gegenstand erzeugen
						{
							shape = new XDiamant(j*xStepsWidth + 4, i*yStepsHeight + 3, xStepsWidth -  xStepsWidth * 0.1, yStepsHeight - yStepsHeight * 0.1  - 2, Integer.parseInt(""+charArr[j]), this.playfield); // Rechteck erzeugen
							shape.setShapeTyp(charArr[j]);	// Shape-Typ setzen
							shapeList.add(shape);
							
							shapeArray[i][j] = true;
						}
					}
				}
			}
		}
		
		for(int i=0; i<mapData.getShapeList().size(); i++)
			mapData.getShapeList().remove(i);
		
		mapData.setShapeList(shapeList);
		mapData.setShapeArray(shapeArray);
		
		retvalue = true;
			
		return retvalue;
	}
}
