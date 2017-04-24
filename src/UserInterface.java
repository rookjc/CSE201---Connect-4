import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UserInterface extends JFrame {
	/**
	 * 
	 */
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
	
	public void goToStatPage() {
		gI.setVisible(false);
		remove(sI);
		sI.setVisible(true);
		sI.updateNumbers();
		add(sI);
	}
	
	// Change the message displayed on gI.getToolBar()
	public void setToolbarMessage(String msg) {
		gI.getToolBar().setTitle(msg);
	}

}
