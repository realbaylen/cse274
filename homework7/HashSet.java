import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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
  public boolean addAll(Collection<? extends T> c) {
    boolean modified = false;
    for (T elem : c) {
      if (add(elem)) {
        modified = true;
      }
    }
    return modified;
  }

  @Override
  public boolean remove(Object value) {
    if (map.containsKey(value)) {
      map.remove(value);
      return true;
    }
    return false;
  }

  @Override
  public boolean contains(Object value) {
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
  public boolean containsAll(Collection<?> c) {
    for (Object value : c) {
      if (!contains(value)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    boolean modified = false;
    for (Object value : c) {
      if (remove(value)) {
        modified = true;
      }
    }
    return modified;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    boolean modified = false;
    Iterator<T> it = iterator();
    while (it.hasNext()) {
      if (!c.contains(it.next())) {
        it.remove();
        modified = true;
      }
    }
    return modified;
  }

  @Override
  public Object[] toArray() {
    return map.keySet().toArray();
  }

  @Override
  public <E> E[] toArray(E[] a) {
    return map.keySet().toArray(a);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    HashSet<?> hashSet = (HashSet<?>) o;
    return map.keySet().equals(hashSet.map.keySet());
  }

  @Override
  public int hashCode() {
    return map.keySet().hashCode();
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
}
