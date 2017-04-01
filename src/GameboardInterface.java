import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class GameboardInterface extends JPanel {
	private ToolBar toolBar;
	public GameboardInterface() {
		setLayout(new BorderLayout());
		
		toolBar = new ToolBar(); 
		
		add(toolBar, BorderLayout.NORTH);
		
		setBackground(new Color(118, 142, 239));
	}
	public ToolBar getToolBar() {
		return toolBar;
	}
	
}
