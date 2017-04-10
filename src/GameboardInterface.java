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
//		tempBtn = new JButton("Show Stats");
//		add(tempBtn);

		Color bg = new Color(118, 142, 239);
		
		setBackground(bg);
		
		JPanel panel = new JPanel();
		panel.setBackground(bg);
		add(panel, BorderLayout.CENTER);
		
		JButton col1 = new JButton();
		col1.setPreferredSize(new Dimension(105, 670));
		panel.add(col1);
		
		JButton col2 = new JButton();
		col2.setPreferredSize(new Dimension(105, 670));
		panel.add(col2);
		
		JButton col3 = new JButton();
		col3.setPreferredSize(new Dimension(105, 670));
		panel.add(col3);
		
		JButton col4 = new JButton();
		col4.setPreferredSize(new Dimension(105, 670));
		panel.add(col4);
		
		JButton col5 = new JButton();
		col5.setPreferredSize(new Dimension(105, 670));
		panel.add(col5);
		
		JButton col6 = new JButton();
		col6.setPreferredSize(new Dimension(105, 670));
		panel.add(col6);
		
		JButton col7 = new JButton();
		col7.setPreferredSize(new Dimension(105, 670));
		panel.add(col7);
	}
	
	public JButton getTempBtn() {
		return tempBtn;
	}

	public ToolBar getToolBar() {
		return toolBar;
	}
}
