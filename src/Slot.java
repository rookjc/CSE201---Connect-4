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
		
		// Figure out dx and dy, the relative positions of consecutive pieces
		// in the slot, using the specified orientation
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
		this.pieces = getPieces(board, origin, dx, dy);
	}
	
	// Extract four aligned pieces based on origin piece and separations dx and dy
	public static Piece[] getPieces(GameBoard board, Piece origin, int dx, int dy) {
		// Get coordinates (row & column) of origin
		int originX = origin.col;
		int originY = origin.row;
		
		// Iterate 4 times to get the four pieces, each differing in position by (dx,dy)
		Piece[] result = new Piece[4];
		for (int i = 0; i < 4; i++)
			result[i] = board.getPiece(originY + i * dy, originX + i * dx);
		
		return result;
	}
	
	// Returns true if the computer player has 3 pieces and the remaining piece is empty
	public boolean has3ComputerPieces() {
		int nComputerPieces = 0;
		for (Piece p : pieces) {
			if (p.isComputer())
				nComputerPieces++;
			else if (p.isHuman())
				return false;
		}
		return nComputerPieces == 3;
	}
	
	// Returns true if the human player has 3 pieces and the remaining piece is empty
	public boolean has3HumamPieces() {
		int nHumanPieces = 0;
		for (Piece p : pieces) {
			if (p.isHuman())
				nHumanPieces++;
			else if (p.isComputer())
				return false;
		}
		return nHumanPieces == 3;
	}
	
	// Return one of the pieces in this slot that is empty, or null if none exists
	public Piece getEmptyPiece() {
		for (Piece p : pieces) {
			if (p.isEmpty())
				return p;
		}
		return null;
	}
}
