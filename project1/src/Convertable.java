/**
 * Interface for convertable content.
 */
public interface Convertable {
  /**
   * Converts the content to a byte array of the specified size.
   * @param byteSize the size of the byte array
   * @return the byte array representation of the content
   */
  byte[] getBytes(int byteSize);
}