import java.awt.Color;

/**
 * Represents the current state of the game board, with 42 Pieces and 69 4-in-a-row Groups
 * @author Hayden Fogle, Kai Li, Joel Minton, Jayson Rook
 * @version 1.0
 */
public class GameBoard {
	private Piece[][] board;
	private Groups[] groups;
	private ComputerPlayer computer;
	
	/**
	 * Constructor for creating an empty GameBoard, along with all its component Pieces
	 */
	public GameBoard () {
		// Create grid of empty Pieces
		this.board = new Piece[6][7];
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				board[row][col] = new Piece(row, col);
			}
		}
		
		// Generate all possible groups into this.groups array
		this.groups = new Groups[69];
		int groupNum = 0;
		for (int row = 0; row < 6; row++) {		// Horizontal
			for (int col = 0; col < 4; col++)
				this.groups[groupNum++] = new Groups(this, this.getPiece(row, col), Groups.HORIZONTAL);
		}
		for (int row = 0; row < 3; row++) {		// Vertical
			for (int col = 0; col < 7; col++)
				this.groups[groupNum++] = new Groups(this, this.getPiece(row, col), Groups.VERTICAL);
		}
		for (int row = 0; row < 3; row++) {		// Diagonal up
			for (int col = 0; col < 4; col++)
				this.groups[groupNum++] = new Groups(this, this.getPiece(row, col), Groups.DIAGONAL_UP);
		}
		for (int row = 3; row < 6; row++) {		// Diagonal down
			for (int col = 0; col < 4; col++)
				this.groups[groupNum++] = new Groups(this, this.getPiece(row, col), Groups.DIAGONAL_DOWN);
		}
		
		// Create computer player
		this.computer = new ComputerPlayer(this);
	}
	
	/**
	 * Revert this GameBoard back to an empty grid
	 */
	public void clearBoard() {
		// Set each of the 42 pieces to empty
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				board[row][col].setColor(Piece.EMPTY);
			}
		}
		GameState.playerTurn = true;
	}
	
	/**
	 * Get the Piece object at a particular position
	 * @param row the row index (0-5)
	 * @param col the column index (0-6)
	 * @return the Piece object representing this position on the board
	 */
	public Piece getPiece(int row, int col) {
		return board[row][col];
	}
	
	/**
	 * Place a piece of a specified color into a specified column
	 * @param col the column index (0-6)
	 * @param pieceColor the color of the piece to be placed
	 * @return true iff the move was valid
	 */
	public boolean makeMove(int col, Color pieceColor) {
		int row = getColumnHeight(col);
		if (row > 5)
			return false;	// Column is full; invalid move
		
		board[row][col].setColor(pieceColor);
		return true;
	}
	
	/**
	 * Gives the height of the stack of pieces in the specified column
	 * @param col the column index (0-6)
	 * @return the height of the column (0 begin empty, 6 being full)
	 */
	public int getColumnHeight(int col) {
		for (int row = 0; row < 6; row++) {
			if (board[row][col].isEmpty())
				return row;
		}
		return 6;
	}
	
	// Get the number of pieces that must be placed in a piece's column,
	// before that piece itself can be a valid move
	/**
	 * Get the number of pieces the must be placed in a column,
	 * until the specified piece's location can be a valid move
	 * @param p the Piece in question
	 * @return this 'buildup' value (0-5) for the Piece p
	 */
	public int getBuildUp(Piece p) {
		return p.row - getColumnHeight(p.col);
	}
	
	/**
	 * Check if either player has won, or if the board is full
	 * @return a GameState constant representing a win, loss, draw, or none of these
	 */
	public int checkGameOver() {
		// Check all potential groups for wins
		for (Groups s : groups) {
			Color winner = s.getWinningColor();
			if (winner.equals(Piece.RED)) {
				// Show where the win is
				s.setAllColors(Piece.RED_WIN);
				// Update the win/loss count and return the state
				GameState.addRedWin();
				return GameState.playerIsRed ? GameState.PLAYERWON : GameState.PLAYERLOST;
			} else if (winner.equals(Piece.YELLOW)) {
				// Show where the win is
				s.setAllColors(Piece.YELLOW_WIN);
				// Update the win/loss count and return the state
				GameState.addYellowWin();
				return GameState.playerIsRed ? GameState.PLAYERLOST : GameState.PLAYERWON;
			}
		}
		
		// If no one has won, is the board full?
		for (int col = 0; col < 7; col++) {
			if (getColumnHeight(col) < 6)
				return GameState.NORMAL;	// Not full, there is still a space left
		}
		// Board full, game is a draw
		GameState.addDraw();
		return GameState.DRAW;
	}

	/**
	 * Process an attempted move from the player
	 * @param col the column the player attempts to move in
	 * @return a GameState constant indicating a win, loss, draw, invalid move, or none of these
	 */
	public int playerClick(int col) {
		if (GameState.playerTurn) {
			GameState.playerTurn = false;
			if (makeMove(col, GameState.playerIsRed ? Piece.RED : Piece.YELLOW)) {
				// Check if player wins
				int state = checkGameOver();
				if (state != GameState.NORMAL)
					return state;
								
				// Make computer move, and check if it wins
				computer.makeMove();
				state = checkGameOver();
				if (state != GameState.NORMAL)
					return state;
				
			} else {
				return GameState.INVALIDMOVE;
			}
		}
		
		// Nothing special happened, so go back to the player's turn
		GameState.playerTurn = true;
		return GameState.NORMAL;
	}
	
	/**
	 * Getter for the array of groups
	 * @return the array of all 69 groups on the board
	 */
	public Groups[] getGroups() {
		return this.groups;
	}
	
}