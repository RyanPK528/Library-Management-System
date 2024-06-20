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
 * This class represents the operation of calculating a fine in the library management system.
 */
public class CalculateFine implements IOOperation {

	/**
	 * Method to perform the calculate fine operation.
	 */
	@Override
	public void oper(Database database, User user) {

		// Create a new frame for the calculate fine operation
		JFrame frame = Main.frame(400, 210);
		frame.setLayout(new BorderLayout());

		// Add a title to the frame
		JLabel title = Main.title("Calculate Fine");
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

		// Add a calculate fine button
		JButton calc = Main.button("Calculate Fine");
		panel.add(calc);

		// Add a cancel button
		JButton cancel = Main.button("Cancel");
		panel.add(cancel);

		// Add an action listener to the calculate fine button
		calc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Check if the book name is empty
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Book name cannot be empty!");
					return;
				}

				boolean g = true;
				for (Borrowing b : database.getBrws()) {
					// Check if the user has borrowed the book
					if (b.getBook().getName().matches(name.getText().toString()) &&
							b.getUser().getName().matches(user.getName())) {
						// Check if the user is late
						if (b.getDaysLeft()>0) {
							JOptionPane.showMessageDialog(new JFrame(),
									"You are late!\n"+"You have to pay "+b.getDaysLeft()*10+"k as fine");
							frame.dispose();
						} else {
							JOptionPane.showMessageDialog(new JFrame(),
									"You don't have to pay fine");
							frame.dispose();
						}
						g = false;
						break;
					}
				}

				// If the user didn't borrow the book, show a message
				if (g) {
					JOptionPane.showMessageDialog(new JFrame(),"You didn't borrow this book!");
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