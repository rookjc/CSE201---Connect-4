import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 * RoundButton, the class that customized the making of round button
 * transitioning of the game pages 
 * @author Kai Li, Jayson Rook, Hayden Fogle, Joel Minton 
 * @version 1.0 
 * */
public class RoundButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	
	private Color color;
	private Color pressedBackgroundColor; 
	public RoundButton(String label, Color color) {
		super(label);
		this.color = color; 
		setBackground(color);
		setFocusable(false);

		/*
		 * These statements enlarge the button so that it becomes a circle
		 * rather than an oval.
		 */
		Dimension size = new Dimension(100, 100);
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);

		/*
		 * This call causes the JButton not to paint the background. This allows
		 * us to paint a round background.
		 */
		setContentAreaFilled(false);
	}

	protected void paintComponent(Graphics g) {
		
		if(getModel().isPressed()) {
			 g.setColor(pressedBackgroundColor);
		}
		
		if (getModel().isArmed()) {
			g.setColor(Color.white);
		} 
		else {
			g.setColor(getBackground());
		}
		g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

		super.paintComponent(g);
	}

	protected void paintBorder(Graphics g) {
		g.setColor(Color.darkGray);
		g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
		
	}

	/**
	 * @return color color of the button 
	 * */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color color the the button 
	 * */
	public void setColor(Color color) {
		this.color = color; 
	}
	
	
	/**
	 * set press color 
	 * @param pressedBackgroundColor press color to be changed 
	 * */
	public void setPressedBackgroundColor(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
        repaint(); 
    } 
}
