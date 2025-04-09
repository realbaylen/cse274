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

  @SuppressWarnings("unchecked")
  public HashMap() {
    buckets = new LinkedList[DEFAULT_CAPACITY];
    for (int i = 0; i < DEFAULT_CAPACITY; i++) {
      buckets[i] = new LinkedList<>();
    }
    size = 0;
  }

  @SuppressWarnings("unchecked")
  public HashMap(int initialCapacity) {
    int capacity = (initialCapacity <= 0) ? DEFAULT_CAPACITY : initialCapacity;
    buckets = new LinkedList[capacity];
    for (int i = 0; i < capacity; i++) {
      buckets[i] = new LinkedList<>();
    }
    size = 0;
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

  @Override
  public boolean containsValue(Object value) {
    for (LinkedList<Pair<K, V>> bucket : buckets) {
      for (Pair<K, V> pair : bucket) {
        if ((value == null && pair.getValue() == null)
            || (value != null && value.equals(pair.getValue()))) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public V get(Object key) {
    int index = getIndex(key);
    LinkedList<Pair<K, V>> bucket = buckets[index];

    for (Pair<K, V> pair : bucket) {
      if ((key == null && pair.getKey() == null) || (key != null && key.equals(pair.getKey()))) {
        return pair.getValue();
      }
    }
    return null;
  }

  @Override
  public V put(K key, V value) {
    if (size >= buckets.length * LOAD_FACTOR) {
      grow();
    }

    int index = getIndex(key);
    LinkedList<Pair<K, V>> bucket = buckets[index];

    for (Pair<K, V> pair : bucket) {
      if ((key == null && pair.getKey() == null) || (key != null && key.equals(pair.getKey()))) {
        V oldValue = pair.getValue();
        pair.setValue(value);
        return oldValue;
      }
    }

    bucket.add(new Pair<>(key, value));
    size++;
    return null;
  }

  @Override
  public void putAll(Map<? extends K, ? extends V> m) {
    Set<? extends K> keys = m.keySet();
    for (K key : keys) {
      put(key, m.get(key));
    }
  }

  @Override
  public V replace(K key, V value) {
    int index = getIndex(key);
    LinkedList<Pair<K, V>> bucket = buckets[index];

    for (Pair<K, V> pair : bucket) {
      if ((key == null && pair.getKey() == null) || (key != null && key.equals(pair.getKey()))) {
        V oldValue = pair.getValue();
        pair.setValue(value);
        return oldValue;
      }
    }
    return null;
  }

  @Override
  public V remove(Object key) {
    int index = getIndex(key);
    LinkedList<Pair<K, V>> bucket = buckets[index];

    Iterator<Pair<K, V>> iterator = bucket.iterator();
    while (iterator.hasNext()) {
      Pair<K, V> pair = iterator.next();
      if ((key == null && pair.getKey() == null) || (key != null && key.equals(pair.getKey()))) {
        V oldValue = pair.getValue();
        iterator.remove();
        size--;
        return oldValue;
      }
    }
    return null;
  }

  @Override
  public boolean remove(Object key, Object value) {
    int index = getIndex(key);
    LinkedList<Pair<K, V>> bucket = buckets[index];

    Iterator<Pair<K, V>> iterator = bucket.iterator();
    while (iterator.hasNext()) {
      Pair<K, V> pair = iterator.next();
      if ((key == null && pair.getKey() == null) || (key != null && key.equals(pair.getKey()))) {
        if ((value == null && pair.getValue() == null)
            || (value != null && value.equals(pair.getValue()))) {
          iterator.remove();
          size--;
          return true;
        }
        return false;
      }
    }
    return false;
  }

  @Override
  public Set<K> keySet() {
    Set<K> keys = new HashSet<>();
    for (LinkedList<Pair<K, V>> bucket : buckets) {
      for (Pair<K, V> pair : bucket) {
        keys.add(pair.getKey());
      }
    }
    return keys;
  }

  @Override
  public Collection<V> values() {
    Collection<V> values = new ArrayList<>();
    for (LinkedList<Pair<K, V>> bucket : buckets) {
      for (Pair<K, V> pair : bucket) {
        values.add(pair.getValue());
      }
    }
    return values;
  }

  @Override
  public void clear() {
    for (LinkedList<Pair<K, V>> bucket : buckets) {
      bucket.clear();
    }
    size = 0;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Map)) return false;

    Map<?, ?> other = (Map<?, ?>) obj;
    if (this.size() != other.size()) return false;

    for (Pair<K, V> pair : this.entries()) {
      Object value = other.get(pair.getKey());
      if (!((pair.getValue() == null && value == null)
          || (pair.getValue() != null && pair.getValue().equals(value)))) {
        return false;
      }
    }
    return true;
  }

  public HashMap<K, V> clone() {
    HashMap<K, V> clone = new HashMap<>(buckets.length);
    for (Pair<K, V> pair : this.entries()) {
      clone.put(pair.getKey(), pair.getValue());
    }
    return clone;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("{");
    boolean first = true;

    for (LinkedList<Pair<K, V>> bucket : buckets) {
      for (Pair<K, V> pair : bucket) {
        if (!first) {
          sb.append(", ");
        }
        sb.append(pair.getKey()).append(", ").append(pair.getValue());
        first = false;
      }
    }

    sb.append("}");
    return sb.toString();
  }

  private int getIndex(Object key) {
    if (key == null) return 0;
    return Math.abs(key.hashCode()) % buckets.length;
  }

  @SuppressWarnings("unchecked")
  private void grow() {
    LinkedList<Pair<K, V>>[] oldBuckets = buckets;
    buckets = new LinkedList[oldBuckets.length * 2];

    for (int i = 0; i < buckets.length; i++) {
      buckets[i] = new LinkedList<>();
    }

    size = 0;
    for (LinkedList<Pair<K, V>> bucket : oldBuckets) {
      for (Pair<K, V> pair : bucket) {
        put(pair.getKey(), pair.getValue());
      }
    }
  }

  private Iterable<Pair<K, V>> entries() {
    Collection<Pair<K, V>> entries = new ArrayList<>();
    for (LinkedList<Pair<K, V>> bucket : buckets) {
      entries.addAll(bucket);
    }
    return entries;
  }
}
