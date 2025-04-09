import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class HashMap<K extends Comparable<K>, V extends Comparable<V>> implements Map<K, V> {
  private static final int DEFAULT_CAPACITY = 16;
  private static final double LOAD_FACTOR = 0.75;

  private LinkedList<Pair<K, V>>[] buckets;
  private int size;
  private int threshold; 
  

  @SuppressWarnings("unchecked")
  public HashMap() {
    buckets = new LinkedList[DEFAULT_CAPACITY];
    for (int i = 0; i < DEFAULT_CAPACITY; i++) {
      buckets[i] = new LinkedList<>();
    }
    size = 0;
    threshold = (int) (DEFAULT_CAPACITY * LOAD_FACTOR);
  }
    @SuppressWarnings("unchecked")
    public HashMap(int initialCapacity) {
        int capacity = (initialCapacity <= 0) ? DEFAULT_CAPACITY : initialCapacity;
        buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
        size = 0;
        threshold = (int) (capacity * LOAD_FACTOR);
    }

    @Override
    public boolean containsKey(Object key) {
        int index = getIndex(key);
        LinkedList<Pair<K, V>> bucket = buckets[index];

        for (Pair<K, V> pair : bucket) {
            if ((key == null && pair.getKey() == null) || (key != null && key.equals(pair.getKey()))) {
                return true;
            }
        }
        return false;
    }
}