import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameboardInterface extends JPanel implements MouseListener {
	private ToolBar toolBar;

	private JButton tempBtn;

	private GameBoard board;

	public GameboardInterface() {
		setLayout(new BorderLayout());

		toolBar = new ToolBar();
		toolBar.getRestartButton().setText("Restart");
		toolBar.getQuitButton().setText("Quit");

		add(toolBar, BorderLayout.NORTH);

		board = new GameBoard();	// Create the underlying data structure
		
		Color bg = new Color(127, 179, 213);

		setBackground(bg);
		
//		for(int i = 0; i < 7; i++) {
//			JButton column = new JButton();
//			column.setPreferredSize(new Dimension(105, 670));
//
//			column.setBackground(Color.PINK);
//
//			final int col = i;
//			column.addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					board.playerClick(col);
//				}
//			});
//
//			this.add(column);
//		}
	}

	@Override
	public void paintComponent(Graphics g)
	{
		int xOffset = 0;
		int yOffset = 0;
				
		super.paintComponent(g);
		
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 6; j++) {
				
				Graphics2D g2 = (Graphics2D)g;
				g2.setColor(Color.WHITE);
				Ellipse2D.Double circle = new Ellipse2D.Double((25 + xOffset), (100 + yOffset), 75, 75);
				g2.fill(circle);
				
				yOffset += 93;
			}
			
			xOffset += 109;
			yOffset = 0;
		}		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int xCoord = e.getX();
		int width = 800;
		
		
	}
	

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(300, 300); // appropriate constants
	}

	public JButton getTempBtn() {
		return tempBtn;
	}

	public ToolBar getToolBar() {
		return toolBar;
	}
	
	public GameBoard getBoard(){
		return board;
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
