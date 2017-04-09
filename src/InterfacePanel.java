import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		mainPageTitle.setFont(new Font("Verdana", Font.BOLD, 60));

		playButton = new RoundButton("Play", new Color(253,105,105));
		playButton.setFont(new Font("Ubuntu", Font.ROMAN_BASELINE, 30));
		
		
		exitButton = new RoundButton("Exit", new Color(249, 255,152));
		exitButton.setFont(new Font("Ubuntu", Font.ROMAN_BASELINE, 30));
		
		setBackground(new Color(118, 142, 239));
		LayoutSetup layOut = new LayoutSetup(mainPageTitle, playButton, exitButton, this); 
		layOut.layoutComponents();
	}

	public JButton getPlayButton() {
		return playButton;
	}

	public void setPlayButton(JButton playButton) {
		this.playButton = playButton;
	}

	public JButton getExitButton() {
		return exitButton;
	}

	public void setExitButton(JButton exitButton) {
		this.exitButton = exitButton;
	}

	
}
