import java.awt.Color;

import javax.swing.JFrame;

public class UserInterface extends JFrame{
	
	public UserInterface() {
		super("ConnectFour");
		setSize(800, 800);
		setVisible(true);
		setResizable(false);
		getContentPane().setBackground(new Color(118, 142,239));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
