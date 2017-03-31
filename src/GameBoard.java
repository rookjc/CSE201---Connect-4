// Represents the current state of the game board, with 42 Pieces and 69 4-in-a-row Slots
public class GameBoard {
	private Piece[][] board;
	
	// Revert this GameBoard back to an empty grid
	public void clearBoard() {
		// Set each of the 42 pieces to empty
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				board[row][col].setColor(Piece.EMPTY);
			}
		}
	}
	
	// Constructor for creating an empty GameBoard, along with all its component Pieces
	public GameBoard () {
		this.board = new Piece[6][7];
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				board[row][col] = new Piece(row, col);
			}
		}
	}
}
