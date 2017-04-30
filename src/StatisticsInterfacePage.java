import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

/**
 * StatisticsInterface for displaying Game statistics  
 * @author Kai Li, Jayson Rook, Hayden Fogle, Joel Minton 
 * @version 1.0 
 * */

public class StatisticsInterfacePage extends JPanel{
 
	private static final long serialVersionUID = 1L;

	private ToolBar toolBar;

	private StatisticsTokenList st;

	public StatisticsInterfacePage() {
		setLayout(new BorderLayout());

		toolBar = new ToolBar();
		add(toolBar, BorderLayout.NORTH);

		toolBar.getTitle().setText("    Results");

		toolBar.getRestartButton().setText("Play Again");

		toolBar.getQuitButton().setText("Quit");

		st = new StatisticsTokenList(GameState.getWinPercent(), GameState.getDrawPercent(), GameState.getLosePercent(), 
				new Color(249, 255,152), new Color(253,105,105));

		add(st);  

		setBackground(new Color(118, 142, 239));
	}

	/**
	 * get Tool Bar 
	 * @return toolBar toolBar of Statistics Page 
	 * */
	public ToolBar getToolBar() {
		return toolBar;
	}
	
	/**
	 * update statistics number in the statistics tokens 
	 * */
	public void updateNumbers () {
		remove(st);
		st = new StatisticsTokenList(GameState.getWinPercent(), GameState.getDrawPercent(), GameState.getLosePercent(), 
				GameState.playerIsRed ? Piece.RED : Piece.YELLOW, GameState.playerIsRed ? Piece.YELLOW : Piece.RED);
		add(st);
	}
}