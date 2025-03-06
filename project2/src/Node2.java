public class Node2<T extends Comparable<T>> implements Comparable<Node2<T>> {
    public T data;
    Node2<T> prev, next;

    public Node2(T data) {
        this.data = data;
        this.prev = this.next = null;
    }

    @Override
    public int compareTo(Node2<T> obj) {
        return this.data.compareTo(obj.data);
    }

    @Override
    public String toString() {
        return this.data.toString();
    }
}
