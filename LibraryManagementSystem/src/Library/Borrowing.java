package Library;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a borrowing in the library management system.
 */
public class Borrowing {

	// The start date of the borrowing
	LocalDate start;

	// The finish date of the borrowing
	LocalDate finish;

	// The number of days left until the finish date
	int daysleft;

	// The book being borrowed
	Book book;

	// The user who is borrowing the book
	User user;

	// A date time formatter to format the dates
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 * Constructor to create a new borrowing with the current date as the start date and 14 days from now as the finish date
	 */
	public Borrowing(Book book, User user) {
		start = LocalDate.now();
		finish = start.plusDays(14);
		daysleft = Period.between(start, finish).getDays();
		this.book = book;
		this.user = user;
	}

	/**
	 * Constructor to create a new borrowing with the given start and finish dates
	 */
	public Borrowing(LocalDate start, LocalDate finish, Book book, User user) {
		this.start = start;
		this.finish = finish;
		this.daysleft = Period.between(finish, LocalDate.now()).getDays();
		this.book = book;
		this.user = user;
	}

	// Method to get the start date of the borrowing as a string
	public String getStart() {
		return formatter.format(start);
	}

	//Method to get the finish date of the borrowing as a string
	public String getFinish() {
		return formatter.format(finish);
	}

	//Method to get the number of days left until the finish date
	public int getDaysLeft() {
		return Period.between(finish, LocalDate.now()).getDays();
	}

	//Method to get the book being borrowed
	public Book getBook() {
		return book;
	}

	// Method to set the book being borrowed
	public void setBook(Book book) {
		this.book = book;
	}

	//Method to get the user who is borrowing the book
	public User getUser() {
		return user;
	}

	//Method to set the user who is borrowing the book
	public void setUser(User user) {
		this.user = user;
	}

	//Method to convert the borrowing to a string
	public String toString() {
		return "Borrowing time: "+start+"\nExpiry date: "+finish+"\nDays left: "+daysleft;
	}

	//Method to convert the borrowing to a string in a specific format
	public String toString2() {
		return getStart()+"<N/>"+getFinish()+"<N/>"+getDaysLeft()+"<N/>"+book.getName()+"<N/>"+
				user.getName()+"<N/>";
	}

}