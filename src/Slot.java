import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

// Stub class to be implemented
public class Slot {
	// Constants for which of the 4 directions a slot can be oriented in
	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;
	public static final int DIAGONAL_DOWN = 2;
	public static final int DIAGONAL_UP = 3;

	public final int orientation;
	private Piece[] pieces;

	// Generate a slot with the four pieces specified by an origin and an orientation
	public Slot(GameBoard board, Piece origin, int orientation) {
		this.orientation = orientation;

		/* Figure out dx and dy, the relative positions of consecutive pieces
	 	   in the slot, using the specified orientation. */
		int dx, dy;
		switch (orientation) {
		case Slot.HORIZONTAL:
			dx = 1; dy = 0;
			break;
		case Slot.VERTICAL:
			dx = 0; dy = 1;
			break;
		case Slot.DIAGONAL_UP:
			dx = 1; dy = 1;
			break;
		case Slot.DIAGONAL_DOWN:
		default:
			dx = 1; dy = -1;
			break;
		}

		// Extract the four needed pieces from the GameBoard
		this.pieces = collectPieces(board, origin, dx, dy);
	}

	// Extract four aligned pieces based on origin piece and separations dx and dy
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

	// Return one of the pieces in this slot that is empty, or null if none exists
	public Piece getEmptyPiece() {
		for (Piece p : pieces) {
			if (p.isEmpty())
				return p;
		}
		return null;
	}

	// Gives a list of all empty pieces in the slot
	public List<Piece> getEmptyPieces() {
		List<Piece> result = new LinkedList<Piece>();
		for (Piece p : pieces) {
			if (p.isEmpty())
				result.add(p);
		}
		return result;
	}

	// Get the color of the player that has won in this slot (or Piece.EMPTY if neither)
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

	// Returns true if there are no pieces in the slot
	public boolean isEmpty() {
		for (Piece p : pieces) {
			if (!p.isEmpty())
				return false;
		}
		return true;
	}

	// Returns true if there are 4 pieces in the slot
	public boolean isFull() {
		for (Piece p : pieces) {
			if (p.isEmpty())
				return false;
		}
		return true;
	}

	// Counts how many of the human's pieces are in this slot
	public int getFrequencyOfHuman() {
		int count = 0;
		for (Piece p : pieces) {
			if (p.isHuman())
				count++;
		}
		return count;
	}

	// Counts how many of the computer's pieces are in this slot
	public int getFrequencyOfComputer() {
		int count = 0;
		for (Piece p : pieces) {
			if (p.isComputer())
				count++;
		}
		return count;
	}

	// Counts how many empty pieces are in this slot
	public int getFrequencyOfEmpty() {
		int count = 0;
		for (Piece p : pieces) {
			if (p.isEmpty())
				count++;
		}
		return count;
	}

	// True iff someone could still win in this slot
	public boolean isWinnable() {
		return getFrequencyOfHuman() == 0 || getFrequencyOfComputer() == 0;
	}

	// True iff the computer can still win this this slot
	public boolean computerCanWin() {
		return getFrequencyOfHuman() == 0;
	}

	// Set all pieces in the slot to this color
	public void setAllColors(Color c) {
		for (Piece p : pieces) {
			p.setColor(c);
		}
	}	
}