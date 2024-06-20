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
 * This class represents the operation of borrowing a book in the library management system.
 */
public class BorrowBook implements IOOperation {

	/**
	 * Method to perform the borrow book operation.
	 */
	@Override
	public void oper(Database database, User user) {

		// Create a new frame for the borrow book operation
		JFrame frame = Main.frame(400, 210);
		frame.setLayout(new BorderLayout());

		// Add a title to the frame
		JLabel title = Main.title("Borrow book");
		frame.getContentPane().add(title, BorderLayout.NORTH);

		// Create a panel to hold the input fields and buttons
		JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		panel.setBackground(null);

		// Add a label and text field for the book name
		JLabel label = Main.label("Book Name:");
		JTextField name = Main.textfield();
		panel.add(label);
		panel.add(name);

		// Add a borrow button
		JButton borrow = Main.button("Borrow Book");
		panel.add(borrow);

		// Add a cancel button
		JButton cancel = Main.button("Cancel");
		panel.add(cancel);

		// Add an action listener to the borrow button
		borrow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Check if the book name is empty
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Book name cannot be empty!");
					return;
				}

				// Check if the book exists in the database
				int i = database.getBook(name.getText().toString());
				if (i>-1) {
					Book book = database.getBook(i);

					// Check if the user has borrowed the book before
					boolean n = true;
					for (Borrowing b : database.getBrws()) {
						if (b.getBook().getName().matches(name.getText().toString()) &&
								b.getUser().getName().matches(user.getName())) {
							n = false;
							JOptionPane.showMessageDialog(new JFrame(),
									"You have borrowed this book before!");
						}
					}

					// If the book is available, create a new borrowing and update the database
					if (n) {
						if (book.getBrwcopies()>1) {
							Borrowing borrowing = new Borrowing(book, user);
							book.setBrwcopies(book.getBrwcopies()-1);
							database.borrowBook(borrowing, book, i);
							JOptionPane.showMessageDialog(new JFrame(),
									"You must return the book before 14 days from now\n"
											+ "Expiry date: "+borrowing.getFinish()+"\nEnjoy!\n");
							frame.dispose();
						} else {
							JOptionPane.showMessageDialog(new JFrame(),
									"This book isn't available for borrowing");
						}
					}

				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Book doesn't exist!");
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

		// Add the panel to the frame
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}

}