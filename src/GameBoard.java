import java.awt.Color;

// Represents the current state of the game board, with 42 Pieces and 69 4-in-a-row Slots
// TODO: make this inherit from Panel
public class GameBoard {
	private Piece[][] board;
	private Slot[] slots;
	private ComputerPlayer computer;
	
	// Constructor for creating an empty GameBoard, along with all its component Pieces
	public GameBoard () {
		// Create grid of empty Pieces
		this.board = new Piece[6][7];
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				board[row][col] = new Piece(row, col);
			}
		}
		
		this.slots = new Slot[69];
		int slotNum = 0;
		// Generate all possible slots into this.slots array
		for (int row = 0; row < 6; row++) {		// Horizontal
			for (int col = 0; col < 4; col++)
				this.slots[slotNum++] = new Slot(this, this.getPiece(row, col), Slot.HORIZONTAL);
		}
		for (int row = 0; row < 3; row++) {		// Vertical
			for (int col = 0; col < 7; col++)
				this.slots[slotNum++] = new Slot(this, this.getPiece(row, col), Slot.VERTICAL);
		}
		for (int row = 0; row < 3; row++) {		// Diagonal up
			for (int col = 0; col < 4; col++)
				this.slots[slotNum++] = new Slot(this, this.getPiece(row, col), Slot.DIAGONAL_UP);
		}
		for (int row = 3; row < 6; row++) {		// Diagonal down
			for (int col = 0; col < 4; col++)
				this.slots[slotNum++] = new Slot(this, this.getPiece(row, col), Slot.DIAGONAL_DOWN);
		}
		
		// Create computer player
		this.computer = new ComputerPlayer(this);
	}
	
	// Revert this GameBoard back to an empty grid
	public void clearBoard() {
		// Set each of the 42 pieces to empty
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				board[row][col].setColor(Piece.EMPTY);
			}
		}
	}
	
	// Get the Piece located at a particular row and column
	public Piece getPiece(int row, int col) {
		return board[row][col];
	}
	
	// Place a piece of a given pieceColor into the column indexed by col
	public boolean makeMove(int col, Color pieceColor) {
		int row = getColumnHeight(col);
		if (row > 5)
			return false;	// Column is full; invalid move
		
		board[row][col].setColor(pieceColor);
		return true;
	}
	
	// Gives the height of the stack of pieces in column number col
	public int getColumnHeight(int col) {
		for (int row = 0; row < 6; row++) {
			if (board[row][col].isEmpty())
				return row;
		}
		return 6;
	}
	
	// Get the number of pieces that must be placed in a piece's column,
	// before that piece itself can be a valid move
	public int getBuildUp(Piece p) {
		return p.row - getColumnHeight(p.col);
	}
	
	// Check if either player has won, or the board is full
	public boolean checkGameOver() {
		// Check all potential slots for wins
		for (Slot s : slots) {
			Color winner = s.getWinningColor();
			if (winner.equals(Piece.RED)) {
				System.out.println("Red wins");	// TODO: show where the win is first
				GameState.addRedWin();
				return true;
			} else if (winner.equals(Piece.YELLOW)) {
				System.out.println("Yellow wins");	// TODO: show where the win is first
				GameState.addYellowWin();
				return true;
			}
		}
		
		// If no one has won, is the board full?
		for (int col = 0; col < 7; col++) {
			if (getColumnHeight(col) < 6)
				return false;	// Not full, there is still a space left
		}
		// Board full, game is a draw
		System.out.println("Draw, no one wins");	// TODO: say something before going to stats page
		GameState.addYellowWin();
		return true;
	}
	
	// Process an attempted move from the player. Return true iff the game ends after this click.
	public boolean playerClick(int col) {
		System.out.println("player moved in col: " + col);	// temporary
		if (GameState.playerTurn) {
			GameState.playerTurn = false;
			if (makeMove(col, GameState.playerIsRed ? Piece.RED : Piece.YELLOW)) {
				if (checkGameOver())
					return true;
				computer.makeMove();
				if (checkGameOver())
					return true;
			} else {
				System.out.println("Oops! That isn't a valid move!");	// TODO: have this actually do something
			}
		}
		GameState.playerTurn = true;
		return false;
	}
	
}
