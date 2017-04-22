import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.xml.internal.messaging.saaj.soap.GifDataContentHandler;

import sun.net.www.content.image.gif;

public class UserInterface extends JFrame {
	static final int WIDTH = 800;
	static final int HEIGHT = 700;

	private InterfacePanel interfacePanel;
	private ColorSelectionPage colorSelection;
	private GameboardInterface gI;

	public UserInterface() {
		super("ConnectFour");
		interfacePanel = new InterfacePanel(WIDTH, HEIGHT);
		colorSelection = new ColorSelectionPage(WIDTH, HEIGHT);
		gI = new GameboardInterface();
		add(interfacePanel);
		// frame set up

		setSize(WIDTH, HEIGHT);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-WIDTH/2, dim.height/2-HEIGHT/2);
		
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// controller
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
					GameState.playerIsRed = true;	// set flag
					// temporary adding gI only , but needs more logical
					// implementation
					add(gI);
				}
			});

			colorSelection.getYellowButton().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					colorSelection.setVisible(false);
					gI.setVisible(true);
					GameState.playerIsRed = false;	// set flag
					// temporary adding gI only , but needs more logical
					// implementation
					add(gI);
				}
			});

			// into gameBoard
			JButton restartBtn = gI.getToolBar().getRestartButton();
			if (restartBtn != null && restartBtn.getText().equals("Restart")) {
				gI.getToolBar().getRestartButton().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						gI.getBoard().clearBoard();
						gI.setVisible(false);
						colorSelection.setVisible(true);
					}
				});
			}

			// quit button for gameBoard
			// tentatively set to quit
			JButton quitBtn = gI.getToolBar().getQuitButton();
			if (quitBtn != null && quitBtn.getText().equals("Quit")) {
				gI.getToolBar().getQuitButton().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
			}

			StatisticsInterfacePage sI = new StatisticsInterfacePage(); 

			//Stat's Interface 
			if(sI != null) {
				JButton replay = sI.getToolBar().getRestartButton(); 
				JButton quit = sI.getToolBar().getQuitButton(); 
				
				replay.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						sI.setVisible(false);
						gI.setVisible(true);
					}
				});
				
				quit.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
			}
			
			
		}

	}

}
