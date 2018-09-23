import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CompleteTheSentenceApp
{
	static int j = 0;
	static int rand2 = 0;
	static JLabel label2;
	static int count = 0;
	static ArrayList<String> list;
	static ArrayList<String> listOfAnswers;
	static Random r = new Random();
	static int rand;
	static JTextField field1, field2;
	
	public static void main(String[] args) throws FileNotFoundException
	{
		JFrame frame = new JFrame("Complete the Sentence");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setSize(700, 170);

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(600, 120));
		panel.setBackground(Color.ORANGE);

		list = new ArrayList<>();
		readListFromFile();
		listOfAnswers = new ArrayList<>();
		updateLists();
		
		JLabel label1 = new JLabel("Complete the sentence:");
		label2 = new JLabel("Number of sentences: " + list.size());
		
		JButton button = new JButton("Restart");

		

		rand = r.nextInt(list.size());

		field1 = new JTextField(list.get(rand), 50);

		field2 = new JTextField("Enter the proper form of the word in brackets.", 50);

		

		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent ee)
			{
				String word = field2.getText();
				field2.setText("");
				
				String s = "";
				if (j == 0)
					s = listOfAnswers.get(rand);
				else
					s = listOfAnswers.get(rand2);
				if (word.matches(s))
				{
					if (j == 0)
					{
						list.remove(rand);
						listOfAnswers.remove(rand);
						label2.setText("Number of sentences: " + list.size());
						
					} else
					{
						list.remove(rand2);
						listOfAnswers.remove(rand2);
						label2.setText("Number of sentences: " + list.size());
					}
					if (list.size() == 0)
					{
						label2.setVisible(false);
						button.setVisible(true);
						field2.setEnabled(false);
						
					}else {
					rand2 = r.nextInt(list.size());
					field1.setText(list.get(rand2));
					j++;
					}

				}
				field2.requestFocus();
			}
		};
		
		ActionListener listener2 = new ActionListener()
		{
			public void actionPerformed(ActionEvent ee)
			{
				try
				{
					list.clear();
					listOfAnswers.clear();
					j=0;
					readListFromFile();
					updateLists();
					label2.setVisible(true);
					label2.setText("Number of sentences: " + list.size());
					button.setVisible(false);
					field2.setEnabled(true);
					field2.requestFocus();
					count=0;
					rand = r.nextInt(list.size());
					field1.setText(list.get(rand));
					field2.setText("Enter the proper form of the word in brackets.");
				} catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}
			}
		};

		field2.addActionListener(listener);
		field1.setEditable(false);
		field2.setEnabled(true);
		button.setVisible(false);
		button.addActionListener(listener2);
		panel.add(label1);
		panel.add(field1);
		panel.add(field2);
		panel.add(label2);
		panel.add(button);
		
		MouseAdapter mouse = new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if (count == 0 && field2.getText().equals("Enter the proper form of the word in brackets."))
				{
					field2.setText("");
					count++;
				}
			}
		};

		field2.addMouseListener(mouse);

		frame.setContentPane(panel);
		frame.pack();

	}

	private static void updateLists()
	{
		int index1 = 0, index2 = 0;
		String answer = "";
		int i = 0;

		for (String s : list)
		{
			index1 = s.lastIndexOf('[');
			index2 = s.lastIndexOf(']');
			answer = s.substring(index1 + 1, index2);
			listOfAnswers.add(answer);
			list.set(i, s.substring(0, index1 - 1));
			i++;
		}
		
	}

	private static void readListFromFile() throws FileNotFoundException
	{
		String fileName = "sentences_spanish.txt";
		String next = "";
		File textFile = new File(fileName);
		
		Locale loc = new Locale("es", "ES");
		Scanner in = new Scanner(textFile, "UTF-8");
		in.useLocale(loc);

		while (in.hasNextLine())
		{
			next = in.nextLine();
			list.add(next);
		}

		in.close();
	}

}
