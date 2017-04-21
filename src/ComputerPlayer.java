import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Holds functionality for analyzing and making the computer's moves
public class ComputerPlayer {
	// Constant values for classifying potential moves
	private static class MovePriority {
		public static final int FULL = 0;
		public static final int LOSE = 1;
		public static final int AVOID = 2;
		public static final int NORMAL = 3;
		public static final int SETUP1 = 4;
		public static final int SETUP2 = 5;
		public static final int BLOCK = 6;
		public static final int WIN = 7;
	}
	
	private GameBoard board;
	private int[] columnPriorities;
	private Piece recentlyPlaced;
	
	// Constructor for creating a computer player tied to a particular board
	public ComputerPlayer(GameBoard gb) {
		this.board = gb;
		this.columnPriorities = new int[7];
		Arrays.fill(this.columnPriorities, MovePriority.NORMAL);
		this.recentlyPlaced = null;
	}
	
	// First method called, to run all of computer player's computations
	public void makeMove() {
		// Remove the visual distinction for previous move
		if (recentlyPlaced != null)
			recentlyPlaced.setColor(GameState.playerIsRed ? Piece.YELLOW : Piece.RED);
		
		// Analyze possible moves and decide on an optimal one
		refreshPriorities();
		int maxPriority = getMaxPriority();
		List<Integer> moveChoices = getMoveChoices(maxPriority);
		System.out.println(moveChoices);
		int moveColumn = chooseFrom(moveChoices);
		
		// Make the move visible on the board
		board.makeMove(moveColumn, Piece.CYAN);
		System.out.println("computer moved in col: " + moveColumn);	// temporary
		recentlyPlaced = board.getPiece(board.getColumnHeight(moveColumn) - 1, moveColumn);
	}
	
	// Update columnPriorities based on the current state of the board
	private void refreshPriorities() {
		// Initially mark all columns as 'normal', unless they're full
		for (int col = 0; col < 7; col++) {
			columnPriorities[col] =
					(board.getColumnHeight(col) < 6) ? MovePriority.NORMAL : MovePriority.FULL;
		}
		
		// Factor in information from all slots (updating columnPriorities)
		for (Slot s : board.getSlots()) {
			evaluateSlot(s);
		}
	}
	
	// Gets the highest priority value appearing in any column
	private int getMaxPriority() {
		int max = -1;
		for (int value : columnPriorities) {
			if (value > max)
				max = value;
		}
		return max;
	}
	
	// Gives the indices of columns with given priority
	private List<Integer> getMoveChoices(int priority) {
		List<Integer> result = new ArrayList<>(8);
		for (int col = 0; col < 7; col++) {
			if (columnPriorities[col] == priority)
				result.add(col);
		}
		return result;
	}
	
	// Choose a random element from a list of values
	private int chooseFrom(List<Integer> values) {
		return values.get((int) (Math.random() * values.size()));
	}
	
	// The order that it's most important to mark MovePriority values in (lower index = more important)
	private static final int[] OVERWRITE_PRIORITIES = new int[] {0, 7, 6, 1, 2, 5, 4, 3};
	
	// If a column in columnPriorities has oldPriority, is it more important to mark it with newPriority?
	private boolean shouldOverwrite(int oldPriority, int newPriority) {
		for (int i = 0; i < 8; i++) {
			if (OVERWRITE_PRIORITIES[i] == oldPriority)
				return false;	// Existing priority marker is more important; do not change
			if (OVERWRITE_PRIORITIES[i] == newPriority)
				return true;	// Existing priority marker is more important; do not change
		}
		return false;	// Should never happen; invalid priority values
	}
	
	// Consider assigning a move priority to a particular column
	private void mark(int col, int priority) {
		if (shouldOverwrite(columnPriorities[col], priority)) {
			columnPriorities[col] = priority;
			System.out.println("marking with priority " + priority);
		}
	}
	
	// See if any moves into this slot are worth considering / specifically avoiding
	private void evaluateSlot(Slot s) {
		if (s.isWinnable()) {
			// Get the number of each player's pieces in this slot. One of them will be 0.
			int nHumanPieces = s.getFrequencyOfHuman();
			int nComputerPieces = s.getFrequencyOfComputer();
			// Order of if-statements doesn't really matter here
			if (nHumanPieces == 3) {
				// Find the last piece needed to completely fill this slot
				Piece lastPiece = s.getEmptyPiece();
				int lastPieceBuildUp = board.getBuildUp(lastPiece);
				if(lastPieceBuildUp == 0) {
					// Block the human from finishing this slot and winning
					mark(lastPiece.col, MovePriority.BLOCK);
				} else if (lastPieceBuildUp == 1) {
					// Don't allow the human to finish the slot in the next turn
					mark(lastPiece.col, MovePriority.LOSE);
				}
			} 
			else if (nComputerPieces == 3) {
				// Find the last piece needed to completely fill this slot
				Piece lastPiece = s.getEmptyPiece();
				int lastPieceBuildUp = board.getBuildUp(lastPiece);
				if(lastPieceBuildUp == 0) {
					// There's a winning move, so mark that!
					mark(lastPiece.col, MovePriority.WIN);
				} else if (lastPieceBuildUp == 1) {
					// Try to avoid allowing the player to block this winning move
					mark(lastPiece.col, MovePriority.AVOID);
				}
			}
			else if (nComputerPieces > 0) {
				// If there are 1 or 2 pieces for the computer, consider building on this win opportunity
				List<Piece> emptyPieces = s.getEmptyPieces();
				for (Piece p : emptyPieces) {
					if (board.getBuildUp(p) == 0)
						mark(p.col, nComputerPieces == 2 ? MovePriority.SETUP2 : MovePriority.SETUP1);
				}
			}
		}
		// If the slot cannot be won in, don't change anything as a result of it
	}

	// Remove memory of what the last piece played was (for when restarting the game)
	public void clearRecentPiece() {
		recentlyPlaced = null;
	}
	
}