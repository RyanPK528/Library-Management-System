package Library;

/**
 * Interface for performing input/output operations on a database
 */
public interface IOOperation {

	/**
	 * Performs an I/O operation on the given database for the specified user
	 */
	public void oper(Database database, User user);

}