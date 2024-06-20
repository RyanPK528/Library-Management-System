package Library;

import java.awt.BorderLayout;
import java.awt.Color;
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
 * This class is responsible for viewing orders in the library database
 */
public class ViewOrders implements IOOperation {

	/**
	 * This method is part of the IOOperation interface and is used to perform the view orders operation
	 */
	@Override
	public void oper(Database database, User user) {

		// Create a new frame
		JFrame frame = Main.frame(400, 210);
		frame.setLayout(new BorderLayout());

		// Create a title label
		JLabel title = Main.title("View Orders");
		frame.getContentPane().add(title, BorderLayout.NORTH);

		// Create a panel to hold the input fields and buttons
		JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		panel.setBackground(null);

		// Create labels, text fields, and buttons
		JLabel label = Main.label("Book Name:");
		JTextField name = Main.textfield();
		JButton view = Main.button("View Orders");
		JButton cancel = Main.button("Cancel");

		// Add the components to the panel
		panel.add(label);
		panel.add(name);
		panel.add(view);
		panel.add(cancel);

		// Add an action listener to the view button
		view.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Check if the book name is empty
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Book name cannot be empty!");
					return;
				}
				// Check if the book exists
				int i = database.getBook(name.getText().toString());
				if (i > -1) {
					// View the orders for the book
					viewOrders(name.getText().toString(), database);
					frame.dispose();
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

		// Add the panel to the frame and make the frame visible
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	/**
	 * This method is used to view the orders for a specific book
	 */
	private void viewOrders(String bookname, Database database) {

		// Calculate the number of rows needed for the panel
		int rows = 1;
		for (Order order : database.getAllOrders()) {
			if (order.getBook().getName().matches(bookname)) {
				rows++;
			}
		}

		// Calculate the height of the frame based on the number of rows
		int height = rows * 55 + 100;

		// Create a new frame
		JFrame frame = Main.frame(500, height);

		// Create a title label
		JLabel title = Main.title("View Orders");
		frame.getContentPane().add(title, BorderLayout.NORTH);

		// Create a panel to hold the order information
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(rows, 4, 0, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
		panel.setBackground(null);

		// Create a header row with column names
		String[] row1 = new String[] {"Book", "User", "Quantity", "Price"};
		for (String s : row1) {
			JLabel label = label(s);
			panel.add(label);
		}

		// Add each order to the panel
		for (Order order : database.getAllOrders()) {
			if (order.getBook().getName().matches(bookname)) {
				JLabel label1 = label(order.getBook().getName());
				JLabel label2 = label(order.getUser().getName());
				JLabel label3 = label(String.valueOf(order.getQty()));
				JLabel label4 = label(String.valueOf(order.getPrice()));
				panel.add(label1);
				panel.add(label2);
				panel.add(label3);
				panel.add(label4);
			}
		}

		// Add the panel to the frame and make the frame visible
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	/**
	 * This method is used to create a new label with a border
	 */
	private JLabel label(String text) {
		JLabel label = Main.label(text);
		label.setBackground(Color.white);
		label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		label.setOpaque(true);
		return label;
	}

}