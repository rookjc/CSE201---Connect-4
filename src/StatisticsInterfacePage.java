import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;


public class StatisticsInterfacePage extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ToolBar toolBar;

	private StatisticsTokenList st;

	public StatisticsInterfacePage() {
		setLayout(new BorderLayout());

		toolBar = new ToolBar();
		add(toolBar, BorderLayout.NORTH);

		toolBar.getTitle().setText("    Results");

		toolBar.getRestartButton().setText("Restart");

		toolBar.getQuitButton().setText("Quit");

		st = new StatisticsTokenList(GameState.getWinPercent(), GameState.getDrawPercent(), GameState.getLosePercent(), 
				new Color(249, 255,152), new Color(253,105,105));

		add(st);  

		setBackground(new Color(118, 142, 239));
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public void updateNumbers () {
		remove(st);
		st = new StatisticsTokenList(GameState.getWinPercent(), GameState.getDrawPercent(), GameState.getLosePercent(), 
				GameState.playerIsRed ? Piece.RED : Piece.YELLOW, GameState.playerIsRed ? Piece.YELLOW : Piece.RED);
		add(st);
	}
}