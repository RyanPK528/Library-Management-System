package Library;

/**
 * This class represents a book in the library management system.
 */
public class Book {

	// Private fields to store the book's details
	private String name;		// title
	private String author;		// author
	private String publisher;	// publisher
	private String status;		// Borrowing Status
	private int qty;			// Copies for sale
	private double price;		// Price
	private int brwcopies;		// Copies for borrowing

	// Default constructor
	public Book() {};

	/**
	 * Constructor to create a book with the given details.
	 */
	public Book(String name, String author, String publisher, int qty, double price, int brwcopies) {
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.qty = qty;
		this.price = price;
		this.brwcopies = brwcopies;
	}

	/**
	 * Method to convert the book to a string.
	 */
	public String toString() {
		String text = "Book Name: " + name+"\n"+
				"Book Author: " + author+"\n"+
				"Book Publisher: " + publisher+"\n"+
				"Quantity: " + String.valueOf(qty)+"\n"+
				"Price: " + String.valueOf(price)+"\n"+
				"Borrowing Copies: " + String.valueOf(brwcopies);
		return text;
	}

	// Getters and setters for the book's details
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getBrwcopies() {
		return brwcopies;
	}

	public void setBrwcopies(int brwcopies) {
		this.brwcopies = brwcopies;
	}

	/**
	 * Method to convert the book to a string in a specific format.
	 */
	public String toString2() {
		String text = name+"<N/>"+author+"<N/>"+publisher+"<N/>"+String.valueOf(qty)+
				"<N/>"+String.valueOf(price)+"<N/>"+String.valueOf(brwcopies);
		return text;
	}

}