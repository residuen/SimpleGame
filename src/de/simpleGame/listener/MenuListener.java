package de.simpleGame.listener;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Locale;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

import de.simpleGame.fileTools.FileHandler;
import de.simpleGame.gameComponents.GamePanel;
import de.simpleGame.gameLogic.BasherMover;
import de.simpleGame.gameLogic.PlayfieldBuilder;
import de.simpleGame.gui.MainMenu;
import de.simpleGame.gui.MainPanel;
import de.simpleGame.model.MapData;

public class MenuListener implements ActionListener, MouseListener
{
	private MainPanel mainPanel = null;	// Referenz auf das Mainpanel
	
	private MainMenu mainMenu = null;	// Referenz auf das Hauptmenue
	
	private MapData mapData = null;		// Daten mit Spiel(feld)informationen
	
	private int levelCounter = 0;		// Zaehlt mit, welche Level erfolgreich beendet wurden
	
	private int testCounter = 0;
	
	private int bouncerTyp = 0;
	
	private double repulsion = 0.25;
	
	public MenuListener(MainPanel mainPanel,  MainMenu mainMenu, MapData mapData)	// , BasherMover basherMover
	{
		this.mainPanel = mainPanel;	// Referenz auf das Mainpanel
		this.mainMenu = mainMenu;
		this.mapData = mapData;
	}
	
	/**
	 * Erhoeht den Zaehler des Level-Counters
	 */
	public void incLevelCounter()
	{
		if(levelCounter < PlayfieldBuilder.FIELDS.length)
			this.levelCounter++;
	}
	
	private void initNewGame(String[] field)
	{
		// Altes GamePanel inkl. Timer loeschen
		if(mainPanel.getGamePanel() != null)
		{
			mainPanel.getGamePanel().getTimer().cancel();
			mainPanel.getGamePanel().removeAll();
			mainPanel.addGamePanel(null);
			mainPanel.getGameContainer().removeAll();	// Spielfeld aus Hauptpanel entfernen
		}

		// Neues GamePanel erzeugen
		GamePanel gp = new GamePanel(new Dimension(mainPanel.getWidth(), mainPanel.getHeight()), field, this, this.mapData);	// Spielfeld-Panel erzeugen
		
		gp.testId = testCounter++;
		
		// Zustand des Gummiband-Flags setzen
		gp.setRubberBand(mainMenu.getRubberBand().getState());	// Flag zum An/Abschalten des Gummibandes setzen
		
		mainPanel.addGamePanel(gp);					// Neues Spielfeld einhaengen
		mainPanel.getGamePanel().getShapeController().getBasherMover().setBouncerTyp(bouncerTyp);	// Bouncer-Typ setzen
		mainPanel.getGamePanel().getShapeController().getBasherMover().setRepulsion(repulsion);		// Setzt den Faktor fuer das Impulsverhalten des Balles
		mainPanel.revalidate();						// Hauptpanel neu zeichnen
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getSource() instanceof JRadioButtonMenuItem)
			checkBoxEvents(arg0);
		else
			if(arg0.getActionCommand().equals("About"))	// Ausgabe einer Lizenz-Onfo
				JOptionPane.showMessageDialog(null, "This is GPL3.0 software!");
			else
				if(arg0.getActionCommand().equals("Load maplist"))	// Ausgabe einer Lizenz-Onfo
				{
					loadMapFile();
				}
				else
					if(arg0.getActionCommand().contains("New level"))	// das naechste "Spielfeld" erzeugen und einhaengen
					{
						if(levelCounter >= PlayfieldBuilder.FIELDS.length)
							initNewGame(PlayfieldBuilder.FIELDS[PlayfieldBuilder.FIELDS.length - 1]);
						else
							initNewGame(PlayfieldBuilder.FIELDS[levelCounter]);
					}
					else
						if(arg0.getActionCommand().contains("Playfield"))	// Ein ausgesuchtes "Spielfeld" erzeugen und einhaengen
						{
							// Name des Menueeintrages auslesen und fuer Auswahl des Spielfeldes verwenden
							JMenuItem mi = (JMenuItem)arg0.getSource();
							
							int index = Integer.parseInt(mi.getName());
							
							initNewGame(PlayfieldBuilder.FIELDS[index]);
						}
						else
							if(arg0.getActionCommand().equals("Rubber band"))	// Gummiband ein-, bzw. ausschalten
							{
								if(mainPanel.getGamePanel() != null)
									mainPanel.getGamePanel().setRubberBand(((JCheckBoxMenuItem)(arg0.getSource())).getState());
							}
							else
								if(arg0.getActionCommand().equals("Ranking"))	// Ranking anzeigen
									System.out.println("Jo Brassa, here's your rankingfile: "+(new File("")).getAbsolutePath());
								else
									if(arg0.getActionCommand().equals("Exit"))	// Programm beenden
										System.exit(0);
	}
	
	private void loadMapFile()
	{
		System.out.println("LoadMapFile");
		
		JFileChooser jfc = new JFileChooser();
	
		jfc.setCurrentDirectory(new File(System.getProperty("user.home").replace("\\", "/") + "/Desktop"));
		System.out.print(jfc.getCurrentDirectory());
		
		int fileState = jfc.showOpenDialog(null);
		
		if(fileState == 0)
		{
			System.out.println(" - fileState=" + fileState + " - file="+jfc.getSelectedFile());
			
			String[] map = FileHandler.getTextFile(jfc.getSelectedFile()).split("\n");
			
//			System.out.println(FileHandler.getTextFile(jfc.getSelectedFile()).split("\n"));
			
			initNewGame(map);
			
		}
		else
			System.err.println("Error while selecting file!");
	}
	
	private void checkBoxEvents(ActionEvent arg0)
	{
		System.out.println(arg0.getActionCommand());
		
		JRadioButtonMenuItem rb = (JRadioButtonMenuItem)arg0.getSource();
		
		if(arg0.getActionCommand().equals("BallBouncer"))
			bouncerTyp = 0;
		else
			if(arg0.getActionCommand().equals("ExtendedBallBouncer"))
				bouncerTyp = 1;
			else
				if(arg0.getActionCommand().contains("repulsion"))
				{
					repulsion = Double.parseDouble(rb.getActionCommand().split(" ")[1]) / 16d;
				}
		
		if(mainPanel.getGamePanel() != null)
		{
			mainPanel.getGamePanel().getShapeController().getBasherMover().setBouncerTyp(bouncerTyp);
			mainPanel.getGamePanel().getShapeController().getBasherMover().setRepulsion(repulsion);
		}
	}
	
	/**
	 * Open the actual needed playfield
	 */
	@Override
	public void mouseClicked(MouseEvent arg0)
	{
//		if(arg0.getSource() instanceof JMenu)
//		{
//			System.out.println(((JMenu)arg0.getSource()).getActionCommand());
//			initNewGame(PlayfieldBuilder.FIELDS[levelCounter]);
//		}
	}

	// Unbenutzte Maus-Eventss
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
