package de.simpleGame.gui;

import javax.swing.JFrame;

/**
 * Hauptfenster, beinhaltet alle grafischen Komponenten
 * und gezeichneten Objekte
 * @author l_bettray
 *
 */
public class MainFrame extends JFrame
{
	public MainFrame(String arg0) // throws HeadlessException
	{
		super("lektion 11: Sekundenzaehler zufuegen Menue erweitern, Debugging und Refactoring");

		initFrame();
	}

	/**
	 * Verhalten beim schliessen des Programms setzen
	 * und die Fenstergroesse festlegen
	 */
	private void initFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(50, 50);
		setSize(640, 480);
	}

}
