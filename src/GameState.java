// Stub class to be implemented
public class GameState {
	public static final int NORMAL = 0;
	public static final int PLAYERWON = 1;
	public static final int PLAYERLOST = 2;
	public static final int DRAW = 3;
	public static final int INVALIDMOVE = 4;
	
	// Player is red if this is true, or yellow if false
	public static boolean playerIsRed = false;
	// True if game board is accepting player input
	public static boolean playerTurn = true;
	
	private static int winCount = 0;
	private static int loseCount = 0;
	private static int drawCount = 0;
	
	public static String getWinPercent() {
		return String.format("%.1f", winCount * 100.0 / (winCount + loseCount + drawCount));
	}
	
	public static String getLosePercent() {
		return String.format("%.1f", loseCount * 100.0 / (winCount + loseCount + drawCount));
	}
	
	public static String getDrawPercent() {
		return String.format("%.1f", drawCount * 100.0 / (winCount + loseCount + drawCount));
	}
	
	public static void addRedWin() {
		if (playerIsRed)
			winCount++;
		else
			loseCount++;
	}
	
	public static void addYellowWin() {
		if (playerIsRed)
			loseCount++;
		else
			winCount++;
	}
	
	public static void addDraw() {
		drawCount++;
	}
}