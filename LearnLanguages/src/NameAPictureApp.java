import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class NameAPictureApp extends JFrame
{
	private JPanel panel = new JPanel();

	public NameAPictureApp()
	{
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(450, 450));
		panel.setBackground(Color.DARK_GRAY);
	}

	public static void main(String[] args)
	{
		Console.start(new NameAPictureApp(), "Name a Picture", 500, 500);
	}

}
