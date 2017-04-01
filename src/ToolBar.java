import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ToolBar extends JPanel {
	private JButton restartButton; 
	private JLabel title; 
	private JButton quitButton; 
	
	public ToolBar() {
		setBorder(BorderFactory.createEtchedBorder());
		
		//restart button 
		restartButton = new JButton("Restart");
		restartButton.setBackground(new Color(253,105,105));
		restartButton.setFont(new Font("Comic Sans MS", Font.ROMAN_BASELINE, 30));
		
		// label text could change as the game goes 
		title = new JLabel("????????");  
		
		
		// quit button 
		quitButton = new JButton("Quit"); 
		quitButton.setBackground(new Color(249, 255,152));
		quitButton.setFont(new Font("Comic Sans MS", Font.ROMAN_BASELINE, 30));
		
		layoutComponent();
	}

	private void layoutComponent() {
		setBackground(new Color(118, 142, 239));
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		//restart button 
		gc.gridx = 0; 
		gc.gridy = 0; 
		
		gc.weightx = 0.3; 
		gc.ipadx = 60;
		gc.ipady = 30; 
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		
		add(restartButton, gc);
		
		// title
		gc.gridx = 1; 
		gc.gridy = 0; 
		
		gc.weightx = 0.8; 
		gc.ipadx = 50; 
		gc.anchor = GridBagConstraints.CENTER;
		add(title, gc);
		
		
		//second button 
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

	public void setRestartButton(JButton restartButton) {
		this.restartButton = restartButton;
	}

	public JLabel getTitle() {
		return title;
	}

	public void setTitle(JLabel title) {
		this.title = title;
	}

	public JButton getQuitButton() {
		return quitButton;
	}

	public void setQuitButton(JButton quitButton) {
		this.quitButton = quitButton;
	}
}