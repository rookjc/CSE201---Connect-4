import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;


public class StatisticsInterfacePage extends JPanel{
	
	
	private ToolBar toolBar;
	
	public StatisticsInterfacePage() {
		setLayout(new BorderLayout());
		
		toolBar = new ToolBar(); 
		add(toolBar, BorderLayout.NORTH);
		
		toolBar.getTitle().setText("Result");
		toolBar.getTitle().setFont(new Font("Comic Sans MS", Font.ROMAN_BASELINE, 50));
		 
		toolBar.getRestartButton().setText("Play Again");
		
		toolBar.getQuitButton().setText("Quit");
		
		StatisticsTokenList st = 
				new StatisticsTokenList(GameState.getWinPercent(), GameState.getDrawPercent(), GameState.getLosePercent(), 
						new Color(249, 255,152), new Color(253,105,105));
		
		st.setWinColor(Color.GREEN);
		add(st);  
		
		setBackground(new Color(118, 142, 239));
	}
	
	public ToolBar getToolBar() {
		return toolBar;
	}
}