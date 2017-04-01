import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InterfacePanel extends JPanel {
	private JLabel mainPageTitle;
	private JButton playButton;
	private JButton exitButton;

	public InterfacePanel(int width, int height) {
		super();
		setSize(width, height);
		mainPageTitle = new JLabel("Connect Four");

		// setup Title
		mainPageTitle.setFont(new Font("Jokerman", Font.BOLD, 60));

		playButton = new RoundButton("Play", new Color(253,105,105));
		playButton.setFont(new Font("Comic Sans MS", Font.ROMAN_BASELINE, 30));
		
		exitButton = new RoundButton("Exit", new Color(249, 255,152));
		exitButton.setFont(new Font("Comic Sans MS", Font.ROMAN_BASELINE, 30));
		
		setBackground(new Color(118, 142, 239));
		layoutComponents();
	}

	public void layoutComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		// constraint for titleLabel
		gc.gridy = 0; // Top to Bottom
		gc.gridx = 0; // Left to Right
		// controls how much space one cell takes compare to other cells
		// weighty controls the height proportion for the cell in 1st row
		gc.weightx = 1;
		gc.gridwidth = 2;
		gc.weighty = 0.6;
		gc.anchor = GridBagConstraints.CENTER;
		add(mainPageTitle, gc);

		// constraint for Buttons
		// first Button
		gc.gridy = 1; // Top to Bottom
		gc.gridx = 0; // Left to Right
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		// controls how much space one cell takes compare to other cells
		// weighty controls the height proportion for the cell in 1st row
		gc.weightx = 0.5;
		gc.weighty = 1;
		gc.ipadx = 200;
		gc.ipady = 200;
		gc.anchor = GridBagConstraints.CENTER;
		add(playButton, gc);

		// second Button
		gc.gridy = 1; // Top to Bottom
		gc.gridx = 1; // Left to Right

		gc.anchor = GridBagConstraints.CENTER;
		add(exitButton, gc);
	}
}
