import java.util.ArrayList;

/**
 * Represents content with a string.
 */
public class Content {
  private String content;

  /**
   * Constructs a Content with the specified string.
   * @param str the string content
   * @throws NullPointerException if the string is null
   */
  public Content(String str) {
    if (str == null) {
      throw new NullPointerException();
    }
    content = new String(str);
  }

  /**
   * Copy constructor for Content.
   * @param original the original content to copy
   */
  public Content(Content original) {
    this(original.getContent());
  }

  /**
   * Gets the content string.
   * @return the content string
   */
  public String getContent() {
    return content;
  }

  /**
   * Checks if the content contains the specified key.
   * @param key the key to search for
   * @return true if the content contains the key, false otherwise
   */
  public boolean contains(String key) {
    if (key == null) return false;
    return content.contains(key);
  }

  /**
   * Finds the indexes of the specified key in the content.
   * @param key the key to search for
   * @return an array of indexes where the key is found
   */
  public int[] indexOf(String key) {
    if (key == null || key.isEmpty()) {
      return new int[0];
    }

    ArrayList<Integer> indexes = new ArrayList<>();
    int index = content.indexOf(key);
    while (index != -1) {
      indexes.add(index);
      index = content.indexOf(key, index + 1);
    }

    int[] result = new int[indexes.size()];
    for (int i = 0; i < indexes.size(); i++) {
      result[i] = indexes.get(i);
    }

    return result;
  }

  /**
   * Creates a clone of this content.
   * @return a clone of this content
   */
  public Object clone() {
    return new Content(this);
  }

  /**
   * Returns a string representation of the content.
   * @return a string representation of the content
   */
  public String toString() {
    return String.format("Content %s%n", content);
  }
}