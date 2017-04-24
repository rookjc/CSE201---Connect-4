import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MessageBox extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel message; 
	private JButton confirmBtn; 

	public MessageBox(String msg, String btnStr, int width, int height) {
		message = new JLabel(msg);
		message.setFont(new Font("Verdana", Font.BOLD , 50));
		confirmBtn = new JButton(btnStr);
		confirmBtn.setBackground(new Color(253, 77, 77));
		confirmBtn.setFont(new Font("Ubuntu", Font.ROMAN_BASELINE , 30));
		setUpLayout(width, height); 
	}

	public void setUpLayout(int width, int height) {

		setPreferredSize(new Dimension(width, height));
		System.out.println(width);
		System.out.println(height); 
		setLayout(new GridBagLayout()); 
		setBorder(BorderFactory.createEtchedBorder());
		GridBagConstraints gc = new GridBagConstraints();

		// Constraint for win button
		gc.gridy = 0; // Top to Bottom
		gc.gridx = 0; // Left to Right
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		/* controls how much space one cell takes compare to other cells
		   weighty controls the height proportion for the cell in 1st row */
		gc.weightx = 1;
		gc.weighty = 5;   
		gc.anchor = GridBagConstraints.CENTER;
		add(message, gc);

		// Draw Button
		gc.gridy = 1; // Top to Bottom
		gc.gridx = 0; // Left to Right
		gc.ipadx = width + 50;
		gc.ipady = (height / 3);
		gc.anchor = GridBagConstraints.SOUTH;
		add(confirmBtn, gc); 
	}

	public JLabel getMessage() {
		return message;
	}

	public void setMessage(JLabel message) {
		this.message = message;
	}

	public JButton getConfirmBtn() {
		return confirmBtn;
	}

	public void setConfirmBtn(JButton confirmBtn) {
		this.confirmBtn = confirmBtn;
	} 
}
