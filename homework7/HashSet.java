import java.util.HashMap;
import java.util.Iterator;

public class HashSet<T extends Comparable<T>> implements Set<T> {
  private HashMap<T, Object> map;
  private static final Object DUMMY = new Object();

  public HashSet() {
    map = new HashMap<>();
  }

  @Override
  public int hashCode() {
    return map.keySet().hashCode();
  }

  public HashSet(int capacity) {
    map = new HashMap<>(capacity);
  }

  @Override
  public boolean add(T value) {
    if (map.containsKey(value)) {
      return false;
    }
    map.put(value, DUMMY);
    return true;
  }

  @Override
  public boolean remove(T value) {
    if (map.containsKey(value)) {
      map.remove(value);
      return true;
    }
    return false;
  }

  @Override
  public boolean contains(T value) {
    return map.containsKey(value);
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public void clear() {
    map.clear();
  }

  @Override
  public Iterator<T> iterator() {
    return map.keySet().iterator();
  }

  @Override
  public boolean isEmpty() {
    return map.isEmpty();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    Iterator<T> it = map.keySet().iterator();
    while (it.hasNext()) {
      sb.append(it.next());
      if (it.hasNext()) {
        sb.append(", ");
      }
    }
    sb.append("}");
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    HashSet<?> hashSet = (HashSet<?>) o;
    return map.keySet().equals(hashSet.map.keySet());
  }
}
