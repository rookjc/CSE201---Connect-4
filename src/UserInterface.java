import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * UserInterface, the controller class which handles the 
 * transitioning of the game pages 
 * @author Kai Li, Jayson Rook, Hayden Fogle, Joel Minton 
 * @version 1.0 
 * */
public class UserInterface extends JFrame {
	
	private static final long serialVersionUID = 1L;
	static final int WIDTH = 800;
	static final int HEIGHT = 700;

	private InterfacePanel interfacePanel;
	private ColorSelectionPage colorSelection;
	private GameboardInterface gI;
	private StatisticsInterfacePage sI;

	public UserInterface() {
		super("ConnectFour");
		interfacePanel = new InterfacePanel(WIDTH, HEIGHT);
		colorSelection = new ColorSelectionPage(WIDTH, HEIGHT);
		gI = new GameboardInterface(this);
		sI = new StatisticsInterfacePage();
		add(interfacePanel);
		// Frame Set-up

		setSize(WIDTH, HEIGHT);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-WIDTH/2, dim.height/2-HEIGHT/2);

		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Controller
		control();
	}

	/**
	 * Hanle control of the transitioning between interfaces 
	 * */
	private void control() {
		if (interfacePanel != null && colorSelection != null && gI != null) {
			interfacePanel.getPlayButton().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					interfacePanel.setVisible(false);
					add(colorSelection);
				}
			});

			interfacePanel.getExitButton().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});

			colorSelection.getRedButton().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					colorSelection.setVisible(false);
					gI.setVisible(true);
					GameState.playerIsRed = true;	// Set Flag
					add(gI);
				}
			});

			colorSelection.getYellowButton().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					colorSelection.setVisible(false);
					gI.setVisible(true);
					GameState.playerIsRed = false;	// set flag
					add(gI);
				}
			});

			JButton restartBtn = gI.getToolBar().getRestartButton();
			if (restartBtn != null && restartBtn.getText().equals("Restart")) {
				gI.getToolBar().getRestartButton().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// If the player confirms, reset the board to an empty state.
						GameState.playerTurn = false;
						int n = JOptionPane.showConfirmDialog(gI, "Are you sure you want to restart?",
								"Confirm Restart", JOptionPane.OK_CANCEL_OPTION);
						System.out.println(n);
						if (n == 0) {
							gI.reset();
							gI.repaint();
						} else {
							GameState.playerTurn = true;
						}
					}
				});
			}

			// Quit button for gameBoard.
			JButton quitBtn = gI.getToolBar().getQuitButton();
			if (quitBtn != null && quitBtn.getText().equals("Quit")) {
				gI.getToolBar().getQuitButton().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// If the player confirms, exit the software.
						GameState.playerTurn = false;
						int n = JOptionPane.showConfirmDialog(gI, "Are you sure you want to quit?",
								"Confirm Quit", JOptionPane.OK_CANCEL_OPTION);
						System.out.println(n);
						if (n == 0) {
							System.exit(0);
						} else {
							GameState.playerTurn = true;
						}
					}
				});
			}

			// Stats Interface 
			if(sI != null) {
				JButton replay = sI.getToolBar().getRestartButton(); 
				JButton quit = sI.getToolBar().getQuitButton(); 

				replay.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						sI.setVisible(false);
						gI.setVisible(true);
						gI.reset();
					}
				});

				quit.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// If the player confirms, exit the software
						GameState.playerTurn = false;
						int n = JOptionPane.showConfirmDialog(sI, "Are you sure you want to quit?",
								"Confirm Quit", JOptionPane.OK_CANCEL_OPTION);
						System.out.println(n);
						if (n == 0) {
							System.exit(0);
						} else {
							GameState.playerTurn = true;
						}
					}
				});
			}
		}
	}

	/**
	 * Method for nevigating to statistic page 
	 * */
	public void goToStatPage() {
		gI.setVisible(false);
		remove(sI);
		sI.setVisible(true);
		sI.updateNumbers();
		add(sI);
	}

	/**
	 * Method to change the display message on the toolbar as the game progresses 
	 * @return msg 
	 * */
	public void setToolbarMessage(String msg) {
		gI.getToolBar().setTitle(msg);
	}
	
	private class ToolBar extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JLabel title; 
		private JButton restartButton;
		private JButton quitButton;

		public ToolBar() {
			setBorder(BorderFactory.createEtchedBorder());

			// Restart button 
			restartButton = new JButton("Restart");
			restartButton.setSize(200, 50);
			restartButton.setBackground(new Color(253,105,105));
			restartButton.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 30));

			// Label text indicating that player can move.
			title = new JLabel("    Waiting for player...");

			// Quit button
			quitButton = new JButton("Quit"); 
			quitButton.setSize(200, 50);
			quitButton.setBackground(new Color(249, 255,152));
			quitButton.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 30));

			layoutComponent();
			title.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 30));
		}

		private void layoutComponent() {
			setBackground(new Color(118, 142, 239));
			setLayout(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();

			// Restart button 
			gc.gridx = 0; 
			gc.gridy = 0; 

			gc.weightx = 0.3; 
			gc.ipadx = 60;
			gc.ipady = 30; 
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.LINE_START;

			add(restartButton, gc);

			// Title
			gc.gridx = 1; 
			gc.gridy = 0; 

			gc.weightx = 0.8; 
			gc.ipadx = 50; 
			gc.anchor = GridBagConstraints.CENTER;
			add(title, gc);


			// Second button 
			gc.gridx = 3; 
			gc.gridy = 0; 

			gc.weightx = 0.3; 
			gc.ipadx = 95;
			gc.anchor = GridBagConstraints.LINE_END; 
			add(quitButton, gc);
		}

		public JButton getRestartButton() {
			return restartButton;
		}

		public JLabel getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title.setText(title);
		}

		public JButton getQuitButton() {
			return quitButton;
		}
	}

	/**
	 * StatisticTokenList, a layout page for statistics page interface 
	 * @author Kai Li, Jayson Rook, Hayden Fogle, Joel Minton 
	 * @version 1.0 
	 * */

	private class StatisticsTokenList extends JPanel {
		private static final long serialVersionUID = 1L;
		private RoundButton winStatBtn;
		private RoundButton drawStatBtn;
		private RoundButton loseStatBtn;

		public StatisticsTokenList(String winPercent, String drawPercent, String losePercent, 
				Color winColor, Color lossColor) {
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

		/**
		 * set up layout for the statistic layout tokens 
		 * */
		public void setUpStatList() {
			setLayout(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();

			// Constraint for win button.
			// First button
			gc.gridy = 2; // Top to Bottom
			gc.gridx = 0; // Left to Right
			gc.gridwidth = 1;
			gc.fill = GridBagConstraints.NONE;
			// Controls how much space one cell takes compare to other cells
			// weighty controls the height proportion for the cell in 1st row
			gc.weightx = 0.3;
			gc.weighty = 1;
			gc.ipadx = 100;
			gc.ipady = 100;
			gc.anchor = GridBagConstraints.CENTER;
			add(winStatBtn, gc);

			// Draw Button
			gc.gridy = 2; // Top to Bottom
			gc.gridx = 1; // Left to Right
			gc.ipadx = 100;
			gc.ipady = 100;
			gc.anchor = GridBagConstraints.CENTER;
			add(drawStatBtn, gc);

			// Second Button
			gc.gridy = 2; // Top to Bottom
			gc.gridx = 2; // Left to Right
			gc.ipadx = 100;
			gc.ipady = 100;
			gc.anchor = GridBagConstraints.CENTER;
			add(loseStatBtn, gc);
		}


	}
	
	/**
	 * StatisticsInterface for displaying Game statistics  
	 * @author Kai Li, Jayson Rook, Hayden Fogle, Joel Minton 
	 * @version 1.0 
	 * */

	private class StatisticsInterfacePage extends JPanel{
	 
		private static final long serialVersionUID = 1L;

		private ToolBar toolBar;

		private StatisticsTokenList st;

		public StatisticsInterfacePage() {
			setLayout(new BorderLayout());

			toolBar = new ToolBar();
			add(toolBar, BorderLayout.NORTH);

			toolBar.getTitle().setText("    Results");

			toolBar.getRestartButton().setText("Play Again");

			toolBar.getQuitButton().setText("Quit");

			st = new StatisticsTokenList(GameState.getWinPercent(), GameState.getDrawPercent(), GameState.getLosePercent(), 
					new Color(249, 255,152), new Color(253,105,105));

			add(st);  

			setBackground(new Color(118, 142, 239));
		}

		/**
		 * get Tool Bar 
		 * @return toolBar toolBar of Statistics Page 
		 * */
		public ToolBar getToolBar() {
			return toolBar;
		}
		
		/**
		 * update statistics number in the statistics tokens 
		 * */
		public void updateNumbers () {
			remove(st);
			st = new StatisticsTokenList(GameState.getWinPercent(), GameState.getDrawPercent(), GameState.getLosePercent(), 
					GameState.playerIsRed ? Piece.RED : Piece.YELLOW, GameState.playerIsRed ? Piece.YELLOW : Piece.RED);
			add(st);
		}
	}

	/**
	 * RoundButton, the class that customized the making of round button
	 * transitioning of the game pages 
	 * @author Kai Li, Jayson Rook, Hayden Fogle, Joel Minton 
	 * @version 1.0 
	 * */
	private class RoundButton extends JButton {
		
		private static final long serialVersionUID = 1L;
		
		private Color pressedBackgroundColor; 
		public RoundButton(String label, Color color) {
			super(label);
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
		 * set press color 
		 * @param pressedBackgroundColor press color to be changed 
		 * */
		public void setPressedBackgroundColor(Color pressedBackgroundColor) {
	        this.pressedBackgroundColor = pressedBackgroundColor;
	        repaint(); 
	    } 
	}
	
	private class LayoutSetup {
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
		
	}
	
	private class InterfacePanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JLabel mainPageTitle;
		private JButton playButton;
		private JButton exitButton;

		public InterfacePanel(int width, int height) {
			super();
			setSize(width, height);
			mainPageTitle = new JLabel("Connect Four");

			// setup Title
			mainPageTitle.setFont(new Font("Verdana", Font.BOLD, 60));

			playButton = new RoundButton("Play", new Color(253,105,105));
			playButton.setFont(new Font("Ubuntu", Font.ROMAN_BASELINE, 30));
			
			
			exitButton = new RoundButton("Exit", new Color(249, 255,152));
			exitButton.setFont(new Font("Ubuntu", Font.ROMAN_BASELINE, 30));
			
			setBackground(new Color(118, 142, 239));
			LayoutSetup layOut = new LayoutSetup(mainPageTitle, playButton, exitButton, this); 
			layOut.layoutComponents();
		}

		public JButton getPlayButton() {
			return playButton;
		}

		public JButton getExitButton() {
			return exitButton;
		}

		
	}
	
	private class GameboardInterface extends JPanel implements MouseListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private ToolBar toolBar;

		private GameBoard board;
		
		private UserInterface ui;

		public GameboardInterface(UserInterface ui) {
			this.ui = ui;
			
			setLayout(new BorderLayout());

			toolBar = new ToolBar();
			toolBar.getRestartButton().setText("Restart");
			toolBar.getQuitButton().setText("Quit");

			add(toolBar, BorderLayout.NORTH);

			board = new GameBoard();	// Create the underlying data structure
			
			Color bg = new Color(127, 179, 213);

			setBackground(bg);
			
			addMouseListener(this);
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
					g2.setColor(board.getPiece(5 - j, i).getColor());
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
			int yCoord = e.getY();
			int width = 800;
			
			// If it's the player's turn and they click on the board region, attempt a move in that column
			if(GameState.playerTurn && yCoord > 80) {
				int column = (xCoord * 7)/width;
				
				int state = board.playerClick(column);
				if (state != GameState.NORMAL) {
					// Figure out what message to display
					String message = "";
					String title = "Game Over";
					boolean gameEnds = true;
					switch (state) {
					case GameState.PLAYERLOST:
						message = "You lost!";
						ui.setToolbarMessage("Computer Wins!");
						break;
					case GameState.PLAYERWON:
						message = "You won!";
						ui.setToolbarMessage("Human Wins!");
						break;
					case GameState.DRAW:
						message = "The board is full, no one wins!";
						ui.setToolbarMessage("Draw!");
						break;
					case GameState.INVALIDMOVE:
						message = "Oops! That isn't a valid move.";
						title = "Warning";
						gameEnds = false;
						break;
					}
					// Show the message
					repaint();
					JOptionPane.showConfirmDialog(this, message, title, JOptionPane.DEFAULT_OPTION);
					if (gameEnds) {
						ui.goToStatPage();
					} else {
						GameState.playerTurn = true;
					}
				}
				repaint();
			}
		}
		

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(300, 300); // appropriate constants
		}

		public ToolBar getToolBar() {
			return toolBar;
		}

		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		// Reset the board for another game
		public void reset() {
			board.clearBoard();
			toolBar.setTitle("    Waiting for player...");
		}
	}
	
	/**
	 * ColorSection Page for player to choose token color 
	 * @author Kai Li, Jayson Rook, Hayden Fogle, Joel Minton 
	 * @version 1.0 
	 * */

	private class ColorSelectionPage extends JPanel{
		
		private static final long serialVersionUID = 1L;
		private JLabel mainPageTitle;
		private JButton redButton;
		private JButton yellowButton;
		
		final Color RED = new Color(253, 105, 105);
		final Color YELLOW = new Color(249, 255,152);

		public ColorSelectionPage(int width, int height) {
			super();
			setSize(width, height);
			mainPageTitle = new JLabel("Pick Your Color");

			// setup Title
			mainPageTitle.setFont(new Font("Verdana", Font.BOLD, 60));

			redButton = new RoundButton("Red", RED);
			redButton.setFont(new Font("Ubuntu", Font.ROMAN_BASELINE, 30));
			
			yellowButton = new RoundButton("Yellow", YELLOW);
			yellowButton.setFont(new Font("Ubuntu", Font.ROMAN_BASELINE, 30));
			
			setBackground(new Color(118, 142, 239));
			LayoutSetup layOut = new LayoutSetup(mainPageTitle, redButton, yellowButton, this); 
			layOut.layoutComponents();
		}

		/**
		 * get Red Button 
		 * @return red Button 
		 * */
		public JButton getRedButton() {
			return redButton;
		}

		/**
		 * get yellow Button 
		 * @return yellowButton 
		 * */
		public JButton getYellowButton() {
			return yellowButton;
		}
		
	}
	
	public static void main(String[] args) {
		// running the graphic processing in a separate thread 
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				UserInterface uf = new UserInterface(); 
			}
		});
	}



}
