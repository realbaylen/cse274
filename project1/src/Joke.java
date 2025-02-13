/**
 * Represents a joke with a category and content.
 */
public class Joke extends Content implements Convertable, Comparable<Joke> {
  private String category;

  /**
   * Constructs a Joke with the specified category and content.
   * @param category the category of the joke
   * @param content the content of the joke
   * @throws NullPointerException if the category is null
   */
  public Joke(String category, String content) {
    super(content);
    if (category == null) {
      throw new NullPointerException();
    }
    this.category = new String(category);
  }

  /**
   * Compares this joke to another joke.
   * @param other the other joke to compare to
   * @return a negative integer, zero, or a positive integer as this joke is less than, equal to, or greater than the specified joke
   */
  @Override
  public int compareTo(Joke other) {
    if (this.getContent().length() == other.getContent().length()) {
      int minLength = Math.min(this.getContent().length(), other.getContent().length());
      minLength = Math.min(minLength, 10);
      return this.getContent().substring(0, minLength).compareTo(other.getContent().substring(0, minLength));
    }
    return Integer.compare(this.getContent().length(), other.getContent().length());
  }

  /**
   * Checks if this joke is equal to another object.
   * @param obj the object to compare to
   * @return true if the object is a joke with the same content, false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Joke)) return false;
    return this.compareTo((Joke) obj) == 0;
  }

  /**
   * Converts the joke to a byte array of the specified size.
   * @param byteSize the size of the byte array
   * @return the byte array representation of the joke
   */
  @Override
  public byte[] getBytes(int byteSize) {
    byteSize = Math.max(byteSize, 500);
    byte[] result = new byte[byteSize];
    byte[] categoryBytes = category.getBytes();
    byte[] contentBytes = this.getContent().getBytes();
    int categoryLength = Math.min(categoryBytes.length, 15);
    System.arraycopy(categoryBytes, 0, result, 0, categoryLength);
    int contentLength = Math.min(contentBytes.length, byteSize - categoryLength);
    System.arraycopy(contentBytes, 0, result, categoryLength, contentLength);
    return result;
  }

  /**
   * Creates a clone of this joke.
   * @return a clone of this joke
   */
  @Override
  public Object clone() {
    return new Joke(this.category, this.getContent());
  }

  /**
   * Returns a string representation of the joke.
   * @return a string representation of the joke
   */
  @Override
  public String toString() {
    return String.format("Category: %s%nJoke: %s%n", category, getContent().substring(0, Math.min(10, getContent().length())));
  }

  /**
   * Gets the category of the joke.
   * @return the category of the joke
   */
  public String getCategory() {
    return this.category;
  }
}