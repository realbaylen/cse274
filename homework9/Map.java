import java.util.Collection; // for values()
import java.util.Set;  // for KeySet()

/**
 * Map Interface.
 * Represents a collection of key-value pairs where each key is unique and
 * associated with a single value.
 * The keys and values must implement the Comparable interface.
 *
 * @param <K> the type of keys; must be Comparable
 * @param <V> the type of values; must be Comparable
 * 
 * @author amjadm
 */
public interface Map<K extends Comparable<K>, V extends Comparable<V>> {

    /**
     * Checks if the map contains the specified key.
     *
     * @param key the key to check for
     * @return true if the key exists in the map, false otherwise
     */
    boolean containsKey(Object key);

    /**
     * Checks if the map contains the specified value.
     *
     * @param value the value to check for
     * @return true if the value exists in the map, false otherwise
     */
    boolean containsValue(Object value);

    /**
     * Compares this map with the specified object for equality.
     *
     * @param o the object to compare with
     * @return true if the specified object is equal to this map, false otherwise
     */
    boolean equals(Object o);

    /**
     * Retrieves the value associated with the specified key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the key, or null if no mapping exists
     */
    V get(Object key);

    /**
     * Associates the specified value with the specified key in the map.
     * If the key already exists, it replaces its value with the new given value.
     * If not, it addes a new key, value pair.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the key
     * @return the previous value associated with the key, or null if there was no
     *         mapping
     */
    V put(K key, V value);

    /**
     * Copies all of the mappings from the specified map to this map.
     * If already existing Keys, it replaces their old values with new ones.
     *
     * @param m the map containing mappings to be stored in this map
     */
    void putAll(Map<? extends K, ? extends V> m);

    /**
     * Replaces the entry for the specified key only if it is currently mapped to
     * some value.
     *
     * @param key   the key whose value is to be replaced
     * @param value the new value to be associated with the key
     * @return the previous value associated with the key, or null if there was no
     *         mapping
     */
    V replace(K key, V value);

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param key the key whose mapping is to be removed
     * @return the value previously associated with the key, or null if there was no
     *         mapping
     */
    V remove(Object key);

    /**
     * Removes the mapping for the specified key only if it is currently mapped to
     * the specified value.
     *
     * @param key   the key with which the specified value is associated
     * @param value the value expected to be associated with the specified key
     * @return true if the mapping was removed, false otherwise
     */
    boolean remove(Object key, Object value);

    /**
     * Returns a set view of the keys contained in this map.
     * 
     * @return a set of keys contained in this map
     */
    Set<K> keySet();

    /**
     * Returns a collection view of the values contained in this map.
     *
     * @return a collection of values contained in this map
     */
    Collection<V> values();

    /**
     * Removes all mappings from this map.
     */
    void clear();

    /**
     * Returns true if this map contains no key-value mappings.
     *
     * @return true if the map is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the size of the map
     */
    int size();
}