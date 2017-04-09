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
		 
		toolBar.getButton1().setText("Play Again");
		
		toolBar.getButton2().setText("Quit");
		
		StatisticsTokenList st = 
				new StatisticsTokenList("1000", "1000", "1000?", 
						new Color(249, 255,152), new Color(253,105,105));
		
		st.setWinColor(Color.GREEN);
		add(st);  
		
		setBackground(new Color(118, 142, 239));
	}
	
	public ToolBar getToolBar() {
		return toolBar;
	}
}
