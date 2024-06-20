package Library;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class is responsible for adding a new book to the library database
 */
public class AddBook implements IOOperation {

	/**
	 * This method is part of the IOOperation interface and is used to perform the add book operation
	 */
	@Override
	public void oper(Database database, User user) {

		// Create a new JFrame for the add book window
		JFrame frame = Main.frame(500, 600);

		// Create a title label for the window
		JLabel title = Main.title("Add new book");
		frame.getContentPane().add(title, BorderLayout.NORTH);

		// Create a panel to hold the input fields and buttons
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(8, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
		panel.setBackground(null);

		// Create labels and text fields for book information
		JLabel label1 = Main.label("Book Name:");
		JLabel label2 = Main.label("Book Author:");
		JLabel label3 = Main.label("Book Publisher:");
		JLabel label4 = Main.label("Quantity:");
		JLabel label5 = Main.label("Price:");
		JLabel label6 = Main.label("Borrowing Copies:");

		JTextField name = Main.textfield();
		JTextField author = Main.textfield();
		JTextField pub = Main.textfield();
		JTextField qty = Main.textfield();
		JTextField price = Main.textfield();
		JTextField brwcpis = Main.textfield();

		// Create buttons for adding the book and canceling the operation
		JButton addbook = Main.button("Add Book");
		JButton cancel = Main.button("Cancel");

		// Add the labels, text fields, and buttons to the panel
		panel.add(label1);
		panel.add(name);
		panel.add(label2);
		panel.add(author);
		panel.add(label3);
		panel.add(pub);
		panel.add(label4);
		panel.add(qty);
		panel.add(label5);
		panel.add(price);
		panel.add(label6);
		panel.add(brwcpis);
		panel.add(addbook);
		panel.add(cancel);

		// Add an action listener to the add book button
		addbook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Check if any of the input fields are empty
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Book name cannot be empty!");
					return;
				}
				if (author.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Book author cannot be empty!");
					return;
				}
				if (pub.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Book publisher cannot be empty!");
					return;
				}
				if (qty.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Quantity cannot be empty!");
					return;
				}
				if (price.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Price cannot be empty!");
					return;
				}
				if (brwcpis.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Borrowing copies cannot be empty!");
					return;
				}

				// Check if the quantity, price, and borrowing copies are valid numbers
				try {
					Integer.parseInt(qty.getText().toString());
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Quantity must be an integer!");
					return;
				}
				try {
					Double.parseDouble(price.getText().toString());
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Price must be number!");
					return;
				}
				try {
					Integer.parseInt(brwcpis.getText().toString());
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Borrowing copies must be integer!");
					return;
				}

				// Create a new Book object and set its properties
				Book book = new Book();
				if (database.getBook(name.getText().toString())>-1) {
					JOptionPane.showMessageDialog(new JFrame(), "There is already a book with this name!");
					return;
				} else {
					book.setName(name.getText().toString());
					book.setAuthor(author.getText().toString());
					book.setPublisher(pub.getText().toString());
					book.setQty(Integer.parseInt(qty.getText().toString()));
					book.setPrice(Double.parseDouble(price.getText().toString()));
					book.setBrwcopies(Integer.parseInt(brwcpis.getText().toString()));

					// Add the book to the database
					database.AddBook(book);
					JOptionPane.showMessageDialog(new JFrame(), "Book added successfully");
					frame.dispose();
				}
			}
		});

		// Add an action listener to the cancel button
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		// Add the panel to the frame and make the frame visible
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}
}