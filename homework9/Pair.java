/**
 * <html>
 * <body>
 * <p>
 * <strong>Pair Class</strong>
 * </p>
 * 
 * <p>
 * This class encapsulates a key-value pair, where the key and value are generic
 * types.
 * It implements the <code>Comparable</code> interface, using both value and key
 * for natural ordering.
 * </p>
 * 
 * <p>
 * <strong>Features:</strong>
 * </p>
 * <ul>
 * <li>Stores a key-value pair.</li>
 * <li>Provides accessors for key and value.</li>
 * <li>Allows the value to be updated while keeping the key immutable.</li>
 * <li>Defines equality, hash code, and natural ordering based solely on the
 * key.</li>
 * <li>Offers a clear string representation for debugging and logging.</li>
 * </ul>
 * </body>
 * </html>
 *
 * @param <K> the key type, must be Comparable
 * @param <V> the value type, must be Comparable
 */
public class Pair<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Pair<K, V>> {

    private final K key;
    private V value;

    /**
     * Constructs a new Pair with the specified key and value.
     *
     * @param key   the key to associate with this pair
     * @param value the value associated with the key
     */
    public Pair(final K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * <p>
     * <strong>Retrieves the key of this pair.</strong>
     * </p>
     *
     * @return the key of this pair
     */
    public K getKey() {
        return this.key;
    }

    /**
     * <p>
     * <strong>Retrieves the value of this pair.</strong>
     * </p>
     *
     * @return the current value associated with the key
     */
    public V getValue() {
        return this.value;
    }

    /**
     * <p>
     * <strong>Updates the value of this pair.</strong>
     * </p>
     *
     * @param value the new value to set for this pair
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * <p>
     * <strong>Determines whether this pair is equal to another object based solely
     * on the key.</strong>
     * </p>
     *
     * @param obj the object to compare with this pair
     * @return true if the other object is a Pair with the same key, false otherwise
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Pair<K, V> other = (Pair<K, V>) obj;
        return key.equals(other.key);
    }

    /**
     * <p>
     * <strong>Compares this pair with another pair based on value, then
     * key.</strong>
     * </p>
     *
     * @param other the pair to compare with
     * @return a negative, zero, or positive integer if this pair is less than,
     *         equal to, or greater than the other pair
     */
    @Override
    public int compareTo(Pair<K, V> other) {
        int cmp = this.value.compareTo(other.getValue());
        if (cmp != 0) {
            return cmp;
        }
        return this.key.compareTo(other.getKey());
    }

    /**
     * <p>
     * <strong>Generates a hash code for this pair based solely on the key.</strong>
     * </p>
     *
     * @return hash code computed from the key
     */
    @Override
    public int hashCode() {
        return (key == null ? 0 : key.hashCode());
    }

    /**
     * <p>
     * <strong>Returns a string representation of this pair.</strong>
     * </p>
     *
     * @return a string in the format (key, value)
     */
    @Override
    public final String toString() {
        return "(" + key + ", " + value + ")";
    }
}