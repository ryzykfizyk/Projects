import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NameAPictureApp extends JFrame
{
	private JPanel panel = new JPanel();
	private JLabel label = new JLabel("Name a picture above:");
	private JTextField field = new JTextField(20);

	public NameAPictureApp() throws IOException
	{
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(450, 450));
		panel.setBackground(Color.white);
		
		BufferedImage picture = ImageIO.read(new File("pic/el jabalí.png"));
		JLabel picLabel = new JLabel(new ImageIcon(picture));
		
		panel.add(picLabel);
		
		panel.add(label);
		panel.add(field);
		
		setContentPane(panel);
		pack();
	}

	public static void main(String[] args) throws IOException
	{
		Console.start(new NameAPictureApp(), "Name a Picture", 500, 500);
	}

}
