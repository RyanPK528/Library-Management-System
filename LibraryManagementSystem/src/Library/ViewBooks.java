package Library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class is responsible for viewing all books in the library database
 */
public class ViewBooks implements IOOperation {

	/**
	 * This method is part of the IOOperation interface and is used to perform the view books operation.
	 */
	@Override
	public void oper(Database database, User user) {

		// Get the number of rows needed for the panel
		int rows = database.getAllBooks().size() + 1;

		// Calculate the height of the frame based on the number of rows
		int height = rows * 60 + 100;

		// Create a new frame
		JFrame frame = Main.frame(1000, height);

		// Create a title label
		JLabel title = Main.title("View Books");
		frame.getContentPane().add(title, BorderLayout.NORTH);

		// Create a panel to hold the book information
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(rows, 7, 0, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
		panel.setBackground(null);

		// Create a header row with column names
		String[] row1 = new String[] {"Name", "Author", "Publisher", "Quantity", "Price", "Copies Borrowed"};
		for (String s : row1) {
			JLabel label = label(s);
			panel.add(label);
		}

		// Add each book to the panel
		for (Book b : database.getAllBooks()) {
			JLabel label1 = label(b.getName());
			JLabel label2 = label(b.getAuthor());
			JLabel label3 = label(b.getPublisher());
			JLabel label4 = label(String.valueOf(b.getQty()));
			JLabel label5 = label(String.valueOf(b.getPrice()));
			JLabel label6 = label(String.valueOf(b.getBrwcopies()));
			panel.add(label1);
			panel.add(label2);
			panel.add(label3);
			panel.add(label4);
			panel.add(label5);
			panel.add(label6);
		}

		// Add the panel to the frame and make the frame visible
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	/**
	 * This method is used to create a new label with a border.
	 */
	private JLabel label(String text) {
		JLabel label = Main.label(text);
		label.setBackground(Color.white);
		label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		label.setOpaque(true);
		return label;
	}

}