import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JFrame;

public class RoundButton extends JButton {
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
		Dimension size = getPreferredSize();
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

		// ANTIALIASING
		// Graphics2D g2d = (Graphics2D)g.create();
		// RenderingHints hints = new RenderingHints(
		// RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
		// );
		// g2d.setRenderingHints(hints);
		super.paintComponent(g);
	}

	protected void paintBorder(Graphics g) {
		g.setColor(Color.darkGray);
		g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
		
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color; 
	}

	public void setPressedBackgroundColor(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
        repaint(); 
    }
	
	/*
	 * public static void main(String[] args) {
	 * 
	 * JFrame.setDefaultLookAndFeelDecorated(true); JFrame frame = new
	 * JFrame("Rounded Button Example"); frame.setLayout(new FlowLayout());
	 * 
	 * JButton b1 = new RoundButton("B1"); JButton b2 = new RoundButton("B2");
	 * 
	 * frame.add(b1); frame.add(b2);
	 * 
	 * frame.setSize(300, 150); frame.setVisible(true); }
	 */
}
