import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NameAPictureApp extends JFrame
{
	public static ArrayList<String> list;
	private JPanel panel = new JPanel();
	private JLabel label = new JLabel("Name a picture above:");
	static JLabel label2;
	private JTextField field = new JTextField(20);
	static Random r = new Random();
	static int rand = 0;
	static BufferedImage picture;
	static JLabel picLabel;

	public NameAPictureApp() throws IOException
	{
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(450, 450));
		panel.setBackground(Color.white);

		rand = r.nextInt(list.size());
		picture = ImageIO.read(new File("pic/" + list.get(rand)));
		picLabel = new JLabel(new ImageIcon(picture));

		panel.add(picLabel);

		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent ee)
			{
				String s1 = field.getText();
				String s2 = (list.get(rand).substring(0, list.get(rand).length() - 4));
				int n1 = s2.indexOf("(");
				int n2 = s2.indexOf(")");

				if (n1 > 0 && n2 > 0)
					s2 = s2.substring(0, n1);

				if ((list.size() > 1) && (s1.equals(s2)))
				{
					list.remove(rand);
					rand = r.nextInt(list.size());
					try
					{
						picture = ImageIO.read(new File("pic/" + list.get(rand)));
						picLabel.setIcon(new ImageIcon(picture));
						label2.setText("Number of images: " + list.size());
					} catch (IOException e)
					{
						e.printStackTrace();
					}
					field.setText("");
					field.requestFocus();

				} else if ((list.size() == 1) && (s1.equals(s2)))
				{
					list.clear();
					readFilesNames();
					try
					{
						rand = r.nextInt(list.size());
						picture = ImageIO.read(new File("pic/" + list.get(rand)));
						picLabel.setIcon(new ImageIcon(picture));
					} catch (IOException e)
					{
						e.printStackTrace();
					}
					field.setText("");
					field.requestFocus();
					label2.setText("Number of images: " + list.size());

				} else
				{
					field.setText("");
					field.requestFocus();
				}
			}
		};
		label2 = new JLabel("Number of images: " + list.size());

		field.addActionListener(listener);
		panel.add(label);
		panel.add(field);
		panel.add(label2);

		setContentPane(panel);
		pack();
	}

	public static void main(String[] args) throws IOException
	{
		readFilesNames();
		Console.start(new NameAPictureApp(), "Name a Picture", 500, 500);
	}

	private static void readFilesNames()
	{
		list = new ArrayList<>();
		File[] files = new File("pic").listFiles();

		for (File file : files)
		{
			if (file.isFile())
			{
				list.add(file.getName());
				// System.out.println(file.getName());
			}
		}

	}

}
