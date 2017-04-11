import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameboardInterface extends JPanel {
	private ToolBar toolBar;

	private JButton tempBtn;
	
	private GameBoard board;

	public GameboardInterface() {
		setLayout(new BorderLayout());

		toolBar = new ToolBar();
		toolBar.getRestartButton().setText("Restart");
		toolBar.getQuitButton().setText("Quit");

		add(toolBar, BorderLayout.NORTH);
		
		board = new GameBoard();	// Create the underlying data structure

//		 Just a temporary button for
//		 testing the statistic page
//		 this button will be replaced
//		 by GameBoard Page Panel
//		tempBtn = new JButton("Show Stats");
//		add(tempBtn);

		Color bg = new Color(118, 142, 239);
		
		setBackground(bg);
		
		JPanel panel = new JPanel();
		panel.setBackground(bg);
		add(panel, BorderLayout.CENTER);
		
		for(int i = 0; i < 7; i++) {
			JButton column = new JButton();
			column.setPreferredSize(new Dimension(105, 670));
			
<<<<<<< HEAD
			column.setBackground(bg);
			
=======
			final int col = i;
>>>>>>> origin/master
			column.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					board.playerClick(col);
				}
			});
			
			panel.add(column);
		}
	}
	
	public JButton getTempBtn() {
		return tempBtn;
	}

	public ToolBar getToolBar() {
		return toolBar;
	}
}
