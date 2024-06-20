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
 * This class implements the IOOperation interface and provides a GUI to delete a book from the database
 */
public class DeleteBook implements IOOperation {

	/**
	 * This method is called when the user wants to delete a book from the database.
	 * It creates a GUI with a text field to enter the book name and two buttons: "Delete Book" and "Cancel".
	 */
	@Override
	public void oper(Database database, User user) {

		// Create a new JFrame with a size of 400x210
		JFrame frame = Main.frame(400, 210);

		// Set the layout of the frame to BorderLayout
		frame.setLayout(new BorderLayout());

		// Create a JLabel with a title message
		JLabel title = Main.title("Delete book");

		// Add the title label to the north of the frame
		frame.getContentPane().add(title, BorderLayout.NORTH);

		// Create a JPanel with a GridLayout
		JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));

		// Set the border and background of the panel
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		panel.setBackground(null);

		// Create a JLabel and a JTextField to enter the book name
		JLabel label = Main.label("Book Name:");
		JTextField name = Main.textfield();

		// Create two buttons: "Delete Book" and "Cancel"
		JButton delete = Main.button("Delete Book");
		JButton cancel = Main.button("Cancel");

		// Add the components to the panel
		panel.add(label);
		panel.add(name);
		panel.add(delete);
		panel.add(cancel);

		// Add an action listener to the "Delete Book" button
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Check if the book name is empty
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Book name cannot be empty!");
					return;
				}

				// Get the index of the book in the database
				int i = database.getBook(name.getText().toString());

				// If the book exists, delete it
				if (i > -1) {
					database.deleteBook(i);
					JOptionPane.showMessageDialog(new JFrame(), "Book deleted successfully!");
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Book doesn't exist!");
				}
			}
		});

		// Add an action listener to the "Cancel" button
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Close the current frame
				frame.dispose();
			}
		});

		// Add the panel to the center of the frame
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		// Make the frame visible
		frame.setVisible(true);

	}

}