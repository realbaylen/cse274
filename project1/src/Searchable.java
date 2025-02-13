/**
 * Interface for searchable content.
 */
public interface Searchable {
  /**
   * Checks if the content contains the specified key.
   * @param key the key to search for
   * @return true if the content contains the key, false otherwise
   */
  boolean contains(String key);

  /**
   * Finds the indexes of the specified key in the content.
   * @param key the key to search for
   * @return an array of indexes where the key is found
   */
  Integer[] indexOf(String key);
}