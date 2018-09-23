import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui_ES
{
	static Random r = new Random();
	static int rand = r.nextInt(TranslateApp_ES.list.size());
	static int rand2;
	static int count = 1;
	boolean check = false;

	public Gui_ES()
	{
		JFrame frame = new JFrame("Translate");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setSize(400, 200);

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(340, 120));
		panel.setBackground(Color.LIGHT_GRAY);

		JLabel label1 = new JLabel("Choose translation type:");
		JLabel label2 = new JLabel("Translate:");
		JLabel label3 = new JLabel("Words to translate: " + TranslateApp_ES.list.size());

		JTextField field1 = new JTextField(TranslateApp_ES.list.get(rand)[0], 20);
		JTextField field2 = new JTextField(20);

		field1.setEditable(false);

		String[] options =
		{ "EN->ES", "ES->EN" };

		JComboBox cb = new JComboBox(options);

		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent ee)
			{
				rand = r.nextInt(TranslateApp_ES.list.size());

				if (cb.getSelectedItem().equals("EN->ES"))
				{
					try
					{
						TranslateApp_ES.readList();

						field1.setText(TranslateApp_ES.list.get(rand)[0]);
						label3.setText("Words to translate: " + TranslateApp_ES.list.size());
						count = 0;
					} catch (FileNotFoundException e)
					{
						e.printStackTrace();
					}
					// for (String[] s : TranslateApp.list)
					// System.out.println(s[0] + ", " + s[1]);

				} else
				{
					try
					{
						TranslateApp_ES.readList();

						for (String[] s : TranslateApp_ES.list)
						{
							String x = s[0];
							s[0] = s[1];
							s[1] = x;
						}
						field1.setText(TranslateApp_ES.list.get(rand)[0]);
						label3.setText("Words to translate: " + TranslateApp_ES.list.size());
						count = 0;
					} catch (FileNotFoundException e)
					{
						e.printStackTrace();
					}

					// for (String[] s : TranslateApp.list)
					// System.out.println(s[0] + ", " + s[1]);
				}
				count++;
			}
		};

		ActionListener listener2 = new ActionListener()
		{
			public void actionPerformed(ActionEvent ee)
			{
				String s = field2.getText();

				if ((count == 1) && (s.equals(TranslateApp_ES.list.get(rand)[1])))
				{
					TranslateApp_ES.list.get(rand)[0] = "*remove*";
					// rand2 = r.nextInt(TranslateApp.list.size());
					// field1.setText(TranslateApp.list.get(rand2)[0]);
					field2.setText("");
					field2.requestFocus();
					label3.setText("Words to translate: " + TranslateApp_ES.list.size());
					count++;
					check = false;
				} else if (s.equals(TranslateApp_ES.list.get(rand2)[1]))
				{
					TranslateApp_ES.list.get(rand2)[0] = "*remove*";
					// rand2 = r.nextInt(TranslateApp.list.size());
					// field1.setText(TranslateApp.list.get(rand2)[0]);
					field2.setText("");
					field2.requestFocus();
					label3.setText("Words to translate: " + TranslateApp_ES.list.size());
					check = false;
				} else
				{
					field2.setText("");
					check = true;
				}

				Iterator itr = TranslateApp_ES.list.iterator();

				while (itr.hasNext())
				{
					String[] element = (String[]) itr.next();
					if (element[0].equals("*remove*"))
					{
						itr.remove();
					}
				}

				if (TranslateApp_ES.list.size() > 0)
				{
					if ((check == false))
					{
						rand2 = r.nextInt(TranslateApp_ES.list.size());
						field1.setText(TranslateApp_ES.list.get(rand2)[0]);
					}
				} else
				{
					try
					{
						TranslateApp_ES.readList();
						field1.setText(TranslateApp_ES.list.get(rand)[0]);
						label3.setText("Words to translate: " + TranslateApp_ES.list.size());
						count = 1;
					} catch (FileNotFoundException e)
					{
						e.printStackTrace();
					}
				}
				label3.setText("Words to translate: " + TranslateApp_ES.list.size());

			}
		};

		cb.addActionListener(listener);
		field2.addActionListener(listener2);

		panel.add(label1);
		panel.add(cb);
		panel.add(label2);
		panel.add(field1);
		panel.add(field2);
		panel.add(label3);

		frame.setContentPane(panel);
		frame.pack();

	}
}
