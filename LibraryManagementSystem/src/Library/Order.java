package Library;

/**
 * Represents an order for a book from a user
 */
public class Order {
	private Book book;
	private User user;
	private double price;
	private int qty;

	/**
	 * Constructs a new Order with the given book, user, price, and quantity
	 */
	public Order(Book book, User user, double price, int qty) {
		this.book = book;
		this.user = user;
		this.price = price;
		this.qty = qty;
	}

	//Constructs an empty Order
	public Order() {}

	//Returns the book being ordered
	public Book getBook() {
		return book;
	}

	//Sets the book being ordered
	public void setBook(Book book) {
		this.book = book;
	}

	//Returns the user who is placing the order
	public User getUser() {
		return user;
	}

	//Sets the user who is placing the order
	public void setUser(User user) {
		this.user = user;
	}

	//Returns the price of the book per unit=
	public double getPrice() {
		return price;
	}

	//Sets the price of the book per unit
	public void setPrice(double price) {
		this.price = price;
	}

	//Returns the quantity of books being ordered
	public int getQty() {
		return qty;
	}

	//Sets the quantity of books being ordered
	public void setQty(int qty) {
		this.qty = qty;
	}

	//Returns a string representation of the order, including the book name, username, price, and quantity
	public String toString() {
		return "Book name: "+book.getName()+"\n"+
				"Username: "+user.getName()+"\n"+
				"Price: "+String.valueOf(price)+"\n"+
				"Quantity: "+String.valueOf(qty);
	}

	//Returns a string representation of the order
	public String toString2() {
		return book.getName()+"<N/>"+user.getName()+"<N/>"+String.valueOf(price)+"<N/>"+
				String.valueOf(qty);
	}

}