import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameboardInterface extends JPanel {
	private ToolBar toolBar;

	private JButton tempBtn;

	public GameboardInterface() {
		setLayout(new BorderLayout());

		toolBar = new ToolBar();
		toolBar.getRestartButton().setText("Restart");
		toolBar.getQuitButton().setText("Quit");

		add(toolBar, BorderLayout.NORTH);

//		 Just a temporary button for
//		 testing the statistic page
//		 this button will be replaced
//		 by GameBoard Page Panel
		tempBtn = new JButton("Show Stats");
		add(tempBtn);

		setBackground(new Color(118, 142, 239));
	}
	
	public JButton getTempBtn() {
		return tempBtn;
	}



	public ToolBar getToolBar() {
		return toolBar;
	}
}
