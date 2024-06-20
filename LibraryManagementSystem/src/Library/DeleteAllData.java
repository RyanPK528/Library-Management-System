package Library;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class implements the IOOperation interface and provides a GUI to delete all data from the database
 */
public class DeleteAllData implements IOOperation {

	/**
	 * This method is called when the user wants to delete all data from the database.
	 * It creates a GUI with a confirmation message and two buttons: "Continue" and "Cancel".
	 */
	@Override
	public void oper(Database database, User user) {

		// Create a new JFrame with a size of 600x150
		JFrame frame = Main.frame(600, 150);

		// Set the layout of the frame to BorderLayout
		frame.setLayout(new BorderLayout());

		// Create a JLabel with a title message
		JLabel title = Main.title("Are you sure you want to delete all the data?");

		// Add the title label to the north of the frame
		frame.getContentPane().add(title, BorderLayout.NORTH);

		// Create a JPanel with a GridLayout
		JPanel panel = new JPanel(new GridLayout(1, 2, 15, 15));

		// Set the border and background of the panel
		panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 30));
		panel.setBackground(null);

		// Create two buttons: "Continue" and "Cancel"
		JButton del = Main.button("Continue");
		JButton cancel = Main.button("Cancel");

		// Add the buttons to the panel
		panel.add(del);
		panel.add(cancel);

		// Add an action listener to the "Continue" button
		del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Delete all data from the database
				database.deleteAllData();

				// Close the current frame
				frame.dispose();

				// Open the exit menu
				new Exit().oper(database, user);
			}
		});

		// Add an action listener to the "Cancel" button
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Close the current frame
				frame.dispose();

				// Go back to the user menu
				user.menu(database, user);
			}
		});

		// Add the panel to the center of the frame
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		// Make the frame visible
		frame.setVisible(true);

	}

}