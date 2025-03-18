public class HashSet<T extends Comparable<T>> implements Set<T> {
    private HashMap<T, Object> map;
    private static final Object DUMMY = new Object();

    public HashSet() {
        map = new HashMap<>();
    }

    @Override
    public void add(T value) {
        map.put(value, DUMMY);
    }

    @Override
    public void remove(T value) {
        map.remove(value);
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
}
