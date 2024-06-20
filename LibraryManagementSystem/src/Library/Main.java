package Library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * This is the main class of the Library Management System
 */
public class Main {
	static Scanner s;
	static Database database;

	/**
	 * The main method is the entry point of the program
	 */
	public static void main(String[] args) {
		// Initialize the database
		database = new Database();

		// Create the main frame
		JFrame frame = frame(500, 300);

		// Create a panel to hold the input fields and buttons
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
		panel.setBackground(null);

		// Create a title label
		JLabel title = label("Welcome to Library Management System");
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setForeground(Color.decode("#1da1f2"));
		frame.getContentPane().add(title, BorderLayout.NORTH);

		// Create labels and text fields for phone number and email
		JLabel label1 = label("Phone Number:");
		JLabel label2 = label("Email:");
		JTextField phonenumber = textfield();
		JTextField email = textfield();

		// Create buttons for login and new user
		JButton login = button("Login");
		JButton newUser = button("New User");

		// Add action listeners to the buttons
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Check if phone number and email are empty
				if (phonenumber.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Phone number cannot be empty!");
					return;
				}
				if (email.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Email cannot be empty!");
					return;
				}
				// Login the user
				login(phonenumber.getText().toString(), email.getText().toString(), frame);
			}
		});
		newUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Create a new user
				newuser();
				frame.dispose();
			}
		});

		// Add the labels, text fields, and buttons to the panel
		panel.add(label1);
		panel.add(phonenumber);
		panel.add(label2);
		panel.add(email);
		panel.add(login);
		panel.add(newUser);

		// Add the panel to the frame and make the frame visible
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	/**
	 * This method is used to login a user
	 */
	private static void login(String phonenumber, String email, JFrame frame) {
		int n = database.login(phonenumber, email);
		if (n!= -1) {
			User user = database.getUser(n);
			user.menu(database, user);
			frame.dispose();
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "User doesn't exist");
		}
	}

	/**
	 * This method is used to create a new user
	 */
	private static void newuser() {
		// Create a new frame
		JFrame frame = frame(500, 400);

		// Create a panel to hold the input fields and buttons
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
		panel.setBackground(null);

		// Create a title label
		JLabel title = label("Create new account");
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setForeground(Color.decode("#1da1f2"));
		frame.getContentPane().add(title, BorderLayout.NORTH);

		// Create labels and text fields for name, phone number, and email
		JLabel label0 = label("Name:");
		JLabel label1 = label("Phone Number:");
		JLabel label2 = label("Email:");
		JTextField name = textfield();
		JTextField phonenumber = textfield();
		JTextField email =textfield();

		// Create radio buttons for admin and normal user
		JRadioButton admin = radioButton("Admin");
		JRadioButton normaluser = radioButton("Normal User");

		// Add action listeners to the radio buttons
		admin.addActionListener(e -> {
			if (normaluser.isSelected()) {
				normaluser.setSelected(false);
			}
		});
		normaluser.addActionListener(e -> {
			if (admin.isSelected()) {
				admin.setSelected(false);
			}
		});

		// Create buttons for creating an account and canceling
		JButton createacc = button("Create Account");
		JButton cancel = button("Cancel");

		// Add action listeners to the buttons
		createacc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Check if the username already exists
				if (database.userExists(name.getText().toString())) {
					JOptionPane.showMessageDialog(new JFrame(), "Username exists!\nTry another one");
					return;
				}
				// Check if the input fields are empty
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Name cannot be empty!");
					return;
				}
				if (phonenumber.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Phone number cannot be empty!");
					return;
				}
				if (email.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Email cannot be empty!");
					return;
				}
				// Check if the user has chosen an account type
				if (!admin.isSelected() &&!normaluser.isSelected()) {
					JOptionPane.showMessageDialog(new JFrame(), "You must choose account type!");
					return;
				}
				// Create a new user
				User user;
				if (admin.isSelected()) {
					user = new Admin(name.getText().toString(),
							email.getText().toString(), phonenumber.getText().toString());
				} else {
					user = new NormalUser(name.getText().toString(),
							email.getText().toString(), phonenumber.getText().toString());
				}
				frame.dispose();
				database.AddUser(user);
				user.menu(database, user);
			}
		});
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		// Add the labels, text fields, radio buttons, and buttons to the panel
		panel.add(label0);
		panel.add(name);
		panel.add(label1);
		panel.add(phonenumber);
		panel.add(label2);
		panel.add(email);
		panel.add(admin);
		panel.add(normaluser);
		panel.add(createacc);
		panel.add(cancel);

		// Add the panel to the frame and make the frame visible
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	/**
	 * This method is used to create a new frame
	 */
	public static JFrame frame(int width, int height) {
		JFrame frame = new JFrame();
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Library Management System");
		frame.setLayout(new BorderLayout());
		frame.setBackground(Color.white);
		frame.getContentPane().setBackground(Color.white);
		return frame;
	}

	/**
	 * This method is used to create a new label
	 */
	public static JLabel label(String text) {
		JLabel label1 = new JLabel(text);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(new Font("Tahoma", Font.BOLD, 17));
		label1.setForeground(Color.black);
		return label1;
	}

	/**
	 * This method is used to create a new text field
	 */
	public static JTextField textfield() {
		JTextField textfield1 = new JTextField();
		textfield1.setFont(new Font("Tahoma", Font.BOLD, 17));
		textfield1.setForeground(Color.black);
		textfield1.setHorizontalAlignment(SwingConstants.CENTER);
		return textfield1;
	}

	/**
	 * This method is used to create a new button
	 */
	public static JButton button(String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("Tahoma", Font.BOLD, 17));
		button.setForeground(Color.white);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setBackground(Color.decode("#1da1f2"));
		button.setBorder(null);
		return button;
	}

	/**
	 * This method is used to create a new radio button
	 */
	public static JRadioButton radioButton(String text) {
		JRadioButton btn = new JRadioButton();
		btn.setForeground(Color.black);
		btn.setText(text);
		btn.setHorizontalAlignment(SwingConstants.CENTER);
		btn.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn.setBackground(null);
		return btn;
	}

	/**
	 * This method is used to create a new title label
	 */
	public static JLabel title(String text) {
		JLabel title = Main.label(text);
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setForeground(Color.decode("#1da1f2"));
		return title;
	}
}