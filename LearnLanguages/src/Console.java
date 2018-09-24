import javax.swing.*;

public class Console {
  public static void
  start(final JFrame frame, final String title, final int width, final int height) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setVisible(true);
    	frame.setResizable(false);
		frame.setLocationRelativeTo(null);
      }
    });
  }
}