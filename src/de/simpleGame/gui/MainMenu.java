package de.simpleGame.gui;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import de.simpleGame.gameLogic.BasherMover;
import de.simpleGame.gameLogic.PlayfieldBuilder;
import de.simpleGame.listener.MenuListener;
import de.simpleGame.model.MapData;

public class MainMenu extends JMenuBar
{
	private JCheckBoxMenuItem rubberBand = null;
	
	private MapData mapData = null;
	
//	private BasherMover basherMover = null;
	
	public MainMenu(MainPanel mainPanel, MapData mapData) // , BasherMover basherMover)
	{
		super();
		
		this.mapData = mapData;
		
		initMenuBar(mainPanel); //, mapData);	// Referenz auf das Mainpanel an MenuListener durchreichen
	}

	/**
	 * Erzeugen und einhaengen der Menue-Komponenten
	 */
	private void initMenuBar(MainPanel mainPanel) //, MapData mapData) // , BasherMover basherMover)
	{
		String[] bouncerList = new String[] { "BallBouncer", "ExtendedBallBouncer" };
		
		MenuListener mListener = new MenuListener(mainPanel, this, this.mapData);	// , basherMover
		
		JMenu file = new JMenu("file");				// File-Menue
		JMenu view = new JMenu("physics");			// View-Menue
		JMenu bouncer = new JMenu("bouncer");		// View-Menue
		JMenu repulsionCoefficient = new JMenu("repulsion coefficient");	// View-Menue
		JMenu help = new JMenu("help");				// Hilfe-Menue
		JMenu newGame = new JMenu("Select game");	// New game-Menue
		
		JRadioButtonMenuItem bouncers = null;
		ButtonGroup bg = new ButtonGroup();
		for(String s : bouncerList)
		{
			bouncers = new JRadioButtonMenuItem(s);
			
			if(s.equals("BallBouncer"))
				bouncers.setSelected(true);
			
			bouncers.addActionListener(mListener);
			bg.add(bouncers);
			bouncer.add(bouncers);
		}
		
		bg = new ButtonGroup();
		for(int i=1; i<=4; i++)
		{
			bouncers = new JRadioButtonMenuItem("repulsion " + i);
			
			if(i == 4)
				bouncers.setSelected(true);
			
			bouncers.addActionListener(mListener);
			bg.add(bouncers);
			repulsionCoefficient.add(bouncers);
		}
		
		JMenuItem newLevel = new JMenuItem("New level");	// New game-Menue
		newLevel.addActionListener(mListener);	// Listener um Menueauswahl zu erfassen

		JMenuItem loadLevelList = new JMenuItem("Load maplist");	// Load maplist Menue
		loadLevelList.addActionListener(mListener);	// Listener um Menueauswahl zu erfassen
		
		JMenuItem playfield = null;
		
		for(int i=0; i<PlayfieldBuilder.FIELDS.length; i++)
		{
			playfield = new JMenuItem("Playfield " + (i+1) );	// Menuepunkt
			playfield.setName(""+i);
			playfield.addActionListener(mListener);			// Listener um Menueauswahl zu erfassen
			newGame.add(playfield);							// Menupunkt in Menu einhaengen
		}

		JMenuItem ranking = new JMenuItem("Ranking");	// New game-Menue
		ranking.addActionListener(mListener);	// Listener um Menueauswahl zu erfassen
		
		JMenuItem about = new JMenuItem("About");		// Menuepunkt
		about.addActionListener(mListener);				// Listener um Menueauswahl zu erfassen
		
		JMenuItem exit = new JMenuItem("Exit");			// Menuepunkt
		exit.addActionListener(mListener);				// Listener um Menueauswahl zu erfassen
		
		rubberBand = new JCheckBoxMenuItem("Rubber band");			// Menuepunkt
		rubberBand.addActionListener(mListener);		// Listener um Menueauswahl zu erfassen

		file.add(loadLevelList);// Menupunkt in Menu einhaengen
		file.addSeparator();
		file.add(newLevel);		// Menupunkt in Menu einhaengen
		file.add(newGame);		// Menupunkt in Menu einhaengen
		file.add(exit);			// Menupunkt in Menu einhaengen
		
		view.add(rubberBand);	// Menupunkt in Menu einhaengen
		view.add(bouncer);		// Menupunkt in Menu einhaengen
		view.add(repulsionCoefficient);	// Menupunkt in Menu einhaengen

		help.add(ranking);	// Menupunkt in Menu einhaengen
		help.addSeparator();
		help.add(about);	// Menupunkt in Menu einhaengen

		add(file);		// Menu in Menu-Balken einhaengen
		add(view);		// Menu in Menu-Balken einhaengen
		add(help);		// Menu in Menu-Balken einhaengen
	}
	
	public JCheckBoxMenuItem getRubberBand()
	{
		return rubberBand;
	}

}
