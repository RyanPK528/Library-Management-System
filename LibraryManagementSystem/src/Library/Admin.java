package Library;

import javax.swing.JFrame;

/**
 * This class represents an admin user in the library management system
 */
public class Admin extends User {

	/**
	 * Constructor to create an admin user with a name
	 */
	public Admin(String name) {
		super(name);
		// Initialize the operations that the admin user can perform
		this.operations = new IOOperation[] {
				new ViewBooks(),
				new AddBook(),
				new DeleteBook(),
				new Search(),
				new DeleteAllData(),
				new ViewOrders(),
				new Exit()
		};
	}

	/**
	 * Constructor to create an admin user with a name, email, and phone number
	 */
	public Admin(String name, String email, String phonenumber) {
		super(name, email, phonenumber);
		// Initialize the operations that the admin user can perform
		this.operations = new IOOperation[] {
				new ViewBooks(),
				new AddBook(),
				new DeleteBook(),
				new Search(),
				new DeleteAllData(),
				new ViewOrders(),
				new Exit()
		};
	}

	/**
	 * Method to display the menu for the admin user
	 */
	@Override
	public void menu(Database database, User user) {
		// Create an array of strings to hold the menu options
		String[] data = new String[7];
		data[0] = "View Books";
		data[1] = "Add Book";
		data[2] = "Delete Book";
		data[3] = "Search";
		data[4] = "Delete all data";
		data[5] = "View Orders";
		data[6] = "Exit";

		// Create a new frame to display the menu
		JFrame frame = this.frame(data, database, user);
		frame.setVisible(true);
	}

	/**
	 * Method to convert the admin user to a string
	 */
	public String toString() {
		return name+"<N/>"+email+"<N/>"+phonenumber+"<N/>"+"Admin";
	}

}