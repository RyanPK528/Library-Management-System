package Library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Abstract class representing a User in the Library Management System.
 */
public abstract class User {

	//User's information
	protected String name;
	protected String email;
	protected String phonenumber;
	//Array of IOOperations available to the user
	protected IOOperation[] operations;

	//Default constructor
	public User() {}

	//Constructor with name parameter
	public User(String name) {
		this.name = name;
	}

	//Constructor with name, email, and phone number parameters
	public User(String name, String email, String phonenumber) {
		this.name = name;
		this.email = email;
		this.phonenumber = phonenumber;
	}

	//Getter for the user's name
	public String getName() {
		return name;
	}

	//Getter for the user's email
	public String getEmail() {
		return email;
	}

	//Getter for the user's phone number
	public String getPhoneNumber() {
		return phonenumber;
	}

	// Abstract method to return a string representation of the user
	abstract public String toString();

	//Abstract method to display the user's menu
	abstract public void menu(Database database, User user);

	/**
	 * Creates a JFrame for the user's menu.
	 */
	public JFrame frame(String[] data, Database database, User user) {
		JFrame frame = new JFrame();
		frame.setSize(400, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Library Management System");
		frame.setLayout(new BorderLayout());
		frame.getContentPane().setBackground(null);
		frame.setBackground(null);

		JLabel label1 = Main.title("Welcome "+ this.name);
		frame.getContentPane().add(label1, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
		panel.setLayout(new GridLayout(7, 1, 15, 15));
		panel.setBackground(null);

		for (int i=0;i<7;i++) {
			JButton button = new JButton(data[i]);
			button.setFont(new Font("Tahoma", Font.BOLD, 17));
			button.setForeground(Color.white);
			button.setHorizontalAlignment(SwingConstants.CENTER);
			button.setBackground(Color.decode("#1da1f2"));
			button.setBorder(null);
			int index = i;
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					operations[index].oper(database, user);
					if (data[index].matches("Exit") || data[index].matches("Delete all data")) {
						frame.dispose();
					}
				}
			});
			panel.add(button);
		}
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		return frame;
	}

}