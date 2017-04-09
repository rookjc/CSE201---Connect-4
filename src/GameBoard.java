// Represents the current state of the game board, with 42 Pieces and 69 4-in-a-row Slots
// TODO: make this inherit from Panel
public class GameBoard {
	private Piece[][] board;
	private Slot[] slots;
	
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
	
}
