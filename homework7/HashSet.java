import java.util.HashMap;
import java.util.Iterator;

public class HashSet<T extends Comparable<T>> implements Set<T> {
  private HashMap<T, Object> map;
  private static final Object DUMMY = new Object();

  public HashSet() {
    map = new HashMap<>();
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

  public boolean addAll(Collection<? extends T> c) {
    boolean modified = false;
    for (T value : c) {
      if (add(value)) {
        modified = true;
      }
    }
    return modified;
  }
}
