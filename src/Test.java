import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class Test {

	public static void main(String[] args) {
		JFrame window = new JFrame("Message Box");
		window.setSize(500, 500);
		window.setVisible(true);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// Setting up message Box 
		MessageBox msgB = new MessageBox("WIN!", "OK", 100, 100);
		msgB.setBackground(Color.yellow);
		window.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints(); 
		gc.weightx = 1.0; 
		gc.weighty = 1.0;
		gc.ipadx = 100; 
		gc.ipady = 100; 
		gc.anchor = GridBagConstraints.CENTER;
		window.add(msgB, gc);
	}

}
