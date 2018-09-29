import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

public class BooksCollectionApp extends JFrame
{
	private JPanel panel = new JPanel();
	private JTextField field = new JTextField(48);
	private JButton button1 = new JButton("Add");
	private JButton button2 = new JButton("Remove");
	public static JList jList = null;
	public static DefaultListModel listOfBooks = new DefaultListModel();
	public static JScrollPane listOfBooksScrollPane;
	public static ArrayList<String> list = new ArrayList<>();

	public BooksCollectionApp()
	{
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(650, 450));
		panel.setBackground(Color.GRAY);

		updateJList();

		field.setText("Add Book in the following format: Title | Author | Description");

		MouseAdapter mouse = new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if (field.getText().equals("Add Book in the following format: Title | Author | Description"))
				{
					field.setText("");
				}
				if (field.getText().equals("Wrong data format!"))
				{
					field.setText("");
				}
			}
		};

		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent ee)
			{
				if (field.getText().equals("Add Book in the following format: Title | Author | Description"))
				{
					field.setText("");
				}
				if (field.getText().equals("Wrong data format!"))
				{
					field.setText("");
				}

				String s = field.getText();
				if (s.split("\\s\\|\\s").length == 3)
				{
					try
					{
						Database.insert(s);
						Database.readList();
						updateJList();

					} catch (Exception e)
					{
						e.printStackTrace();
					}
					field.setText("");
					field.requestFocus();
				} else
				{
					field.setText("Wrong data format!");
					field.requestFocus();
				}

			}
		};

		ActionListener listenerButton1 = new ActionListener()
		{
			public void actionPerformed(ActionEvent ee)
			{
				if (field.getText().equals("Add Book in the following format: Title | Author | Description"))
				{
					field.setText("");
				}
				if (field.getText().equals("Wrong data format!"))
				{
					field.setText("");
				}

				String s = field.getText();
				if (s.split("\\s\\|\\s").length == 3)
				{
					try
					{
						Database.insert(s);
						Database.readList();
						updateJList();

					} catch (Exception e)
					{
						e.printStackTrace();
					}
					field.setText("");
					field.requestFocus();
				} else
				{
					field.setText("Wrong data format!");
					field.requestFocus();
				}
			}
		};

		ActionListener listenerButton2 = new ActionListener()
		{
			public void actionPerformed(ActionEvent ee)
			{
				int selectedIndex = jList.getSelectedIndex() + 1;
				if (selectedIndex > 0)
				{
					try
					{
						Database.remove(selectedIndex);
						Database.readList();
						updateJList();
						
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				button2.requestFocus();
			}
		};

		field.addMouseListener(mouse);
		field.addActionListener(listener);
		button1.addActionListener(listenerButton1);
		button2.addActionListener(listenerButton2);

		panel.add(listOfBooksScrollPane);
		panel.add(field);
		panel.add(button1);
		panel.add(button2);
		setContentPane(panel);
		pack();
	}

	private void updateJList()
	{
		listOfBooks.clear();
		for (String s : list)
		{
			listOfBooks.addElement(s);
		}
		
		//jList = null;
		//if(jList==null)
			jList = new JList(listOfBooks);

		//jList.removeAll();


		//jList.setModel(listOfBooks);

		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//jList.setPreferredSize(new Dimension(590, 100));
		jList.setFixedCellWidth(590);
		//jList.setSelectedIndex(jList.getSelectedIndex());//TRICK!
		//jList.setSelectedIndex(0);
		jList.setVisibleRowCount(10);
		
		
		jList.requestFocus();
		
		listOfBooksScrollPane = new JScrollPane(jList);
		listOfBooksScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		listOfBooksScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		listOfBooksScrollPane.requestFocus();

		getContentPane().add(listOfBooksScrollPane, BorderLayout.CENTER);
		
		//listOfBooksScrollPane.requestFocus();
		//jList.clearSelection();
		//jList.requestFocus();
		
		//System.out.println("jList.getSelectedIndex = " + jList.getSelectedIndex());
	}

	public static void main(String[] args) throws Exception
	{
		Database.create();
		Database.readList();
		Console.start(new BooksCollectionApp(), "Collection of Books", 700, 270);

//		for (String s : list)
//		{
//			System.out.println(s);
//		}
	}

}
