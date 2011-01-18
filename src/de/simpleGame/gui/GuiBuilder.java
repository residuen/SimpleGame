package de.simpleGame.gui;

import java.util.Map;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.simpleGame.model.MapData;

/**
 * GuiBuilder: Die für die GUI benoetigten grafischen Komponenten
 * wie z.B. Hauptfenster (JFrame), Menue (JMenueBar), strukturierende
 * Panels (JPanel) etc., werden hier zentral zusammengefuegt
 * @author l_bettray
 *
 */
public class GuiBuilder {
	public GuiBuilder()
	{
		initLookAndFeel();
		
		MapData mapData = new MapData();
		
		MainFrame mainFrame = new MainFrame("Mainframe");	// Hauptfenster
		
		MainPanel mainPanel = new MainPanel();			// Hauptpanel, in dem alle weiteren Komponenten angezeigt werden
		
		MainMenu mainMenu = new MainMenu(mainPanel, mapData);	// Das Menue im Programmkopf
		
		mainFrame.setJMenuBar(mainMenu);				// Einhaengen des Menues in das hauptfenster
//
		mainFrame.getContentPane().add(mainPanel);		// Einhaengen des Hauptpanels in dasHauptfenster
//
		mainFrame.setVisible(true);						// Hauptfenster sichtbar machen
	}

	/**
	 * Setzt das Look & Feel der Anwendung fest
	 */
	private void initLookAndFeel() {
		// Setzen des Look & Feels auf die System-Optik
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
