/*
SimpleGame: A lection to become a simple breakout-clone

Copyright (C) 2010 Karsten Bettray, supported by DRS-Schule Neuwied (http://www.drsneuwied.de)

Dieses Programm ist freie Software. Sie koennen es unter den Bedingungen der GNU General Public License,
wie von der Free Software Foundation veroeffentlicht, weitergeben und/oder modifizieren, entweder gemaess
Version 3 der Lizenz oder (nach Ihrer Option) jeder spaeteren Version.
Die Veroeffentlichung dieses Programms erfolgt in der Hoffnung, dass es Ihnen von Nutzen sein wird, aber
OHNE IRGENDEINE GARANTIE, sogar ohne die implizite Garantie der MARKTREIFE oder der VERWENDBARKEIT FUER
EINEN BESTIMMTEN ZWECK. Details finden Sie in der GNU General Public License.
Sie sollten ein Exemplar der GNU General Public License zusammen mit diesem Programm erhalten haben.
Falls nicht, siehe <http://www.gnu.org/licenses/>.
*/
import de.simpleGame.gui.GuiBuilder;

/**
 * UPDATEs: 17
 * 
 * 1. Grundgeruest eines Spiels erstellen
 * 2. Auslagern der Ereignisse (z.B. Maus-Events) in separate Klasse MenuListener
 * 3. Einbinden eines generierten Spielfelds ueber die Klasse PlayfieldBuilder
 * 4. Erweiterte Grafikobjekte implementieren im package gameComponents und package interfaces
 * 		- Erstellen eines Interfaces XShape (im package interfaces)
 * 		- Anpassung der Liste in ArrayList<XShape>
 * 		- Erstellen eines erweiterten Rechteck-Objekts 'XRectangle' (im package gameComponents)
 * 5. Eventsteuerung und einfache Algorithmisierung der Schlaegerbewegung mit Hilfslinie. Klassen siehe:
 * 		- ShapeController
 * 		- PlayFieldListener (erweitert)
 * 6. Traegheitsbewegung des Schlaegers implementieren, siehe Klasse BasherMover
 * 7. Kollisionsabfrage zwischen Schlaeger und Gegenstaenden durch die Klasse CollisionsControl
 * 8. Anpassen des Playfieldbuilders
 * 9. Implementieren eines "blinkenden Steines" (Objekt XDiamand) und Erweiterung der Kollisionsabfrage
 * 10. Wechseln der Objektfarbe bei Kollision und verschwinden bei maximalen Treffern
 * 11. Implementierung eines Algorithmus zum Abprallen des Schlaegers
 * 12. Erfolgsmeldung bei Erreichen des Diamanten
 * 13. Anzeige eines Sekundenzaehlers fuer die Spieldauer
 * 14. Erweitern des Menues
 * 15. Erweitern Kollisionsfunktionen
 * 16. Anpassen der GUI und zufuegen neuer Spielfelder
 * 17. Refactoring + Debugging:
 * 		- Optimierung der Datenstrukturen fuer effektive Kollisionsueberpruefung
 * 		- Entfernen vom "Zittern" beim Bouncen
 * 
 * @author l_bettray
 */
public class Game
{

	public Game()
	{
		new GuiBuilder();	// Starten des GUI-Builders
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Game start = new Game();	// Starten des Programms mit Konstruktoraufruf
	}

}
