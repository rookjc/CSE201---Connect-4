import javax.swing.SwingUtilities;

public class Starter {

	public static void main(String[] args) {
		// running the graphic processing in a separate thread 
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				UserInterface uf = new UserInterface(); 
			}
		});
	}
}
