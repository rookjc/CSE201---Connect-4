import java.awt.Color;

// Represents one piece in the 6 by 7 GameBoard.board
public class Piece {
	// TODO: Actual colors can be changed to make it prettier
	public static final Color EMPTY = Color.WHITE;
	public static final Color RED = Color.RED;
	public static final Color YELLOW = Color.YELLOW;
	
	// Where this piece is located in the board
	public final int row, col;
	
	// Whether this is a red piece, yellow piece, or no piece (Piece.EMPTY)
	private Color color;
	
	// Initialize an empty piece
	public Piece(int row, int col) {
		this.row = row;
		this.col = col;
		this.color = Piece.EMPTY;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	// True iff there is no piece here
	public boolean isEmpty() {
		return this.color.equals(Piece.EMPTY);
	}
	
	public boolean isHuman() {
		return this.color.equals(GameState.playerIsRed ? Piece.RED : Piece.YELLOW);
	}
	
	public boolean isComputer() {
		return this.color.equals(GameState.playerIsRed ? Piece.YELLOW : Piece.RED);
	}
}
