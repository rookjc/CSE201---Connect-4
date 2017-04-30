import java.awt.Color;

/**
 * Represents one Piece (with color and coordinate position) in a 6 by 7 GameBoard
 * @author Kai Li, Jayson Rook, Hayden Fogle, Joel Minton 
 * @version 1.0
 */
public class Piece {
	public static final Color EMPTY = Color.WHITE;
	public static final Color RED = new Color(253, 105, 105);
	public static final Color YELLOW = new Color(249, 255,152);
	public static final Color RED_WIN = new Color(125, 50, 50);
	public static final Color YELLOW_WIN = new Color(240, 200, 120);

	// Where this piece is located in the board
	public final int row, col;

	// Whether this is a red piece, yellow piece, or no piece (Piece.EMPTY)
	private Color color;

	/**
	 * Initialize an empty Piece
	 * @param row the row index (0-5)
	 * @param col the column index (0-6)
	 */
	public Piece(int row, int col) {
		this.row = row;
		this.col = col;
		this.color = Piece.EMPTY;
	}
	
	/**
	 * Assign a new color to this Piece
	 * @param color the new color value
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Get this Piece's color
	 * @return the current color value
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * Determine whether or not a player's Piece is here
	 * @return true iff there is no colored Piece here
	 */
	public boolean isEmpty() {
		return this.color.equals(Piece.EMPTY);
	}

	/**
	 * Determine whether or not the human's Piece is here
	 * @return true iff the human's Piece is here
	 */
	public boolean isHuman() {
		return this.color.equals(GameState.playerIsRed ? Piece.RED : Piece.YELLOW);
	}

	/**
	 * Determine whether or not the computer's Piece is here
	 * @return true iff the computer's Piece is here
	 */
	public boolean isComputer() {
		return this.color.equals(GameState.playerIsRed ? Piece.YELLOW : Piece.RED);
	}
}
