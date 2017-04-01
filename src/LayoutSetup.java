import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class LayoutSetup {
	private JLabel titleLabel;
	private JButton btn1;
	private JButton btn2;
	private JComponent jc; 

	public LayoutSetup(JLabel lbl, JButton btn1, JButton btn2, JComponent jc) {
		if(lbl!= null && btn1 != null && btn2!= null && jc != null) {
			this.titleLabel = lbl; 
			this.btn1 = btn1; 
			this.btn2 = btn2; 
			this.jc = jc;
		} 
	}
	
	public void layoutComponents() {
		jc.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		// constraint for titleLabel
		gc.gridy = 0; // Top to Bottom
		gc.gridx = 0; // Left to Right
		// controls how much space one cell takes compare to other cells
		// weighty controls the height proportion for the cell in 1st row
		gc.weightx = 1;
		gc.gridwidth = 2;
		gc.weighty = 0.6;
		gc.anchor = GridBagConstraints.CENTER;
		jc.add(titleLabel, gc);

		// constraint for Buttons
		// first Button
		gc.gridy = 1; // Top to Bottom
		gc.gridx = 0; // Left to Right
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		// controls how much space one cell takes compare to other cells
		// weighty controls the height proportion for the cell in 1st row
		gc.weightx = 0.5;
		gc.weighty = 1;
		gc.ipadx = 200;
		gc.ipady = 200;
		gc.anchor = GridBagConstraints.CENTER;
		jc.add(btn1, gc);

		// second Button
		gc.gridy = 1; // Top to Bottom
		gc.gridx = 1; // Left to Right

		gc.anchor = GridBagConstraints.CENTER;
		jc.add(btn2, gc);
	}

	public JLabel getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}

	public JButton getBtn1() {
		return btn1;
	}

	public void setBtn1(JButton btn1) {
		this.btn1 = btn1;
	}

	public JButton getBtn2() {
		return btn2;
	}

	public void setBtn2(JButton btn2) {
		this.btn2 = btn2;
	}

	public JComponent getJc() {
		return jc;
	}

	public void setJc(JComponent jc) {
		this.jc = jc;
	}
	
}
