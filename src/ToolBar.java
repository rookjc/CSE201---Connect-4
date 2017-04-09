import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ToolBar extends JPanel {
	private JButton button1; 
	private JLabel title; 
	private JButton button2; 
	
	public ToolBar() {
		setBorder(BorderFactory.createEtchedBorder());
		
		//restart button 
		button1 = new JButton();
		button1.setBackground(new Color(253,105,105));
		button1.setFont(new Font("Comic Sans MS", Font.ROMAN_BASELINE, 30));
		
		// label text could change as the game goes 
		title = new JLabel("????????");  
		
		
		// quit button 
		button2 = new JButton(); 
		button2.setBackground(new Color(249, 255,152));
		button2.setFont(new Font("Comic Sans MS", Font.ROMAN_BASELINE, 30));
		
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
		
		add(button1, gc);
		
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
		add(button2, gc);
	}

	public JButton getButton1() {
		return button1;
	}

	public JLabel getTitle() {
		return title;
	}

	public JButton getButton2() {
		return button2;
	}
	
}
