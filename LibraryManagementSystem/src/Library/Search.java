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
 * Implements the "Search" operation, allowing users to search for a book by name
 */
public class Search implements IOOperation {

	@Override
	/**
	 * Performs the "Search" operation, creating a GUI for the user to input book name
	 */
	public void oper(Database database, User user) {

		JFrame frame = Main.frame(400, 210);
		frame.setLayout(new BorderLayout());

		JLabel title = Main.title("Search");
		frame.getContentPane().add(title, BorderLayout.NORTH);

		JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		panel.setBackground(null);
		JLabel label = Main.label("Book Name:");
		JTextField name = Main.textfield();
		JButton search = Main.button("Search");
		JButton cancel = Main.button("Cancel");
		panel.add(label);
		panel.add(name);
		panel.add(search);
		panel.add(cancel);

		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Validate input fields
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Book name cannot be empty!");
					return;
				}
				// Search for the book in the database
				int i = database.getBook(name.getText().toString());
				if (i>-1) {
					// Display book details if found
					JOptionPane.showMessageDialog(new JFrame(),database.getBook(i).toString());
					frame.dispose();
				} else {
					// Display error message if book not found
					JOptionPane.showMessageDialog(new JFrame(), "Book doesn't exist!");
				}
			}
		});

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);

	}

}