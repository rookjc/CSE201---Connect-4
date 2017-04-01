import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UserInterface extends JFrame{
	
	private InterfacePanel interfacePanel; 
	
	public UserInterface() {
		super("ConnectFour");
		interfacePanel = new InterfacePanel(800,800); 
		add(interfacePanel);
		// frame set up 
		
		setSize(800, 800);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
}
