import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class BooksCollectionApp extends JFrame
{
	private JPanel panel = new JPanel();
	private JTextField field = new JTextField(48);
	private JButton button = new JButton("Add");

	public BooksCollectionApp()
	{
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(650, 450));
		panel.setBackground(Color.GRAY);

		final DefaultListModel listOfBooks = new DefaultListModel();

		listOfBooks.addElement("\"Unweaving the Rainbow\" | Richard Dawkins | Science, Delusion and the Appetite for Wonder");


		final JList jList = new JList(listOfBooks);
		JScrollPane listOfBooksScrollPane = new JScrollPane(jList);

		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList.setSelectedIndex(0);
		jList.setVisibleRowCount(7);

		field.setText("Add Book in the following format: Title | Author | Description");
		
		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent ee)
			{
				field.setText("");
				field.requestFocus();
			}
		};
		
		MouseAdapter mouse = new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if(field.getText().equals("Add Book in the following format: Title | Author | Description")) {
					field.setText("");
				}
			}
		};
		
		ActionListener listenerButton = new ActionListener()
		{
			public void actionPerformed(ActionEvent ee)
			{
				field.setText("");
				field.requestFocus();
			}
		};

		field.addMouseListener(mouse);
		field.addActionListener(listener);
		button.addActionListener(listenerButton);
		
		panel.add(listOfBooksScrollPane);
		panel.add(field);
		panel.add(button);
		setContentPane(panel);
		pack();
	}

	public static void main(String[] args) throws Exception
	{
		Database.create();
		Console.start(new BooksCollectionApp(), "Books Collection", 700, 500);
		
		//Database.read();
	}

}
