package Library;

import javax.swing.JFrame;

/**
 * Represents a normal user of the library system
 */
public class NormalUser extends User {

	//Constructs a new NormalUser with the given name
	public NormalUser(String name) {
		super(name);
		this.operations = new IOOperation[] {
				new ViewBooks(),
				new Search(),
				new PlaceOrder(),
				new BorrowBook(),
				new CalculateFine(),
				new ReturnBook(),
				new Exit()
		};
	}

	//Constructs a new NormalUser with the given name, email, and phone number
	public NormalUser(String name, String email, String phonenumber) {
		super(name, email, phonenumber);
		this.operations = new IOOperation[] {
				new ViewBooks(),
				new Search(),
				new PlaceOrder(),
				new BorrowBook(),
				new CalculateFine(),
				new ReturnBook(),
				new Exit()
		};
	}

	@Override
	//Displays the menu for the normal user, allowing them to interact with the library system
	public void menu(Database database, User user) {

		String[] data = new String[7];
		data[0] = "View Books";
		data[1] = "Search";
		data[2] = "Place Order";
		data[3] = "Borrow Book";
		data[4] = "Calculate Fine";
		data[5] = "Return Book";
		data[6] = "Exit";

		JFrame frame = this.frame(data, database, user);
		frame.setVisible(true);
	}

	@Override
	//Returns a string representation of the normal user, including their name, email, phone number, and user type
	public String toString() {
		return name+"<N/>"+email+"<N/>"+phonenumber+"<N/>"+"Normal";
	}

}