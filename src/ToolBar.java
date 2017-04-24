import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ToolBar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel title; 
	private JButton restartButton;
	private JButton quitButton;

	public ToolBar() {
		setBorder(BorderFactory.createEtchedBorder());

		// Restart button 
		restartButton = new JButton("Restart");
		restartButton.setSize(200, 50);
		restartButton.setBackground(new Color(253,105,105));
		restartButton.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 30));

		// Label text indicating that player can move.
		title = new JLabel("    Waiting for player...");

		// Quit button
		quitButton = new JButton("Quit"); 
		quitButton.setSize(200, 50);
		quitButton.setBackground(new Color(249, 255,152));
		quitButton.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 30));

		layoutComponent();
		title.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 30));
	}

	private void layoutComponent() {
		setBackground(new Color(118, 142, 239));
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		// Restart button 
		gc.gridx = 0; 
		gc.gridy = 0; 

		gc.weightx = 0.3; 
		gc.ipadx = 60;
		gc.ipady = 30; 
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;

		add(restartButton, gc);

		// Title
		gc.gridx = 1; 
		gc.gridy = 0; 

		gc.weightx = 0.8; 
		gc.ipadx = 50; 
		gc.anchor = GridBagConstraints.CENTER;
		add(title, gc);


		// Second button 
		gc.gridx = 3; 
		gc.gridy = 0; 

		gc.weightx = 0.3; 
		gc.ipadx = 95;
		gc.anchor = GridBagConstraints.LINE_END; 
		add(quitButton, gc);
	}

	public JButton getRestartButton() {
		return restartButton;
	}

	public JLabel getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title.setText(title);
	}

	public JButton getQuitButton() {
		return quitButton;
	}
}
