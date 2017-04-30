import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ColorSection Page for player to choose token color 
 * @author Kai Li, Jayson Rook, Hayden Fogle, Joel Minton 
 * @version 1.0 
 * */

public class ColorSelectionPage extends JPanel{
	
	private static final long serialVersionUID = 1L;
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

	/**
	 * get Red Button 
	 * @return red Button 
	 * */
	public JButton getRedButton() {
		return redButton;
	}

	/**
	 * set Red Button 
	 * @param redButton red Button to be passed in 
	 * */
	public void setRedButton(JButton redButton) {
		this.redButton = redButton;
	}

	/**
	 * get yellow Button 
	 * @return yellowButton 
	 * */
	public JButton getYellowButton() {
		return yellowButton;
	}

	/**
	 * set Yellow Button 
	 * @param yellowButton button to be passed in 
	 * */
	public void setYellowButton(JButton yellowButton) {
		this.yellowButton = yellowButton;
	}
	
}
