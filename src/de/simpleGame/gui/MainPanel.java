package de.simpleGame.gui;

import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import de.simpleGame.gameComponents.GamePanel;

/**
 * Beinhaltet ein Container-Objekt, welches die erzeugten SPielfelder aufnimmt
 * @author l_bettray
 *
 */
public class MainPanel extends JPanel
{
	private JPanel gameContainer = new JPanel(new GridLayout(1, 1));
	
	private GamePanel gamePanel = null;
	
	public MainPanel()
	{
		super(new GridLayout(1, 1));
		
		initPanel();
	}
	
	public MainPanel(LayoutManager arg0)
	{
		super(arg0);

		initPanel();
	}

	private void initPanel()
	{
		this.add(gameContainer);
	}

	public JPanel getGameContainer() {
		return gameContainer;
	}
	
	public void addGamePanel(GamePanel gamePanel)
	{
		this.gamePanel = gamePanel;
		
		if(gamePanel != null)
			gameContainer.add(gamePanel);
	}
	
	public GamePanel getGamePanel()
	{
		return gamePanel;
	}

}
