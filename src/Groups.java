import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents 4 Piece objects in a row, where either player could potentially win
 * @author Hayden Fogle, Kai Li, Joel Minton, Jayson Rook
 * @version 1.0
 */
public class Groups {
	// Constants for which of the 4 directions a group can be oriented in
	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;
	public static final int DIAGONAL_DOWN = 2;
	public static final int DIAGONAL_UP = 3;

	public final int orientation;
	private Piece[] pieces;

	/**
	 * Generate a group with the four pieces specified by an origin and an orientation
	 * @param board the GameBoard object the pieces are all from
	 * @param origin the first Piece
	 * @param orientation the Groups.[direction] constant representing the position
	 * 			of subsequent Pieces relative to the first
	 */
	public Groups(GameBoard board, Piece origin, int orientation) {
		this.orientation = orientation;

		/* Figure out dx and dy, the relative positions of consecutive pieces
	 	   in the group, using the specified orientation. */
		int dx, dy;
		switch (orientation) {
		case Groups.HORIZONTAL:
			dx = 1; dy = 0;
			break;
		case Groups.VERTICAL:
			dx = 0; dy = 1;
			break;
		case Groups.DIAGONAL_UP:
			dx = 1; dy = 1;
			break;
		case Groups.DIAGONAL_DOWN:
		default:
			dx = 1; dy = -1;
			break;
		}

		// Extract the four needed pieces from the GameBoard
		this.pieces = collectPieces(board, origin, dx, dy);
	}

	/**
	 * Extract four aligned pieces based on an origin Piece and displacements dx and dy
	 * @param board the GameBoard the Pieces are all from
	 * @param origin the first Piece
	 * @param dx the change in x-coordinate (column #) between adjacent Pieces
	 * @param dy the change in y-coordinate (row #) between adjacent Pieces
	 * @return an array of the 4 Pieces in this newly defined group
	 */
	public static Piece[] collectPieces(GameBoard board, Piece origin, int dx, int dy) {
		// Get coordinates (row & column) of origin
		int originX = origin.col;
		int originY = origin.row;

		// Iterate 4 times to get the four pieces, each differing in position by (dx,dy)
		Piece[] result = new Piece[4];
		for (int i = 0; i < 4; i++)
			result[i] = board.getPiece(originY + i * dy, originX + i * dx);

		return result;
	}

	/**
	 * Return one of the Pieces in this group that is empty, or null if none exists
	 * @return an empty Piece
	 */
	public Piece getEmptyPiece() {
		for (Piece p : pieces) {
			if (p.isEmpty())
				return p;
		}
		return null;
	}

	/**
	 * Gives a List of all empty Pieces in the Group
	 * @return a LinkedList containing all (max 4) empty Piece objects
	 */
	public List<Piece> getEmptyPieces() {
		List<Piece> result = new LinkedList<Piece>();
		for (Piece p : pieces) {
			if (p.isEmpty())
				result.add(p);
		}
		return result;
	}
	
	/**
	 * Get the color of the player that has won in this group (or Piece.EMPTY if neither)
	 * @return Piece.RED, Piece.YELLOW, or Piece.EMPTY depending on who has one this Group
	 */
	public Color getWinningColor() {
		boolean humanWins = pieces[0].isHuman();
		for (Piece p : pieces) {
			// If any piece isn't the winning color, no one wins
			if ((p.isHuman() && !humanWins)
					|| (p.isComputer() && humanWins)
					|| (p.isEmpty()))
				return Piece.EMPTY;
		}
		// One of the players wins
		return (humanWins ^ GameState.playerIsRed ? Piece.YELLOW : Piece.RED);
	}

	/**
	 * Detect whether there are any empty Pieces left in this Group
	 * @return true iff one or more Pieces are empty
	 */
	public boolean isEmpty() {
		for (Piece p : pieces) {
			if (!p.isEmpty())
				return false;
		}
		return true;
	}

	/**
	 * Detect whether all Pieces in the Group are full (the opposite of isEmpty())
	 * @return true iff the Group is full
	 */
	public boolean isFull() {
		for (Piece p : pieces) {
			if (p.isEmpty())
				return false;
		}
		return true;
	}

	/**
	 * Counts how many of the human's Pieces are in this Group
	 * @return the count of human Pieces (0-4)
	 */
	public int getFrequencyOfHuman() {
		int count = 0;
		for (Piece p : pieces) {
			if (p.isHuman())
				count++;
		}
		return count;
	}

	/**
	 * Counts how many of the computer's Pieces are in this Group
	 * @return the count of computer Pieces (0-4)
	 */
	public int getFrequencyOfComputer() {
		int count = 0;
		for (Piece p : pieces) {
			if (p.isComputer())
				count++;
		}
		return count;
	}

	/**
	 * Counts how many empty Pieces are in this Group
	 * @return the count of empty Pieces (0-4)
	 */
	public int getFrequencyOfEmpty() {
		int count = 0;
		for (Piece p : pieces) {
			if (p.isEmpty())
				count++;
		}
		return count;
	}

	/**
	 * Determine whether either player could still win here
	 * @return True iff someone could still win
	 */
	public boolean isWinnable() {
		return getFrequencyOfHuman() == 0 || getFrequencyOfComputer() == 0;
	}

	/**
	 * Determine whether the computer player could still win here
	 * @return True iff the computer could still win
	 */
	public boolean computerCanWin() {
		return getFrequencyOfHuman() == 0;
	}

	/**
	 * Set all pieces in the group to this color
	 * @param c the color value to assign them
	 */
	public void setAllColors(Color c) {
		for (Piece p : pieces) {
			p.setColor(c);
		}
	}	
}