import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StatisticsTokenList extends JPanel {

	private Color winColor; 
	private Color lossColor; 
	private RoundButton winStatBtn;
	private RoundButton drawStatBtn;
	private RoundButton loseStatBtn;
	
	public StatisticsTokenList(String winPercent, String drawPercent, String losePercent, 
			Color winColor, Color lossColor) {
		this.winColor = winColor;
		this.lossColor = lossColor;
		winStatBtn = new RoundButton("Wins: " + winPercent + "%", winColor);
		winStatBtn.setFont(new Font("Comic Sans MS", Font.ROMAN_BASELINE, 20));
		winStatBtn.setPressedBackgroundColor(winColor);
		
		drawStatBtn = new RoundButton("Draws: " + drawPercent + "%", new Color(255, 255, 255));
		drawStatBtn.setFont(new Font("Comic Sans MS", Font.ROMAN_BASELINE, 20));
		drawStatBtn.setPressedBackgroundColor(new Color(255, 255, 255));
		
		
		loseStatBtn = new RoundButton("Losses: " + losePercent + "%", lossColor);
		loseStatBtn.setFont(new Font("Comic Sans MS", Font.ROMAN_BASELINE, 20));
		loseStatBtn.setPressedBackgroundColor(lossColor);

		setBackground(new Color(118, 142, 239));
		setUpStatList();
	}

	public void setUpStatList() {
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		// constraint for win button
		// first Button
		gc.gridy = 2; // Top to Bottom
		gc.gridx = 0; // Left to Right
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		// controls how much space one cell takes compare to other cells
		// weighty controls the height proportion for the cell in 1st row
		gc.weightx = 0.3;
		gc.weighty = 1;
		gc.ipadx = 100;
		gc.ipady = 100;
		gc.anchor = GridBagConstraints.CENTER;
		add(winStatBtn, gc);

		// draw Button
		gc.gridy = 2; // Top to Bottom
		gc.gridx = 1; // Left to Right
		gc.ipadx = 100;
		gc.ipady = 100;
		gc.anchor = GridBagConstraints.CENTER;
		add(drawStatBtn, gc);

		// second Button
		gc.gridy = 2; // Top to Bottom
		gc.gridx = 2; // Left to Right
		gc.ipadx = 100;
		gc.ipady = 100;
		gc.anchor = GridBagConstraints.CENTER;
		add(loseStatBtn, gc);
	}

	public Color getWinColor() {
		return winColor;
	}

	public void setWinColor(Color winColor) {
		this.winColor = winColor;
		winStatBtn.setColor(winColor);
	}

	public Color getLossColor() {
		return lossColor;
	}

	public void setLossColor(Color lossColor) {
		this.lossColor = lossColor;
		loseStatBtn.setColor(lossColor);
	}
	
	
}
