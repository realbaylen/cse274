/**
 * <p>
 * This is an "interface"
 * </p>
 * <p>
 * It contains a list of the public methods of the class, but
 * they are all "pure virtual," which means they are not implemented
 * anywhere in the code. You will need to write a supporting class
 * to "implement" it.
 * </p>
 * 
 * @author Meisam Amjad
 */
public interface Trends {
	/**
	 * <p>
	 * Increase the count of the given string s.
	 * </p>
	 * <p>
	 * Example usage:
	 * </p>
	 * 
	 * <pre>
	 * increaseCount("apple", 3); // Increases the count of "apple" by 3.
	 * </pre>
	 * <p>
	 * Inside the method, you might:
	 * </p>
	 * <ul>
	 * <li>Check if the string s exists in your data structure.</li>
	 * <li>If it exists, increase its count by amount.</li>
	 * <li>If it doesn't exist, initialize it with the given amount.</li>
	 * <li>Ensure that exceptions such as negative amounts are handled
	 * properly, if necessary.</li>
	 * </ul>
	 * 
	 * @param s      String whose count is being increased.
	 * @param amount Amount by which it is being increased.
	 */
	public void increaseCount(String s, int amount);

	/**
	 * <p>
	 * Returns the number of times the given string s has been seen.
	 * </p>
	 * <p>
	 * Example usage:
	 * </p>
	 * 
	 * <pre>
	 * int count = getCount("apple"); // Returns the count of "apple" seen so far.
	 * </pre>
	 * <p>
	 * Inside the method, you might:
	 * </p>
	 * <ul>
	 * <li>Check if the string s exists in your data structure.</li>
	 * <li>If it exists, return its count.</li>
	 * <li>If it doesn't exist, return 0.</li>
	 * </ul>
	 * 
	 * @param s The string we are counting.
	 * @return The number of times s has been seen thus far.
	 */
	public int getCount(String s);

	/**
	 * <p>
	 * Gets the nth most popular item based on its count.
	 * </p>
	 *
	 * <p>
	 * The ranking starts at 0, where:
	 * </p>
	 * <ul>
	 * <li>0 = most popular item</li>
	 * <li>1 = second most popular item</li>
	 * <li>2 = third most popular item, and so on.</li>
	 * </ul>
	 *
	 * <p>
	 * In case of a tie, the string that comes first alphabetically is returned.
	 * </p>
	 *
	 * <p>
	 * Example usage:
	 * </p>
	 * 
	 * <pre>
	 * String popularItem = getNthPopular(1); // Returns the 2nd most popular item.
	 * </pre>
	 *
	 * <p>
	 * Inside the method, you might:
	 * </p>
	 * <ul>
	 * <li>Sort the items based on their count in descending order.</li>
	 * <li>If multiple items have the same count, sort them alphabetically.</li>
	 * <li>Return the item at index n in the sorted list.</li>
	 * <li>Handle cases where n is out of bounds.</li>
	 * </ul>
	 * 
	 * @param n Rank requested.
	 * @return The nth most popular string.
	 */
	public String getNthPopular(int n);

	/**
	 * <p>
	 * Returns the total number of unique strings in the list.
	 * </p>
	 *
	 * <p>
	 * This will <strong>not</strong> be equal to the number of times
	 * <code>increaseCount</code> has been called, because the same string
	 * may be added multiple times.
	 * </p>
	 *
	 * <p>
	 * This function is useful when looping through the results
	 * using <code>getNthPopular</code>.
	 * </p>
	 *
	 * <p>
	 * Example usage:
	 * </p>
	 * 
	 * <pre>
	 * int uniqueCount = numEntries(); // Returns the total number of unique strings stored.
	 * </pre>
	 * 
	 * <pre>
	 * Calling <code>getNthPopular(numEntries() - 1)</code> should return the
	 * least popular item.
	 * </pre>
	 *
	 * <p>
	 * Additional details:
	 * </p>
	 * <ul>
	 * <li>Counts only distinct strings, ignoring duplicate additions.</li>
	 * <li>Useful for iterating through all stored strings using
	 * <code>getNthPopular</code>.</li>
	 * </ul>
	 * 
	 * @return The number of distinct entries.
	 */
	public int numEntries();
}
