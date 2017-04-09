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
	
	// Constructor for creating a computer player tied to a particular board
	public ComputerPlayer(GameBoard gb) {
		this.board = gb;
		this.columnPriorities = new int[7];
		Arrays.fill(this.columnPriorities, MovePriority.NORMAL);
	}
	
	// First method called, to run all of computer player's computations
	public void makeMove() {
		// Analyze possible moves and decide on an optimal one
		refreshPriorities();
		int maxPriority = getMaxPriority();
		List<Integer> moveChoices = getMoveChoices(maxPriority);
		int moveColumn = chooseFrom(moveChoices);
		
		// Make the move visible on the board
		board.makeMove(moveColumn, Piece.CYAN);
	}
	
	// Update columnPriorities based on the current state of the board
	private void refreshPriorities() {
		// TODO: Implement this method fully. Probably the bulk of the AI code
		Arrays.fill(this.columnPriorities, MovePriority.NORMAL);
	}
	
	// Gets the highest priority value appearing in any column
	private int getMaxPriority() {
		int max = -1;
		for (int value : columnPriorities) {
			if (value > max)
				value = max;
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
	
	
}
