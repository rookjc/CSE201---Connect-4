import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorSelectionPage extends JPanel{
	private JLabel mainPageTitle;
	private JButton redButton;
	private JButton yellowButton;
	
	final Color RED = new Color(253, 105, 105);
	final Color YELLOW = new Color(249, 255,152);

	public ColorSelectionPage(int width, int height) {
		super();
		setSize(width, height);
		mainPageTitle = new JLabel("Pick Your Color");

		// setup Title
		mainPageTitle.setFont(new Font("Verdana", Font.BOLD, 60));

		redButton = new RoundButton("Red", RED);
		redButton.setFont(new Font("Ubuntu", Font.ROMAN_BASELINE, 30));
		
		yellowButton = new RoundButton("Yellow", YELLOW);
		yellowButton.setFont(new Font("Ubuntu", Font.ROMAN_BASELINE, 30));
		
		setBackground(new Color(118, 142, 239));
		LayoutSetup layOut = new LayoutSetup(mainPageTitle, redButton, yellowButton, this); 
		layOut.layoutComponents();
	}

	public JButton getRedButton() {
		return redButton;
	}

	public void setRedButton(JButton redButton) {
		this.redButton = redButton;
	}

	public JButton getYellowButton() {
		return yellowButton;
	}

	public void setYellowButton(JButton yellowButton) {
		this.yellowButton = yellowButton;
	}
	
}
